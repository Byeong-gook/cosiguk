package com.coronacommunity.CoronaCommunity.service;

import com.coronacommunity.CoronaCommunity.entity.Admin;
import com.coronacommunity.CoronaCommunity.entity.Board;
import com.coronacommunity.CoronaCommunity.entity.Chat;
import com.coronacommunity.CoronaCommunity.entity.Notice;
import com.coronacommunity.CoronaCommunity.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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

    //게시판 전체리스트 API
    public List<Board> boardAdminListApi( ) {
        String jpql = "SELECT b FROM board b ORDER BY b.id DESC";

        TypedQuery<Board> query = em.createQuery(jpql, Board.class);

        List<Board> boardList = query.getResultList();

        return boardList;
    }

    //해당 게시글 댓글리스트 API
    public List<Chat> chatAdminListApi(long boardId) {
        //게시판을 내림차순으로 정렬해서 리스트형식으로 가져옵니다.
        String jpql = "SELECT c FROM chat c where board_id = :boardAddress ORDER BY c.id DESC";
        TypedQuery<Chat> query = em.createQuery(jpql, Chat.class);
        query.setParameter("boardAddress", boardId);

        List<Chat> chatList = query.getResultList();


        return chatList;
    }

}
