package com.coronacommunity.CoronaCommunity.service;

import com.coronacommunity.CoronaCommunity.entity.*;
import com.coronacommunity.CoronaCommunity.repository.BoardRepository;
import com.coronacommunity.CoronaCommunity.repository.b_IpstoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class b_DeclarationipService {

    private final EntityManager em;



    @Autowired
    private b_IpstoreRepository b_ipstoreRepository;

    @Autowired
    private BoardRepository boardRepository;


    // IP 저장 메서드 (id = 게시글번호 , ip = client ip)
    @Transactional
    public void ipAdd(Long id, String ip) {
        //게시판 DB에서 게시글번호의 객체를 가져와서
        Board board = boardRepository.getById(id);
        em.persist(board);

        b_Declarationip b_declarationip = new b_Declarationip();
        b_declarationip.setIp(ip);
        b_declarationip.setBoard(board);
        em.persist(b_declarationip);
    }

    //board_id와 클라이언트 ip를 통해 게시글에 저장되있는 ip 데이터 가져오기
    public String IpSelectQuery(long boardId, String ip) throws SQLException {
        String jpql = "SELECT i FROM b_declarationip i where ip = :ipAddress and board_id = :boardAddress";
        TypedQuery<b_Declarationip> query = em.createQuery(jpql, b_Declarationip.class);
        query.setParameter("ipAddress", ip);
        query.setParameter("boardAddress", boardId);


        List<b_Declarationip> ipList = query.getResultList();

        for (b_Declarationip b_declarationip : ipList) {
            String checkIp = b_declarationip.getIp();

            return checkIp;
        }


        String notIp = "no Ip";
        return notIp;
    }

    //API 요청시 없는 chatId로 요청하면 에러 처리용
    public Long CheckBoardId(long boardId) {
        String jpql = "SELECT b FROM board b where id = :boardAddress";
        TypedQuery<Board> query = em.createQuery(jpql, Board.class);
        query.setParameter("boardAddress", boardId);

        List<Board> boardList = query.getResultList();

        for(Board board : boardList) {
            Long checkId = board.getId();

            return checkId;
        }

        return 999999L;
    }
}
