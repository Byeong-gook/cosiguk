package com.coronacommunity.CoronaCommunity.entity;


import lombok.*;

import javax.persistence.*;

@Entity(name="b_declarationip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class b_Declarationip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키

    private String ip; //신고별 클라이언트 ip

    //Board 테이블의 id(기본키)를 참조해야함 Declarationip 의 외래키명 id_FK
    @ManyToOne
    @JoinColumn(name="board_id", referencedColumnName = "id")
    private Board board;
}
