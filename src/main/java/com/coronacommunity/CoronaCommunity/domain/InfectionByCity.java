package com.coronacommunity.CoronaCommunity.domain;


import lombok.Data;

@Data
public class InfectionByCity {
    // [검역]
    private String quarantineGubun; //시도명(검역)
    private String quarantineIsol_Ing_Cnt; //격리중(검역)
    private String quarantinedefCnt; //확진자(검역)
    private String quarantineincDec; // 전일대비 증감수(검역)
    private String quarantineisolClearCnt; // 격리해제수(검역)
    private String quarantinedeathCnt; // 사망자수(검역)

    // [제주]
    private String JejuGubun; //시도명(제주)
    private String JejuIsol_Ing_Cnt; //격리중(제주)
    private String JejudefCnt; //확진자(제주)
    private String JejuincDec; // 전일대비 증감수(제주)
    private String JejuisolClearCnt; // 격리해제수(제주)
    private String JejudeathCnt; // 사망자수(제주)

    // [경남]
    private String GyeongnamGubun; //시도명(경남)
    private String GyeongnamIsol_Ing_Cnt; //격리중(경남)
    private String GyeongnamdefCnt; //확진자(경남)
    private String GyeongnamincDec; // 전일대비 증감수(경남)
    private String GyeongnamisolClearCnt; // 격리해제수(경남)
    private String GyeongnamdeathCnt; // 사망자수(경남)

    // [경북]
    private String GyeongbukGubun; //시도명(경북)
    private String GyeongbukIsol_Ing_Cnt; //격리중(경북)
    private String GyeongbukdefCnt; //확진자(경북)
    private String GyeongbukincDec; // 전일대비 증감수(경북)
    private String GyeongbukisolClearCnt; // 격리해제수(경북)
    private String GyeongbukdeathCnt; // 사망자수(경북)

    // [전남]
    private String JeonnamGubun; //시도명(전남)
    private String JeonnamIsol_Ing_Cnt; //격리중(전남)
    private String JeonnamdefCnt; //확진자(전남)
    private String JeonnamincDec; // 전일대비 증감수(전남)
    private String JeonnamisolClearCnt; // 격리해제수(전남)
    private String JeonnamdeathCnt; // 사망자수(전남)

    // [전북]
    private String JeonbukGubun; //시도명(전북)
    private String JeonbukIsol_Ing_Cnt; //격리중(전북)
    private String JeonbukdefCnt; //확진자(전북)
    private String JeonbukincDec; // 전일대비 증감수(전북)
    private String JeonbukisolClearCnt; // 격리해제수(전북)
    private String JeonbukdeathCnt; // 사망자수(전북)

    // [충남]
    private String ChungnamGubun; //시도명(충남)
    private String ChungnamIsol_Ing_Cnt; //격리중(충남)
    private String ChungnamdefCnt; //확진자(충남)
    private String ChungnamincDec; // 전일대비 증감수(충남)
    private String ChungnamisolClearCnt; // 격리해제수(충남)
    private String ChungnamdeathCnt; // 사망자수(충남)

    // [충북]
    private String ChungbukGubun; //시도명(충북)
    private String ChungbukIsol_Ing_Cnt; //격리중(충북)
    private String ChungbukdefCnt; //확진자(충북)
    private String ChungbukincDec; // 전일대비 증감수(충북)
    private String ChungbukisolClearCnt; // 격리해제수(충북)
    private String ChungbukdeathCnt; // 사망자수(충북)

    // [강원]
    private String GangwonGubun; //시도명(강원)
    private String GangwonIsol_Ing_Cnt; //격리중(강원)
    private String GangwondefCnt; //확진자(강원)
    private String GangwonincDec; // 전일대비 증감수(강원)
    private String GangwonisolClearCnt; // 격리해제수(강원)
    private String GangwondeathCnt; // 사망자수(강원)


    // [경기]
    private String GyeonggiGubun; //시도명(경기)
    private String GyeonggiIsol_Ing_Cnt; //격리중(경기)
    private String GyeonggidefCnt; //확진자(경기)
    private String GyeonggiincDec; // 전일대비 증감수(경기)
    private String GyeonggiisolClearCnt; // 격리해제수(경기)
    private String GyeonggideathCnt; // 사망자수(경기)

    // [세종]

    private String SejongGubun; //시도명(세종)
    private String SejongIsol_Ing_Cnt; //격리중(세종)
    private String SejongdefCnt; //확진자(세종)
    private String SejongincDec; // 전일대비 증감수(세종)
    private String SejongisolClearCnt; // 격리해제수(세종)
    private String SejongdeathCnt; // 사망자수(세종)

    // [울산]

    private String UlsanGubun; //시도명(울산)
    private String UlsanIsol_Ing_Cnt; //격리중(울산)
    private String UlsandefCnt; //확진자(울산)
    private String UlsanincDec; // 전일대비 증감수(울산)
    private String UlsanisolClearCnt; // 격리해제수(울산)
    private String UlsandeathCnt; // 사망자수(울산)

    // [대전]

    private String DaejeonGubun; //시도명(대전)
    private String DaejeonIsol_Ing_Cnt; //격리중(대전)
    private String DaejeondefCnt; //확진자(대전)
    private String DaejeonincDec; // 전일대비 증감수(대전)
    private String DaejeonisolClearCnt; // 격리해제수(대전)
    private String DaejeondeathCnt; // 사망자수(대전)

    // [광주]

    private String GwangjuGubun; //시도명(광주)
    private String GwangjuIsol_Ing_Cnt; //격리중(광주)
    private String GwangjudefCnt; //확진자(광주)
    private String GwangjuincDec; // 전일대비 증감수(광주)
    private String GwangjuisolClearCnt; // 격리해제수(광주)
    private String GwangjudeathCnt; // 사망자수(광주)

    // [인천]

    private String IncheonGubun; //시도명(인천)
    private String IncheonIsol_Ing_Cnt; //격리중(인천)
    private String IncheondefCnt; //확진자(인천)
    private String IncheonincDec; // 전일대비 증감수(인천)
    private String IncheonisolClearCnt; // 격리해제수(인천)
    private String IncheondeathCnt; // 사망자수(인천)

    // [대구]

    private String DaeguGubun; //시도명(대구)
    private String DaeguIsol_Ing_Cnt; //격리중(대구)
    private String DaegudefCnt; //확진자(대구)
    private String DaeguincDec; // 전일대비 증감수(대구)
    private String DaeguisolClearCnt; // 격리해제수(대구)
    private String DaegudeathCnt; // 사망자수(대구)

    // [부산]

    private String BusanGubun; //시도명(부산)
    private String BusanIsol_Ing_Cnt; //격리중(부산)
    private String BusandefCnt; //확진자(부산)
    private String BusanincDec; // 전일대비 증감수(부산)
    private String BusanisolClearCnt; // 격리해제수(부산)
    private String BusandeathCnt; // 사망자수(부산)

    // [서울]

    private String SeoulGubun; //시도명(서울)
    private String SeoulIsol_Ing_Cnt; //격리중(서울)
    private String SeouldefCnt; //확진자(서울)
    private String SeoulincDec; // 전일대비 증감수(서울)
    private String SeoulisolClearCnt; // 격리해제수(서울)
    private String SeouldeathCnt; // 사망자수(서울)

    // [합계]

    private String SumGubun; //시도명(합계)
    private String SumIsol_Ing_Cnt; //격리중(합계)
    private String SumdefCnt; //확진자(합계)
    private String SumincDec; // 전일대비 증감수(합계)
    private String SumisolClearCnt; // 격리해제수(합계)
    private String SumdeathCnt; // 사망자수(합계)




}
