package com.coronacommunity.CoronaCommunity.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name="b_ipstore")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class b_Ipstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키

    private String ip; // 댓글별 클라이언트 ip주소

    //Board 테이블의 id(기본키)를 참조해야함 Ipstore의 외래키명 id_FK
    @ManyToOne
    @JoinColumn(name="board_id", referencedColumnName = "id")
    private Board board;
}
