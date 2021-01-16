package com.yhb.common.result;

/**
 * @author fusu
 * @since 2021/1/16 12:57 下午
 */
public class ResultUtil<T> {

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(true, message, data);
    }

}
