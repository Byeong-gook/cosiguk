package com.coronacommunity.CoronaCommunity.entity;

import com.coronacommunity.CoronaCommunity.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="notice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //글번호 (기본키 Autoincrement)

    private String title; //제목
    private String content; //내용
    private int hit; //조회수
}
