package com.coronacommunity.CoronaCommunity.entity;

import com.coronacommunity.CoronaCommunity.domain.BaseTimeEntity;
import com.coronacommunity.CoronaCommunity.dto.AdminPageBoardListDto;
import com.coronacommunity.CoronaCommunity.dto.BoardDetailDto;
import com.coronacommunity.CoronaCommunity.dto.BoardListDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name="board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SqlResultSetMapping(
        name="BoardDetailDtoMapping",
        classes = @ConstructorResult(
                targetClass = BoardDetailDto.class,
                columns = {
                        @ColumnResult(name="id", type=Long.class),
                        @ColumnResult(name="title", type=String.class),
                        @ColumnResult(name="content", type=String.class),
                        @ColumnResult(name="nickname", type=String.class),
                        @ColumnResult(name="createdDate", type=String.class),
                        @ColumnResult(name="modifiedDate", type=String.class),
                        @ColumnResult(name="hit", type=Integer.class),
                        @ColumnResult(name="recommend", type=Integer.class),
                        @ColumnResult(name="deprecate", type=Integer.class),
                        @ColumnResult(name="declaration", type=Integer.class)
                })
)
@SqlResultSetMapping(
        name="BoardListDtoMapping",
        classes = @ConstructorResult(
                targetClass = BoardListDto.class,
                columns = {
                        @ColumnResult(name="id", type=Long.class),
                        @ColumnResult(name="title", type=String.class),
                        @ColumnResult(name="content", type=String.class),
                        @ColumnResult(name="nickname", type=String.class),
                        @ColumnResult(name="createdDate", type=String.class),
                        @ColumnResult(name="modifiedDate", type=String.class),
                        @ColumnResult(name="review_count", type=Integer.class),
                        @ColumnResult(name="hit", type=Integer.class),
                        @ColumnResult(name="recommend", type=Integer.class),
                        @ColumnResult(name="deprecate", type=Integer.class),
                        @ColumnResult(name="declaration", type=Integer.class)
                })
)
@SqlResultSetMapping(
        name="AdminPageBoardListDtoMapping",
        classes = @ConstructorResult(
                targetClass = AdminPageBoardListDto.class,
                columns = {
                        @ColumnResult(name="id", type=Long.class),
                        @ColumnResult(name="title", type=String.class),
                        @ColumnResult(name="content", type=String.class),
                        @ColumnResult(name="nickname", type=String.class),
                        @ColumnResult(name= "password", type=String.class),
                        @ColumnResult(name="created_Date", type=String.class),
                        @ColumnResult(name="modified_Date", type=String.class),
                        @ColumnResult(name="view", type=Integer.class),
                        @ColumnResult(name="review_count", type=Integer.class),
                        @ColumnResult(name="hit", type=Integer.class),
                        @ColumnResult(name="recommend", type=Integer.class),
                        @ColumnResult(name="deprecate", type=Integer.class),
                        @ColumnResult(name="declaration", type=Integer.class)
                })
)
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //글번호 (기본키 Autoincrement)

    private String nickname; //닉네임
    private String password; //비밀번호
    private String title; // 제목
    private String content; //내용
    private int review_count; // 해당 게시글의 댓글 개수
    private int view; // 삭제 판별용 컬럼 전체리스트 호출시 0 이면 보여주기 1이면 보여주지 않기
    private int hit; // 조회수
    private int declaration; //신고기능
    private int recommend; //추천기능
    private int deprecate; //비추천기능

    //댓글
    @OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Chat> chat;

    //신고 ip 체크
    @OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<b_Declarationip> b_declarationip;
    //추천,비추천 ip 체크
    @OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<b_Ipstore> b_ipstore;


}
