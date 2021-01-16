package com.yhb.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fusu
 * @since 2021/1/16 12:49 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {

    private boolean success;

    private String message;

    private T data;

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(true, null, data);
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(true, message, data);
    }

}
