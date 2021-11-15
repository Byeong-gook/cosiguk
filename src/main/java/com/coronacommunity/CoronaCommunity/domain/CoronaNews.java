package com.coronacommunity.CoronaCommunity.domain;

import lombok.Data;

@Data
public class CoronaNews {
    private String title; // 뉴스 제목
    private String originallink; // 뉴스 원본 링크
    private String link; // 뉴스 링크
    private String description; // 뉴스 내용
    private String pubDate; // 날짜
}
