package com.yhb.cms.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fusu
 * @since 2020/10/16 5:57 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {

    private Integer code;

    private Weather data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather {

        private City city;

        private Condition condition;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class City {
            private String name;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Condition {
            /*天气*/
            private String condition;
            /*湿度*/
            private String humidity;
            /*温度*/
            private String temp;
            /*更新时间*/
            private String updatetime;
            /*风向*/
            private String windDir;
            /*风力*/
            private String windLevel;
            /*风速*/
            private String windDegrees;
        }

    }

}
