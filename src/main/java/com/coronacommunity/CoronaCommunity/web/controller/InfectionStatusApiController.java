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
import org.springframework.stereotype.Service;
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
public class InfectionStatusApiController {
    JSONObject InfectionStatus_Obj1 = null; //totalCount 2일때 데이터 json형태로 받아오는 객체
    JSONObject InfectionStatus_Obj2 = null; //totalCOunt 1일때 데이터 json형태로 받아오는 객체


    @Autowired
    ApiService apiService;




    @GetMapping("/api/data/infectionStatus")
    public ResponseEntity infectionStatusAPI() {
        HttpHeaders header=new HttpHeaders();
        header.add("Content-Type","application/json;charset=UTF-8");

        StringBuffer infection_Status_result = new StringBuffer(); // 코로나 일일 및 전체 데이터 XML 결과 받아오는 변수
        StringBuffer infection_StatusResultNull = new StringBuffer();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

        //날짜 포맷
        String toDay = SDF.format(calendar.getTime());

        //yesterDay 전날 Api 파라미터 요청용 Data
        calendar.add(Calendar.DATE, -1);
        String yesterDay = SDF.format(calendar.getTime());

        //yesterDayEve 그저께 Api 파라미터 요청용 Data
        calendar.add(Calendar.DATE, -2);
        String yesterDayEve = SDF.format(calendar.getTime());

        try {
            String apiUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?" +
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
                infection_Status_result.append(returnLine);
            }


            InfectionStatus_Obj1 = XML.toJSONObject(infection_Status_result.toString());
            JSONObject parse_response = (JSONObject) InfectionStatus_Obj1.get("response");
            JSONObject parse_body = (JSONObject) parse_response.get("body");
            String totalCount = parse_body.get("totalCount").toString();
            //totalCount 1이면 공공데이터 API상에서 당일집계 업데이트 중이므로.. 조회파라미터를 그저께 + 전날
            //totalCOunt 2이면 공공데이터 API상에서 정상데이터가 넘어오는것이므로.. 조회파라미터를 어제 + 당일로..

            if (!totalCount.equals("2")) {
                String apiUrlNull = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?" +
                        "serviceKey=" + apiService.getStatusApiServiceKey() +
                        "&pageNo=1" +
                        "&numOfRows=10" +
                        "&startCreateDt=" + yesterDayEve +
                        "&endCreateDt=" + yesterDay;

                URL urlNull = new URL(apiUrlNull);
                HttpURLConnection urlConnectionNull = (HttpURLConnection) urlNull.openConnection();
                urlConnectionNull.connect();
                BufferedInputStream bufferedInputStreamNull = new BufferedInputStream(urlConnectionNull.getInputStream());
                BufferedReader bufferedReaderNull = new BufferedReader(new InputStreamReader(bufferedInputStreamNull, "UTF-8"));
                String returnLineNull;
                while ((returnLineNull = bufferedReaderNull.readLine()) != null) {
                    infection_StatusResultNull.append(returnLineNull);
                }

                InfectionStatus_Obj2 = XML.toJSONObject(infection_StatusResultNull.toString());
                return new ResponseEntity(InfectionStatus_Obj2.toString(),header, HttpStatus.OK);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(InfectionStatus_Obj1.toString(),header, HttpStatus.OK);
    }
}