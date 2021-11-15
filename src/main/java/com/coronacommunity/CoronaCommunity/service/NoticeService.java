package com.coronacommunity.CoronaCommunity.service;

import com.coronacommunity.CoronaCommunity.entity.Notice;
import com.coronacommunity.CoronaCommunity.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;



@RequiredArgsConstructor
@Service
public class NoticeService {

    private final EntityManager em;

    @Autowired
    private NoticeRepository noticeRepository;

    // 공지사항 작성 처리 (putMapping + HidenHttpMethodFilter 등을 활용하여 수정도 NoticeAdd 메서드를 통해 처리한다.)
    public void noticeAdd(Notice notice) {

        noticeRepository.save(notice);
    }


    //공지사항 전체 리스트 처리
    public Page<Notice> noticeList(Pageable pageable) {
        return noticeRepository.findAll(pageable);

    }

    //공지사항 리스트 처리 API
    public List<Notice> noticeListApi( ) {
        String jpql = "SELECT n FROM notice n ORDER BY n.id DESC";
        TypedQuery<Notice> query = em.createQuery(jpql, Notice.class);
        List<Notice> noticeList = query.getResultList();

        return noticeList;
    }


    // 특정 공지사항 불러오기
    public Notice noticeDetail(Long id) {
        // 조회수 처리
        Notice findBoard = noticeRepository.findById(id).get();
        findBoard.setHit(findBoard.getHit()+1);
        noticeRepository.save(findBoard);


        return noticeRepository.findById(id).get();
    }

    //특정 공지사항 수정하기
    public void noticeUpdate(Long id, Notice notice) {
        noticeRepository.save(notice);
    }


    // 특정 공지사항 삭제
    public void noticeDelete(Long id) {
        noticeRepository.deleteById(id);
    }

}
