package com.yhb.cms.service;

import com.yhb.cms.util.WeatherResponse;

/**
 * @author fusu
 */
public interface WeatherApiService {

    /**
     * 获取当前天气
     *
     * @return 结果
     */
    WeatherResponse getTodayWeather();

}