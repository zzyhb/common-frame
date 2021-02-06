package com.yhb.redis.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created on 2021/2/6 11:25
 *
 * @author Yhb
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException extends RuntimeException{

    private Integer code;

    private String message;

    public ApiException(String message) {
        this.message = message;
    }
}
