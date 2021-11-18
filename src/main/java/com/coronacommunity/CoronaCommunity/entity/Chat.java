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
    private Long id; //글번호 (기본키 Autoincrement)

    private String nickname; //닉네임
    private String password; //비밀번호
    private String content; //내용
    private int view; // 삭제 판별용 컬럼 전체리스트 호출시 0 이면 보여주기 1이면 보여주지 않기
    private int declaration; //신고기능
    private int recommend; //추천기능
    private int deprecate; //비추천기능

    @OneToMany(mappedBy = "chat", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<c_Declarationip> c_declarationip;
    @OneToMany(mappedBy = "chat", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<c_Ipstore> c_ipstore;

    @ManyToOne
    @JoinColumn(name="board_id", referencedColumnName = "id")
    private Board board;

}