package com.coronacommunity.CoronaCommunity.web.controller;

import com.coronacommunity.CoronaCommunity.entity.Board;
import com.coronacommunity.CoronaCommunity.entity.Chat;
import com.coronacommunity.CoronaCommunity.repository.BoardRepository;
import com.coronacommunity.CoronaCommunity.service.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class BoardApiController {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private c_DeclarationipService c_declarationipService;


    @Autowired
    private c_IpstoreService c_ipstoreService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private b_IpstoreService b_ipstoreService;

    @Autowired
    private b_DeclarationipService b_declarationipService;

    @Autowired
    private BoardRepository boardRepository;


    //게시판 전체 리스트 가져오기
    @GetMapping("/api/board/boardList")
    public ResponseEntity BoardListAPI() {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        JSONObject result_data = new JSONObject();

        result_data.put("data", boardService.boardListApi());

        jsonObject.put("result_data", result_data);


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //게시판 등록 API
    @PostMapping("api/board/boardAdd")
    public ResponseEntity boardAddAPI(@RequestParam(value="nickname",required = false)String nickname,
                                      @RequestParam(value="title", required = false) String title,
                                      @RequestParam(value="password", required = false) String password,
                                      @RequestParam(value="content",required = false) String content){

        HttpHeaders header=new HttpHeaders();
        header.add("Content-Type","application/json;charset=UTF-8");

        //사용자가 nickname 데이터와 content 데이터를 null값으로 요청할시 에러코드 반환
        if((nickname==null||nickname.trim().isEmpty())||(content==null||content.trim().isEmpty())||title==null||title.trim().isEmpty()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",-1);
            jsonObject.put("message","failure");
            jsonObject.put("error","제목내용데이터를기입해주세요");

            return new ResponseEntity(jsonObject.toString(),header,HttpStatus.NOT_FOUND);
        }

        Board board = new Board();
        board.setNickname(nickname);
        board.setTitle(title);
        board.setPassword(password);
        board.setContent(content);
        boardService.boardAdd(board);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        return new ResponseEntity(jsonObject.toString(),header,HttpStatus.OK);
    }

    //게시판 단일 게시글 조회
    @GetMapping("/api/board/{boardId}")
    public ResponseEntity boardDetailAPI(@PathVariable("boardId") long boardId) throws Exception{
        HttpHeaders header=new HttpHeaders();
        header.add("Content-Type","application/json;charset=UTF-8");

        Long checkBoardId = b_ipstoreService.CheckBoardId(boardId);

        //api로 요청시 없는 게시글로 요청시 에러응답 반환
        if(checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 게시글 번호는 없는 게시글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("message","success");

        JSONObject data;
        Board boardDetail =  boardService.boardDetail(boardId);
            data = new JSONObject();
            data.put("id",boardDetail.getId());
            data.put("nickname",boardDetail.getNickname());
            data.put("password",boardDetail.getPassword());
            data.put("title",boardDetail.getTitle());
            data.put("content",boardDetail.getContent());
            data.put("hit",boardDetail.getHit());
            data.put("declaration",boardDetail.getDeclaration());
            data.put("recommend",boardDetail.getRecommend());
            data.put("deprecate",boardDetail.getDeprecate());
            data.put("createDate",boardDetail.getCreatedDate());
            data.put("modifiedDate",boardDetail.getModifiedDate());




        JSONObject result_data = new JSONObject();
        result_data.put("account",data);

        jsonObject.put("result_data",result_data);


        return new ResponseEntity(jsonObject.toString(),header,HttpStatus.OK);

    }

    //게시판 삭제 API
    @PostMapping("/api/board/{boardId}/delete")
    public ResponseEntity boardDeleteAPI(@PathVariable("boardId") long boardId
            ,@RequestParam("password")String password) {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkBoardId = b_ipstoreService.CheckBoardId(boardId);

        //api로요청시없는게시글로요청시에러응답반환
        if (checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한게시글번호는없는게시글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }

        Board passCheckBord = boardService.boardDetail(boardId);

        if (password.equals(passCheckBord.getPassword())) {
            boardService.boardDeleteChangeView(boardId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 0);
            jsonObject.put("message", "success");
            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "비밀번호가다릅니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }
    }

    //게시판 추천 API
    //엉뚱한 게시글번호로 요청시 에러처리 하는방법을 모르겠음..
    @GetMapping("/api/board/{boardId}/recommend")
    public ResponseEntity BoardRecommendAPI(@PathVariable long boardId, HttpServletRequest request, Model model) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkBoardId = b_ipstoreService.CheckBoardId(boardId);

        //api로 요청시 없는 게시글로 요청시 에러응답 반환
        if(checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 게시글 번호는 없는 게시글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }


        String ip=request.getHeader("X-Forwarded-For");

        logger.info(">>>>X-FORWARDED-FOR:"+ip);

        if(ip==null){
            ip=request.getHeader("Proxy-Client-IP");
            logger.info(">>>>Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("WL-Proxy-Client-IP");
            logger.info(">>>>WL-Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>>HTTP_CLIENT_IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>>HTTP_X_FORWARDED_FOR:"+ip);
        }
        if(ip==null){
            ip=request.getRemoteAddr();
        }




        //ip 가져오기
        String checkIp = b_ipstoreService.IpSelectQuery(boardId,ip);

        Chat boardIdCheck;


        if(ip.equals(checkIp)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "추천 비추천 기능은 IP당 한번만 가능합니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        } else {
            //데이터베이스에 클라이언트 ip 추가하기
            b_ipstoreService.ipAdd(boardId,ip);
            //추천수 1 증가하기
            boardService.boardRecommend(boardId);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //게시판 비추천 API
    @GetMapping("/api/board/{boardId}/deprecate")
    public ResponseEntity BoardDeprecateAPI(@PathVariable long boardId, HttpServletRequest request, Model model) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkBoardId = b_ipstoreService.CheckBoardId(boardId);

        //api로 요청시 없는 게시글로 요청시 에러응답 반환
        if(checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 게시글 번호는 없는 게시글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }


        String ip=request.getHeader("X-Forwarded-For");

        logger.info(">>>>X-FORWARDED-FOR:"+ip);

        if(ip==null){
            ip=request.getHeader("Proxy-Client-IP");
            logger.info(">>>>Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("WL-Proxy-Client-IP");
            logger.info(">>>>WL-Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>>HTTP_CLIENT_IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>>HTTP_X_FORWARDED_FOR:"+ip);
        }
        if(ip==null){
            ip=request.getRemoteAddr();
        }

        //ip 가져오기
        String checkIp = b_ipstoreService.IpSelectQuery(boardId,ip);


        if(ip.equals(checkIp)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "추천 비추천 기능은 IP당 한번만 가능합니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        } else {
            //데이터베이스에 클라이언트 ip 추가하기
            b_ipstoreService.ipAdd(boardId,ip);
            //비추천수 1 증가하기
            boardService.boardDeprecate(boardId);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //게시판 신고 API
    @GetMapping("/api/board/{boardId}/declaration")
    public ResponseEntity BoardDeclarationAPI(@PathVariable long boardId, HttpServletRequest request, Model model) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkBoardId = b_ipstoreService.CheckBoardId(boardId);

        //api로 요청시 없는 게시글로 요청시 에러응답 반환
        if(checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 게시글 번호는 없는 게시글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }


        String ip=request.getHeader("X-Forwarded-For");

        logger.info(">>>>X-FORWARDED-FOR:"+ip);

        if(ip==null){
            ip=request.getHeader("Proxy-Client-IP");
            logger.info(">>>>Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("WL-Proxy-Client-IP");
            logger.info(">>>>WL-Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>>HTTP_CLIENT_IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>>HTTP_X_FORWARDED_FOR:"+ip);
        }
        if(ip==null){
            ip=request.getRemoteAddr();
        }

        //ip 가져오기
        String checkIp = b_declarationipService.IpSelectQuery(boardId, ip);


        if(ip.equals(checkIp)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "신고 기능은 IP당 한번만 가능합니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        } else {
            b_declarationipService.ipAdd(boardId,ip);
            boardService.boardDeclaration(boardId);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");
        jsonObject.put("help", "신고기능 5회초과시 게시글 자동 삭제되니 참고 바랍니다.");


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }


    //여기서부터는 댓글 API 코드!

    //댓글 전체 목록 조회 API
    @GetMapping("/api/chat/{boardId}/chatList")
    public ResponseEntity boardChatListAPI(@PathVariable long boardId) {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        JSONObject result_data = new JSONObject();

        result_data.put("data", chatService.chatListApi(boardId));


        jsonObject.put("result_data", result_data);


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //댓글 등록 API
    @PostMapping("/api/chat/{boardId}/chatAdd")
    public ResponseEntity chatAddAPI( @PathVariable("boardId") long boardId,
                                      @RequestParam("nickname") String nickname,
                                      @RequestParam("password") String password,
                                      @RequestParam("content") String content) {

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkBoardId = b_ipstoreService.CheckBoardId(boardId);

        //api로 요청시 없는 게시글의 댓글리스트 요청시 에러응답 반환
        if(checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 댓글리스트는 찾을수없습니다. 게시글번호를 확인해주세요 ");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }



        if((nickname == null || nickname.trim().isEmpty()) || (content == null || content.trim().isEmpty())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "제목 내용 데이터를 기입해주세요");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }

        chatService.chatCountPlus(boardId);
        chatService.chatAdd(boardId, nickname, password, content);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //댓글 추천 API
    //엉뚱한 게시글번호로 요청시 에러처리 하는방법을 모르겠음..
    @GetMapping("/api/chat/{chatId}/recommend")
    public ResponseEntity chatRecommendAPI(@PathVariable long chatId, HttpServletRequest request, Model model) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkBoardId = c_ipstoreService.CheckChatId(chatId);

        //api로 요청시 없는 게시글로 요청시 에러응답 반환
        if(checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 댓글은 없는 댓글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }


        String ip=request.getHeader("X-Forwarded-For");

        logger.info(">>>>X-FORWARDED-FOR:"+ip);

        if(ip==null){
            ip=request.getHeader("Proxy-Client-IP");
            logger.info(">>>>Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("WL-Proxy-Client-IP");
            logger.info(">>>>WL-Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>>HTTP_CLIENT_IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>>HTTP_X_FORWARDED_FOR:"+ip);
        }
        if(ip==null){
            ip=request.getRemoteAddr();
        }




        //ip 가져오기
        String checkIp = c_ipstoreService.IpSelectQuery(chatId,ip);

        Chat boardIdCheck;


        if(ip.equals(checkIp)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "추천 비추천 기능은 IP당 한번만 가능합니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        } else {
            //데이터베이스에 클라이언트 ip 추가하기
            c_ipstoreService.ipAdd(chatId,ip);
            //추천수 1 증가하기
            chatService.chatRecommend(chatId);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //댓글 비추천 API
    @GetMapping("/api/chat/{chatId}/deprecate")
    public ResponseEntity chatDeprecateAPI(@PathVariable long chatId, HttpServletRequest request, Model model) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkBoardId = c_ipstoreService.CheckChatId(chatId);

        //api로 요청시 없는 게시글로 요청시 에러응답 반환
        if(checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 댓글 번호는 없는 게시글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }


        String ip=request.getHeader("X-Forwarded-For");

        logger.info(">>>>X-FORWARDED-FOR:"+ip);

        if(ip==null){
            ip=request.getHeader("Proxy-Client-IP");
            logger.info(">>>>Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("WL-Proxy-Client-IP");
            logger.info(">>>>WL-Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>>HTTP_CLIENT_IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>>HTTP_X_FORWARDED_FOR:"+ip);
        }
        if(ip==null){
            ip=request.getRemoteAddr();
        }

        //ip 가져오기
        String checkIp = c_ipstoreService.IpSelectQuery(chatId,ip);


        if(ip.equals(checkIp)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "추천 비추천 기능은 IP당 한번만 가능합니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        } else {
            //데이터베이스에 클라이언트 ip 추가하기
            c_ipstoreService.ipAdd(chatId,ip);
            //비추천수 1 증가하기
            chatService.chatDeprecate(chatId);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //댓글 신고 API
    @GetMapping("/api/chat/{chatId}/declaration")
    public ResponseEntity chatDeclarationAPI(@PathVariable long chatId, HttpServletRequest request, Model model) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkBoardId = c_ipstoreService.CheckChatId(chatId);

        //api로 요청시 없는 게시글로 요청시 에러응답 반환
        if(checkBoardId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 게시글 번호는 없는 게시글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }


        String ip=request.getHeader("X-Forwarded-For");

        logger.info(">>>>X-FORWARDED-FOR:"+ip);

        if(ip==null){
            ip=request.getHeader("Proxy-Client-IP");
            logger.info(">>>>Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("WL-Proxy-Client-IP");
            logger.info(">>>>WL-Proxy-Client-IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>>HTTP_CLIENT_IP:"+ip);
        }
        if(ip==null){
            ip=request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>>HTTP_X_FORWARDED_FOR:"+ip);
        }
        if(ip==null){
            ip=request.getRemoteAddr();
        }

        //ip 가져오기
        String checkIp = c_declarationipService.DeclarationIpSelectQuery(chatId, ip);



        if(ip.equals(checkIp)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "신고 기능은 IP당 한번만 가능합니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        } else {
            c_declarationipService.DeclarationIpAdd(chatId,ip);
            chatService.chatDeclaration(chatId);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");
        jsonObject.put("help", "신고기능 5회초과시 게시글 자동 삭제되니 참고 바랍니다.");


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }

    //댓글 삭제 API
    @PostMapping("/api/{boardId}/chat/{chatId}/delete")
    public ResponseEntity chatDeleteAPI(
            @PathVariable("boardId") long boardId,
            @PathVariable("chatId") long chatId, @RequestParam("password") String password){
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        Long checkChatId = c_ipstoreService.CheckChatId(chatId);

        //api로 요청시 없는 게시글로 요청시 에러응답 반환
        if(checkChatId == 999999L) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "요청한 댓글 번호는 없는 댓글입니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }


        Chat passCheckBoard = chatService.chatDetail(chatId);

        if(password.equals(passCheckBoard.getPassword())) {
            chatService.chatCountMinus(boardId);
            chatService.chatDeleteChangeView(chatId);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 0);
            jsonObject.put("message", "success");
            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            jsonObject.put("message", "failure");
            jsonObject.put("error", "비밀번호가 다릅니다.");

            return new ResponseEntity(jsonObject.toString(), header, HttpStatus.NOT_FOUND);
        }
    }

}
