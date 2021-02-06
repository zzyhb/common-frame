package com.yhb.redis.exception;

import com.yhb.common.result.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created on 2021/1/17 15:33
 *
 * @author Yhb
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> exception(Exception e) {
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseResult<Void> apiException(ApiException e) {
        return ResponseResult.fail(e.getMessage());
    }

}
