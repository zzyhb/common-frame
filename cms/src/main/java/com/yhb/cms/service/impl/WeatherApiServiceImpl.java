package com.yhb.cms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yhb.cms.service.WeatherApiService;
import com.yhb.cms.util.WeatherResponse;
import com.yhb.common.json.Jackson;

import cn.hutool.http.HttpRequest;

/**
 * @author fusu
 */
@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    public final static String HOST = "https://freecityid.market.alicloudapi.com";

    public final static String BRIEF_CONDITION_URL = "/whapi/json/alicityweather/briefcondition";

    public final static String CITY_ID = "284873";

    public final static String TOKEN = "46e13b7aab9bb77ee3358c3b672a2ae4";

    public final static String APP_CODE = "f35f7e015a5b45cf842353790f308e7a";

    @Override
    public WeatherResponse getTodayWeather() {
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("cityId", CITY_ID);
        formMap.put("token", TOKEN);

        String result = HttpRequest.post(HOST + BRIEF_CONDITION_URL)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization","APPCODE " + APP_CODE)
                .form(formMap)
                .execute()
                .body();
        return Jackson.build()
                .readValue(result, WeatherResponse.class);
    }

}