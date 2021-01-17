package com.yhb.redis.exception;

import com.yhb.common.result.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created on 2021/1/17 15:33
 *
 * @author Yhb
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> exception(Exception e) {
        return ResponseResult.fail(e.getMessage());
    }

}
