package com.coronacommunity.CoronaCommunity.service;


import com.coronacommunity.CoronaCommunity.dto.ChatListDto;
import com.coronacommunity.CoronaCommunity.entity.Board;
import com.coronacommunity.CoronaCommunity.entity.Chat;
import com.coronacommunity.CoronaCommunity.repository.BoardRepository;
import com.coronacommunity.CoronaCommunity.repository.ChatRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final EntityManager em;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private BoardRepository boardRepository;

    // 댓글 작성 처리 (putMapping + HidenHttpMethodFilter 등을 활용하여 수정도 NoticeAdd 메서드를 통해 처리한다.)
    @Transactional
    public void chatAdd(long id, String nickname, String password, String content) {
        Board board = boardRepository.getById(id);
        em.persist(board);


        Chat chat = new Chat();
        chat.setNickname(nickname);
        chat.setPassword(password);
        chat.setContent(content);
        chat.setBoard(board);
        em.persist(chat);
    }



    //댓글 전체리스트 불러오기
    public Page<Chat> chatList(Pageable pageable) {
        return chatRepository.findAll(pageable);

    }


  /*  //해당 게시글 댓글 전체리스트 API
    public List<Chat> chatListApi(long boardId) {
        int viewValue = 0;
        //게시판을 내림차순으로 정렬해서 리스트형식으로 가져옵니다.
        String jpql = "SELECT c FROM chat c where board_id = :boardAddress and view = :viewValue ORDER BY c.id DESC";
        TypedQuery<Chat> query = em.createQuery(jpql, Chat.class);
        query.setParameter("boardAddress", boardId);
        query.setParameter("viewValue", viewValue);

        List<Chat> chatList = query.getResultList();


        return chatList;
    }*/

    //해당 게시글 댓글 전체리스트 API
    public List<ChatListDto> chatListApi(long boardId) {
        int viewValue = 0; // 댓글 view 컬럼의 값이 0인 댓글만 조회

        String q = "SELECT c.id, c.nickname, c.content, c.created_date AS createdDate, c.modified_date AS modifiedDate," +
                "c.board_id ,c.recommend, c.deprecate, c.declaration " +
                "FROM chat c where board_id = :boardAddress and view = :viewValue ORDER BY c.id DESC";
        Query query = em.createNativeQuery(q, "ChatListDtoMapping");
        query.setParameter("boardAddress", boardId);
        query.setParameter("viewValue", viewValue);

        List<ChatListDto> chatList = query.getResultList();

        return chatList;
    }


    //댓글 추천 기능
    public void chatRecommend(Long id) {
        Chat findChat = chatRepository.findById(id).get();
        //추천기능 +1 증가
        findChat.setRecommend(findChat.getRecommend()+1);
        chatRepository.save(findChat);
    }

    //댓글 작성시 댓글 수 증가
    public void chatCountPlus(Long id) {
        Board findBoard = boardRepository.findById(id).get();
        //댓글 수 +1 증가
        findBoard.setReview_count(findBoard.getReview_count()+1);
        boardRepository.save(findBoard);
    }

    //댓글 작성시 댓글 수 감소
    public void chatCountMinus(Long id) {
        Board findBoard = boardRepository.findById(id).get();
        //댓글 수 +1 증가
        findBoard.setReview_count(findBoard.getReview_count()-1);
        boardRepository.save(findBoard);
    }

    //댓글 비추천 기능
    public void chatDeprecate(Long id) {
        Chat findChat = chatRepository.findById(id).get();
        //비추천기능 +1
        findChat.setDeprecate(findChat.getDeprecate()+1);
        chatRepository.save(findChat);
    }

    //댓글 신고 기능
    public void chatDeclaration(Long id) {
        Chat findChat = chatRepository.findById(id).get();

        //(신고 누적횟수 5회 초과시 게시글삭제기능 구현)
        if(findChat.getDeclaration() > 5) {
            chatRepository.deleteById(id);
        } else {
            //신고기능 +1 증가
            findChat.setDeclaration(findChat.getDeclaration() + 1);
            chatRepository.save(findChat);
        }
    }

    // 특정 댓글 불러오기
    public Chat chatDetail(Long id) {

        return chatRepository.findById(id).get();
    }



    // 특정 댓글 삭제
    public void chatDelete(Long id) {

        chatRepository.deleteById(id);
    }

    // 댓글 삭제 요청시 chat 테이블의 view 컬럼값 0 에서 1로 변경
    // 이유 : 데이터를 삭제 하지않고 보존하기위해서
    public void chatDeleteChangeView(Long id) {
        Chat findChat = chatRepository.findById(id).get();
        // View 값을 1로 변경
        // 이유 : 데이터를 삭제 하지않고 보존하기위해서
        findChat.setView(1);
        chatRepository.save(findChat);

    }
}
