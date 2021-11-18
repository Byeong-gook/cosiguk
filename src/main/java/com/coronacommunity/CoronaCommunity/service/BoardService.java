package com.coronacommunity.CoronaCommunity.service;


import com.coronacommunity.CoronaCommunity.dto.BoardDetailDto;
import com.coronacommunity.CoronaCommunity.dto.BoardListDto;
import com.coronacommunity.CoronaCommunity.entity.Board;
import com.coronacommunity.CoronaCommunity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final EntityManager em;


    @Autowired
    BoardRepository boardRepository;


    //게시판 전체리스트 가져오기
    //게시판을 내림차순으로 정렬해서 리스트형식으로 가져옵니다.

/*    //게시판 전체리스트 API
    public List<Board> boardListApi( ) {
        int viewValue = 0;
        String jpql = "SELECT b FROM board b where view = :viewValue ORDER BY b.id DESC";

*//*
        서브쿼리문
        String jpql = "SELECT b, (SELECT COUNT(board_id) FROM chat WHERE board_id = b.id)" +
                " AS review_count FROM board b WHERE b.view = :viewValue ORDER BY b.id DESC";
*//*

        //쿼리의 반환형타입이 2개이상이라서 TypedQuery 대신에 Query를 써주면됨
        TypedQuery<Board> query = em.createQuery(jpql, Board.class);
        query.setParameter("viewValue", viewValue);

        List<Board> boardList = query.getResultList();

        return boardList;
    }*/


    //게시판 전체리스트 API
    public List<BoardListDto> boardListApi( ) {
        int viewValue = 0; // 게시판 view 컬럼의 값이 0인 게시글만 조회

        String q = "SELECT b.id, b.title, b.content,b.nickname,b.created_date AS createdDate," +
                "b.modified_date AS modifiedDate, b.review_count, b.hit, b.recommend, " +
                "b.deprecate, b.declaration FROM board b where view = :viewValue ORDER BY b.id DESC";

        Query query = em.createNativeQuery(q, "BoardListDtoMapping" );
        query.setParameter("viewValue", viewValue);
        List<BoardListDto> boardList = query.getResultList();

        return boardList;
    }


    // 게시판 작성 처리 (putMapping + HidenHttpMethodFilter 등을 활용하여 수정도 NoticeAdd 메서드를 통해 처리한다.)
    public void boardAdd(Board board) {
        boardRepository.save(board);
    }

    // 단일 게시판 불러오기
    public BoardDetailDto boardDetail(Long id) {
/*        // 조회수 처리
        Board findBoard = boardRepository.findById(id).get();
        findBoard.setHit(findBoard.getHit()+1);
        boardRepository.save(findBoard);


        return boardRepository.findById(id).get();*/

        String q = "SELECT b.id, b.title, b.content,b.nickname,b.created_date AS createdDate,b.modified_date " +
                "AS modifiedDate, b.hit, b.recommend, b.deprecate, b.declaration FROM board b " +
                "where b.view = 0 AND b.id = :idValue";
        Query query = em.createNativeQuery(q, "BoardDetailDtoMapping");
        query.setParameter("idValue", id);
        BoardDetailDto boardDetail = (BoardDetailDto) query.getSingleResult();

        //게시글 단일조회 할시에 조회수 1증가 로직
        Board findBoard = boardRepository.findById(id).get();
        findBoard.setHit(findBoard.getHit()+1);
        boardRepository.save(findBoard);


        return boardDetail;
    }

    //단일 게시판 API 처리용
    public Board boardDetailApi(Long id) {
        Board findBoard = boardRepository.findById(id).get();
        findBoard.setHit(findBoard.getHit()+1);
        boardRepository.save(findBoard);


        return boardRepository.findById(id).get();
    }

    // 단일 게시판 삭제
    public void boardDelete(Long id) {

        boardRepository.deleteById(id);
    }


    //게시판 추천 기능
    public void boardRecommend(Long id) {
        Board findBoard = boardRepository.findById(id).get();
        //추천기능 +1 증가
        findBoard.setRecommend(findBoard.getRecommend()+1);
        boardRepository.save(findBoard);
    }

    //게시판 비추천 기능
    public void boardDeprecate(Long id) {
        Board findBoard = boardRepository.findById(id).get();
        //비추천기능 +1
        findBoard.setDeprecate(findBoard.getDeprecate()+1);
        boardRepository.save(findBoard);
    }

    //게시판 신고 기능
    public void boardDeclaration(Long id) {
        Board findBoard = boardRepository.findById(id).get();

        //(신고 누적횟수 5회 초과시 게시글삭제기능 구현)
        if(findBoard.getDeclaration() > 5) {
            findBoard.setView(1);
            boardRepository.save(findBoard);
        } else {
            //신고기능 +1 증가
            findBoard.setDeclaration(findBoard.getDeclaration() + 1);
            boardRepository.save(findBoard);
        }
    }

    // 게시판 삭제 요청시 chat 테이블의 view 컬럼값 0 에서 1로 변경
    // 이유 : 데이터를 삭제 하지않고 보존하기위해서
    public void boardDeleteChangeView(Long id) {
        Board findBoard = boardRepository.findById(id).get();

         //View 값을 1로 변경
        findBoard.setView(1);
        boardRepository.save(findBoard);
    }


}
