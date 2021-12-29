package com.coronacommunity.CoronaCommunity.entity;

import com.coronacommunity.CoronaCommunity.dto.InfectionCityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="infectioncity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
        name="InfectionCityDtoMapping",
        classes = @ConstructorResult(
                targetClass = InfectionCityDto.class,
                columns = {
                        @ColumnResult(name="createDt", type=String.class),
                        @ColumnResult(name="gubun", type=String.class),
                        @ColumnResult(name="deathcnt", type=Integer.class),
                        @ColumnResult(name="defcnt", type=Integer.class)
                })
)
public class InfectionCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키

    String createdt; //생성일
    String gubun; // 구분 (대구 , 부산 , 울산.. 등등)
    int deathcnt; // 사망자수
    int defcnt; // 확진자수
}
