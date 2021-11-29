package com.coronacommunity.CoronaCommunity.web.controller;

import com.coronacommunity.CoronaCommunity.entity.Admin;
import com.coronacommunity.CoronaCommunity.entity.Notice;
import com.coronacommunity.CoronaCommunity.repository.NoticeRepository;
import com.coronacommunity.CoronaCommunity.service.AdminService;
import com.coronacommunity.CoronaCommunity.service.BoardService;
import com.coronacommunity.CoronaCommunity.service.ChatService;
import com.coronacommunity.CoronaCommunity.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class AdminApiController {
    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private AdminService adminService;

    //------------------------------------------------------------------------------------------------
    // 여기서부터는 클라이언트 API 코드

    //버전 확인 API
    @GetMapping("/api/version")
    public ResponseEntity VersionAPI() {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");
        JSONObject data = new JSONObject();
        data.put("ios", "2.1");
        data.put("iosUrl", "http://test.byeonggook.shop/");
        data.put("android", "2.1");
        data.put("androidUrl", "http://test.byeonggook.shop/");



        JSONObject result_data = new JSONObject();
        result_data.put("version", data);

        jsonObject.put("result_data", result_data);
        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //로그인 인증 API
    @PostMapping("/api/auth/Login")
    public ResponseEntity LoginAuthenticationApi(@RequestParam("NAME") String Name, @RequestParam("PASSWORD") String password) {
        Admin admin = adminService.adminGetInformation(1L);

        if(Name.equals(admin.getName()) && password.equals(admin.getPassword())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 0);
            jsonObject.put("message", "success");
            JSONObject data = new JSONObject();
            data.put("id", admin.getId());
            data.put("NAME", admin.getName());

            JSONObject result_data = new JSONObject();
            result_data.put("account", data);

            jsonObject.put("result_data", result_data);

            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", "application/json; charset=UTF-8");


            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", -1);
        jsonObject.put("message", "failure");
        jsonObject.put("error", "아이디 비밀번호를 정확히 입력해주세요");

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");




        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
    }

    //관리자페이지 게시판 전체 목록 조회 API
    @GetMapping("/api/admin/boardList")
    public ResponseEntity BoardListAPI() {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        JSONObject result_data = new JSONObject();

        result_data.put("data", adminService.boardAdminListApi());

        jsonObject.put("result_data", result_data);


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }



    //관리자페이지 해당 게시글 댓글 전체 목록 조회 API
    @GetMapping("/api/admin/{boardId}/chatList")
    public ResponseEntity boardChatListAPI(@PathVariable long boardId) {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        JSONObject result_data = new JSONObject();

        result_data.put("data", adminService.chatAdminListApi(boardId));


        jsonObject.put("result_data", result_data);


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }


    //공지사항 목록조회 API
    @GetMapping("/api/notice/noticeList")
    public ResponseEntity NoticeListAPI() {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        JSONObject result_data = new JSONObject();

        result_data.put("data", noticeService.noticeListApi());


        jsonObject.put("result_data", result_data);




        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }


    //공지사항 등록 API
    @PostMapping("/api/notice/noticeAdd")
    public ResponseEntity NoticeAddAPI(@RequestParam(value="title",required = false) String title,@RequestParam(value="content",required = false) String content) {

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");


        if((title == null || title.trim().isEmpty()) || (content == null || content.trim().isEmpty())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "제목 내용 데이터를 기입해주세요");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }

        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        noticeService.noticeAdd(notice);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //특정 공지사항 조회 API
    @GetMapping("/api/notice/{noticeId}")
    public ResponseEntity NoticeDetailAPI(@PathVariable("noticeId") long noticeId) {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        JSONObject data;
        Notice noticeDetail = noticeService.noticeDetail(noticeId);
        try {
            data = new JSONObject();
            data.put("id", noticeDetail.getId());
            data.put("title",noticeDetail.getTitle());
            data.put("content",noticeDetail.getContent());
            data.put("hit",noticeDetail.getHit());
            data.put("createDate",noticeDetail.getCreatedDate());
            data.put("modifiedDate",noticeDetail.getModifiedDate());

        } catch (NoSuchElementException error) {
            JSONObject ERROR_jsonObject = new JSONObject();
            ERROR_jsonObject.put("code", -1);
            ERROR_jsonObject.put("message", "failure");
            ERROR_jsonObject.put("error", "조회할수없는 게시글입니다.");

            return new ResponseEntity(ERROR_jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }

        JSONObject result_data = new JSONObject();
        result_data.put("account", data);

        jsonObject.put("result_data", result_data);


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //특정 공지사항 삭제 API
    @GetMapping("/api/notice/{noticeId}/delete")
    public ResponseEntity NoticeDeleteAPI(@PathVariable("noticeId") long noticeId) {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        try {
            noticeService.noticeDelete(noticeId);
        } catch(EmptyResultDataAccessException error){
            JSONObject ERROR_jsonObject = new JSONObject();
            ERROR_jsonObject.put("code", -1);
            ERROR_jsonObject.put("message", "failure");
            ERROR_jsonObject.put("error", "삭제할 게시글이 존재하지않습니다.");

            return new ResponseEntity(ERROR_jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");


        return new ResponseEntity(jsonObject.toString(),header,HttpStatus.OK);
    }

    // 특정 공지사항 수정하기 API
    @PutMapping("/api/notice/{noticeId}/edit")
    public ResponseEntity NoticeEditAPI(@PathVariable Long noticeId,Notice notice) {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        if((notice.getTitle() == null || notice.getTitle() .trim().isEmpty()) || (notice.getContent()  == null || notice.getContent().trim().isEmpty())){
            JSONObject ERROR_jsonObject = new JSONObject();
            ERROR_jsonObject.put("code", -1);
            ERROR_jsonObject.put("message", "failure");
            ERROR_jsonObject.put("error", "제목 내용 데이터를 기입해주세요");

            return new ResponseEntity(ERROR_jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }

        Notice AfterNotice;
        try {
            noticeRepository.findById(noticeId)
                    .map(Notice -> {
                        Notice.setTitle(notice.getTitle());
                        Notice.setContent(notice.getContent());
                        return noticeRepository.save(Notice);
                    });

            AfterNotice = noticeService.noticeDetail(noticeId);
        } catch (NoSuchElementException error) {
            JSONObject ERROR_jsonObject = new JSONObject();
            ERROR_jsonObject.put("code", -1);
            ERROR_jsonObject.put("message", "failure");
            ERROR_jsonObject.put("error", "수정할 게시글이 존재하지않습니다.");

            return new ResponseEntity(ERROR_jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        JSONObject data = new JSONObject();
        data.put("id", AfterNotice.getId());
        data.put("title", AfterNotice.getTitle());
        data.put("content", AfterNotice.getContent());


        JSONObject result_data = new JSONObject();
        result_data.put("account", data);

        jsonObject.put("result_data", result_data);

        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }
}
