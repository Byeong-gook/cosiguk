package com.coronacommunity.CoronaCommunity.entity;


import com.coronacommunity.CoronaCommunity.domain.BaseTimeEntity;
import com.coronacommunity.CoronaCommunity.dto.AdminPageChatListDto;
import com.coronacommunity.CoronaCommunity.dto.ChatListDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name="chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SqlResultSetMapping(
        name="ChatListDtoMapping",
        classes = @ConstructorResult(
                targetClass = ChatListDto.class,
                columns = {
                        @ColumnResult(name="id", type=Long.class),
                        @ColumnResult(name="nickname", type=String.class),
                        @ColumnResult(name="content", type=String.class),
                        @ColumnResult(name="createdDate", type=String.class),
                        @ColumnResult(name="modifiedDate", type=String.class),
                        @ColumnResult(name="board_id", type=Integer.class),
                        @ColumnResult(name="recommend", type=Integer.class),
                        @ColumnResult(name="deprecate", type=Integer.class),
                        @ColumnResult(name="declaration", type=Integer.class)
                })
)
@SqlResultSetMapping(
        name="AdminPageChatListDtoMapping",
        classes = @ConstructorResult(
                targetClass = AdminPageChatListDto.class,
                columns = {
                        @ColumnResult(name="id", type=Long.class),
                        @ColumnResult(name="nickname", type=String.class),
                        @ColumnResult(name="password", type=String.class),
                        @ColumnResult(name="content", type=String.class),
                        @ColumnResult(name="createdDate", type=String.class),
                        @ColumnResult(name="modifiedDate", type=String.class),
                        @ColumnResult(name="view", type=Integer.class),
                        @ColumnResult(name="board_id", type=Integer.class),
                        @ColumnResult(name="recommend", type=Integer.class),
                        @ColumnResult(name="deprecate", type=Integer.class),
                        @ColumnResult(name="declaration", type=Integer.class)
                })
)
public class Chat extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //????????? (????????? Autoincrement)

    private String nickname; //?????????
    private String password; //????????????
    private String content; //??????
    private int view; // ?????? ????????? ?????? ??????????????? ????????? 0 ?????? ???????????? 1?????? ???????????? ??????
    private int declaration; //????????????
    private int recommend; //????????????
    private int deprecate; //???????????????

    @OneToMany(mappedBy = "chat", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<c_Declarationip> c_declarationip;
    @OneToMany(mappedBy = "chat", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<c_Ipstore> c_ipstore;

    @ManyToOne
    @JoinColumn(name="board_id", referencedColumnName = "id")
    private Board board;

}