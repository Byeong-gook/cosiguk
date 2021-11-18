package com.coronacommunity.CoronaCommunity.service;

import com.coronacommunity.CoronaCommunity.dto.AdminPageBoardListDto;
import com.coronacommunity.CoronaCommunity.dto.AdminPageChatListDto;
import com.coronacommunity.CoronaCommunity.dto.BoardListDto;
import com.coronacommunity.CoronaCommunity.dto.ChatListDto;
import com.coronacommunity.CoronaCommunity.entity.Admin;
import com.coronacommunity.CoronaCommunity.entity.Board;
import com.coronacommunity.CoronaCommunity.entity.Chat;
import com.coronacommunity.CoronaCommunity.entity.Notice;
import com.coronacommunity.CoronaCommunity.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final EntityManager em;

    @Autowired
    AdminRepository adminRepository;


    // 관리자 계정정보 불러오기
    public Admin adminGetInformation(Long id) {
        return adminRepository.findById(id).get();
    }

  /*  //게시판 전체리스트 API
    public List<Board> boardAdminListApi( ) {
        String jpql = "SELECT b FROM board b ORDER BY b.id DESC";

        TypedQuery<Board> query = em.createQuery(jpql, Board.class);

        List<Board> boardList = query.getResultList();

        return boardList;
    }*/

    public List<AdminPageBoardListDto> boardAdminListApi() {
        String q = "SELECT b.id,b.title,b.content, b.nickname, b.password," +
                "b.created_date,b.modified_date,b.view,b.review_count,b.hit," +
                "b.recommend,b.deprecate, b.declaration" +
                " FROM board b ORDER BY b.id DESC";

        Query query = em.createNativeQuery(q, "AdminPageBoardListDtoMapping");
        List<AdminPageBoardListDto> adminBoardList = query.getResultList();

        return adminBoardList;
    }

    //해당 게시글 댓글리스트 API
    public List<AdminPageChatListDto> chatAdminListApi(long boardId) {

        String q = "SELECT c.id, c.nickname, c.password, c.content, c.created_date AS createdDate, c.modified_date AS modifiedDate,"
                + "c.view,c.board_id ,c.recommend, c.deprecate, c.declaration " +
                "FROM chat c where board_id = :boardAddress ORDER BY c.id DESC";
        Query query = em.createNativeQuery(q, "AdminPageChatListDtoMapping");
        query.setParameter("boardAddress", boardId);
        List<AdminPageChatListDto> chatList = query.getResultList();

        return chatList;

    }

}
