package com.coronacommunity.CoronaCommunity.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="c_declarationip")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class c_Declarationip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키

    private String ip; //신고별 클라이언트 ip

    //Board 테이블의 id(기본키)를 참조해야함 Declarationip 의 외래키명 id_FK
    @ManyToOne
    @JoinColumn(name="chat_id", referencedColumnName = "id")
    private Chat chat;
}
