package com.coronacommunity.CoronaCommunity.web.controller;


import com.coronacommunity.CoronaCommunity.entity.InfectionCity;
import com.coronacommunity.CoronaCommunity.service.ApiService;
import com.coronacommunity.CoronaCommunity.service.InfectionCityService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RequiredArgsConstructor
@Controller
public class InfectionCity7DayspiController {
    @Autowired
    ApiService apiService;

    @Autowired
    InfectionCityService infectionCityService;


    JSONObject InfectionCity_Obj = null;

    //매일 10시마다 해당 메서드 강제 실행
    @Scheduled(cron = "0 0 10 * * *")
    @GetMapping("/api/data/testDay")
    public ResponseEntity InfectionCityDataSave() {
        StringBuffer infection_City_result = new StringBuffer(); // 코로나 시도별 전체 데이터 XML 결과 받아오는 변수

        Calendar calendar = new GregorianCalendar();

        SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

        //날짜 포맷
        String toDay = SDF.format(calendar.getTime());

        //yesterDay 전날 Api 파라미터 요청용 Data
        calendar.add(Calendar.DATE, -7);
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
            JSONObject parse_response = (JSONObject) InfectionCity_Obj.get("response");
            JSONObject parse_body = (JSONObject) parse_response.get("body");
            JSONObject parse_items = (JSONObject) parse_body.get("items");
            JSONArray parse_item = (JSONArray) parse_items.get("item");



            JSONObject obj;
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
            //해당일
            String OneDay = null;
            //지역
            String gubun = null;



            for(int i =0; i < parse_item.length(); i++) {
                InfectionCity infectionCity = new InfectionCity();
                obj = (JSONObject) parse_item.get(i); //해당 item가져오기
               infectionCity.setGubun((String) obj.get("gubun"));
               gubun = (String) obj.get("gubun");
               String dateStr = (String) obj.get("createDt");
               Date f_date = input.parse(dateStr);
               infectionCity.setCreatedt(output.format(f_date));
               OneDay = output.format(f_date);
               infectionCity.setDeathcnt((Integer) obj.get("deathCnt"));
               infectionCity.setDefcnt((Integer) obj.get("defCnt"));

               infectionCityService.InfectionCityApiDataAdd(infectionCity, OneDay, gubun);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(InfectionCity_Obj.toString(),header, HttpStatus.OK);
    }

    @GetMapping("/api/data/infectionCity7Day")
    public ResponseEntity infectionCityAPI() {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", "success");

        JSONObject result_data = new JSONObject();

        result_data.put("data", infectionCityService.InfectionCityAPiDataCall());


        jsonObject.put("result_data", result_data);


        return new ResponseEntity(jsonObject.toString(), header, HttpStatus.OK);
    }
        }


