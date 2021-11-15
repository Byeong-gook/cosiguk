package com.coronacommunity.CoronaCommunity.service;

import com.coronacommunity.CoronaCommunity.entity.Chat;
import com.coronacommunity.CoronaCommunity.entity.c_Ipstore;
import com.coronacommunity.CoronaCommunity.repository.ChatRepository;
import com.coronacommunity.CoronaCommunity.repository.c_IpstoreRepository;
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
public class c_IpstoreService {

    private final EntityManager em;



    @Autowired
    private c_IpstoreRepository c_ipstoreRepository;

    @Autowired
    private ChatRepository chatRepository;


    // IP 저장 메서드 (id = 게시글번호 , ip = client ip)
    @Transactional
    public void ipAdd(Long id, String ip) {
        //게시판 DB에서 게시글번호의 객체를 가져와서
        Chat chat = chatRepository.getById(id);
        em.persist(chat);

        c_Ipstore c_ipstore = new c_Ipstore();
        c_ipstore.setIp(ip);
        c_ipstore.setChat(chat);
        em.persist(c_ipstore);
    }

    //board_id와 클라이언트 ip를 통해 게시글에 저장되있는 ip 데이터 가져오기
    public String IpSelectQuery(long chatId, String ip) throws SQLException {
        String jpql = "SELECT i FROM c_ipstore i where ip = :ipAddress and chat_id = :chatAddress";
        TypedQuery<c_Ipstore> query = em.createQuery(jpql, c_Ipstore.class);
        query.setParameter("ipAddress", ip);
        query.setParameter("chatAddress", chatId);


        List<c_Ipstore> ipList = query.getResultList();

        for (c_Ipstore c_ipstore : ipList) {
            String checkIp = c_ipstore.getIp();

            return checkIp;
        }


        String notIp = "no Ip";
        return notIp;
    }

    //API 요청시 없는 chatId로 요청하면 에러 처리용
    public Long CheckChatId(long chatId) {
        String jpql = "SELECT c FROM chat c where id = :chatAddress";
        TypedQuery<Chat> query = em.createQuery(jpql, Chat.class);
        query.setParameter("chatAddress", chatId);

        List<Chat> chatList = query.getResultList();

        for(Chat chat : chatList) {
            Long checkId = chat.getId();

            return checkId;
        }

        return 999999L;
    }
}


