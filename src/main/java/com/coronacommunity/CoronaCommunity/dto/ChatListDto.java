package com.coronacommunity.CoronaCommunity.dto;

import lombok.Data;

@Data
public class ChatListDto {
    private Long id; // 댓글 번호
    private String nickname; // 댓글 작성자
    private String content; // 댓글 내용
    private String createdDate; // 생성일
    private String modifiedDate; // 수정일
    private int board_id;
    private int recommend; // 추천
    private int deprecate; // 비추천
    private int declaration; // 신고

    public ChatListDto(Long id, String nickname, String content, String createdDate, String modifiedDate,
                       int board_id ,int recommend, int deprecate, int declaration) {
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.board_id = board_id;
        this.recommend = recommend;
        this.deprecate = deprecate;
        this.declaration = declaration;
    }

}
