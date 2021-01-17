package com.yhb.common.other;

import java.util.Random;

import com.yhb.common.json.Jackson;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yhb
 * @since 2021年01月17日 23:26
 */
@Slf4j
public class StringUtil {

    private static final String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandomString(int length) { //length表示生成字符串的长度
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String formatYearMonth(Integer periodNumber) {
        if (NullUtil.isNull(periodNumber)) {
            return null;
        }
        String periodString = String.valueOf(periodNumber);
        return periodString.substring(0, 4) + "-" + periodString.substring(4);
    }

    public static void main(String[] args) {

        log.info(Jackson.build().writeValueAsString(getRandomString(32)));
    }
}
