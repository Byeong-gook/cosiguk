package com.coronacommunity.CoronaCommunity.service;

import com.coronacommunity.CoronaCommunity.entity.Chat;
import com.coronacommunity.CoronaCommunity.entity.c_Declarationip;
import com.coronacommunity.CoronaCommunity.repository.ChatRepository;
import com.coronacommunity.CoronaCommunity.repository.c_DeclarationipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Service
public class c_DeclarationipService {
    private final EntityManager em;


    @Autowired
    private c_DeclarationipRepository c_declarationipRepository;

    @Autowired
    private ChatRepository chatRepository;


    // IP 저장 메서드 (id = 게시글번호 , ip = client ip)
    @Transactional
    public void DeclarationIpAdd(Long id, String ip) {
        //게시판 DB에서 게시글번호의 객체를 가져와서
        Chat chat = chatRepository.getById(id);
        em.persist(chat);

        c_Declarationip c_declarationip = new c_Declarationip();

        c_declarationip.setIp(ip);
        c_declarationip.setChat(chat);
        em.persist(c_declarationip);
    }

    public String DeclarationIpSelectQuery(long chatId, String ip) throws Exception {
        String jpql = "SELECT d FROM c_declarationip d where ip = :ipAddress and chat_id = :chatAddress";
        TypedQuery<c_Declarationip> query = em.createQuery(jpql, c_Declarationip.class);
        query.setParameter("ipAddress", ip);
        query.setParameter("chatAddress", chatId);


            List<c_Declarationip> ipList = query.getResultList();


            for (c_Declarationip c_declarationip : ipList) {
                String checkIp = c_declarationip.getIp();

                return checkIp;
            }



        String notIp = "no Ip";
        return notIp;
    }
}
