package com.coronacommunity.CoronaCommunity.dto;

import lombok.Data;

@Data
public class AdminPageBoardListDto {
    private Long id; // 게시글 번호
    private String title; // 제목
    private String content; //내용
    private String nickname; // 작성자
    private String password;
    private String created_Date; // 생성일
    private String modified_Date; // 수정일
    private int view;
    private int review_count; // 댓글 갯수
    private int hit; // 조회수

    public AdminPageBoardListDto(Long id, String title, String content, String nickname,
                                 String password, String created_Date, String modified_Date,
                                 int view, int review_count, int hit, int recommend, int deprecate, int declaration) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.password = password;
        this.created_Date = created_Date;
        this.modified_Date = modified_Date;
        this.view = view;
        this.review_count = review_count;
        this.hit = hit;
        this.recommend = recommend;
        this.deprecate = deprecate;
        this.declaration = declaration;
    }

    private int recommend; // 추천
    private int deprecate; // 비추천
    private int declaration; // 신고



}
