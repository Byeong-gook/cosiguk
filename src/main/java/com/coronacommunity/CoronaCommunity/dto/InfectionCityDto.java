package com.coronacommunity.CoronaCommunity.dto;

import lombok.Data;

@Data
public class InfectionCityDto {
    private String createDt;
    private String gubun;
    private int deathCnt;
    private int defCnt;


    public InfectionCityDto(String createDt, String gubun, int deathCnt, int defCnt) {
        this.createDt = createDt;
        this.gubun = gubun;
        this.deathCnt = deathCnt;
        this.defCnt = defCnt;
    }
}