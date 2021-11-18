package com.coronacommunity.CoronaCommunity.dto;

import lombok.Data;

@Data
public class BoardDetailDto {
    private Long id; // 게시글 번호
    private String title; // 제목
    private String content; //내용
    private String nickname; // 작성자
    private String createdDate; // 생성일
    private String modifiedDate; // 수정일
    private int hit; // 조회수
    private int recommend; // 추천
    private int deprecate; // 비추천
    private int declaration; // 신고

    public BoardDetailDto(Long id, String title, String content, String nickname,
                          String createdDate, String modifiedDate,
                          int hit, int recommend, int deprecate, int declaration) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.hit = hit;
        this.recommend = recommend;
        this.deprecate = deprecate;
        this.declaration = declaration;
    }
}
