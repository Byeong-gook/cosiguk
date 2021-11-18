package com.coronacommunity.CoronaCommunity.dto;

import lombok.Data;

@Data
public class AdminPageChatListDto {

    private Long id; // 댓글 번호
    private String nickname; // 댓글 작성자
    private String password; // 댓글 비밀번호
    private String content; // 댓글 내용
    private String createdDate; // 생성일
    private String modifiedDate; // 수정일
    private int view; // view 0이면 댓글 노출 view 1일시 댓글 안보이게 (데이터베이스에서는 살아있음)
    private int board_id; // 게시판 번호(외래키)
    private int recommend; // 추천
    private int deprecate; // 비추천
    private int declaration; // 신고

    public AdminPageChatListDto(Long id, String nickname, String password, String content,
                                String createdDate, String modifiedDate,
                                int view, int board_id, int recommend, int deprecate, int declaration) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.view = view;
        this.board_id = board_id;
        this.recommend = recommend;
        this.deprecate = deprecate;
        this.declaration = declaration;
    }
}
