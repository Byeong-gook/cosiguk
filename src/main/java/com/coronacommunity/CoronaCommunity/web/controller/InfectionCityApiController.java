package com.coronacommunity.CoronaCommunity.web.controller;

import com.coronacommunity.CoronaCommunity.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


@RequiredArgsConstructor
@Controller
public class InfectionCityApiController {

    @Autowired
    ApiService apiService;

    JSONObject InfectionCity_Obj = null;

    @GetMapping("/api/data/infectionCity")
    public ResponseEntity infectionCityAPI() {
        StringBuffer infection_City_result = new StringBuffer(); // 코로나 시도별 전체 데이터 XML 결과 받아오는 변수

        Calendar calendar = new GregorianCalendar();

        SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

        //날짜 포맷
        String toDay = SDF.format(calendar.getTime());

        //yesterDay 전날 Api 파라미터 요청용 Data
        calendar.add(Calendar.DATE, -1);
        String yesterDay = SDF.format(calendar.getTime());


        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        try {
            String apiUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?" +
                    //application.properties 파일은 저에게 카톡으로 요청해주세요
                    "serviceKey=" + apiService.getStatusApiServiceKey() +
                    "&pageNo=1" +
                    "&numOfRows=10" +
                    "&startCreateDt=" + yesterDay +
                    "&endCreateDt=" + toDay;

            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while ((returnLine = bufferedReader.readLine()) != null) {
                infection_City_result.append(returnLine);
            }

            InfectionCity_Obj = XML.toJSONObject(infection_City_result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(InfectionCity_Obj.toString(),header, HttpStatus.OK);
    }
}