package com.coronacommunity.CoronaCommunity.dto;

import lombok.Data;

@Data
public class InfectionCityDto {
    private String createDt;
    private String gubun;
    private int deathcnt;
    private int defcnt;


    public InfectionCityDto(String createDt, String gubun, int deathcnt, int defcnt) {
        this.createDt = createDt;
        this.gubun = gubun;
        this.deathcnt = deathcnt;
        this.defcnt = defcnt;
    }
}
