package com.coronacommunity.CoronaCommunity.domain;

import lombok.Data;

@Data
public class InfectionStatus {
    private String DECIDE_CNT; // 확진자수
    private String CLEAR_CNT; // 격리해제 수
    private String DEATH_CNT; // 사망자수
    private String ACC_EXAM_CNT; //누적검사 수
    private String EXAM_CNT; // 검사중
    private String RESULT_NEG_CNT; // 결과 음성 수
    private String T_DECIDE_CNT; // 일일 확진자 수
    private String T_CLEAR_CNT; // 일일 격리해제 수
    private String T_DEATH_CNT; // 일일 사망자 수


}
