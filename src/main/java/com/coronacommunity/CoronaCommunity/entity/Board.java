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
    private Long id; //????????? (????????? Autoincrement)

    private String nickname; //?????????
    private String password; //????????????
    private String title; // ??????
    private String content; //??????
    private int review_count; // ?????? ???????????? ?????? ??????
    private int view; // ?????? ????????? ?????? ??????????????? ????????? 0 ?????? ???????????? 1?????? ???????????? ??????
    private int hit; // ?????????
    private int declaration; //????????????
    private int recommend; //????????????
    private int deprecate; //???????????????

    //??????
    @OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Chat> chat;

    //?????? ip ??????
    @OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<b_Declarationip> b_declarationip;
    //??????,????????? ip ??????
    @OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<b_Ipstore> b_ipstore;


}
