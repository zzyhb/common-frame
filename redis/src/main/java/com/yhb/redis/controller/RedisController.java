package com.yhb.redis.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yhb.common.result.ResponseResult;
import com.yhb.redis.service.RedisService;
import com.yhb.redis.vo.RedisStringValueVO;

/**
 * @author fusu
 * @since 2021/1/16 11:34 上午
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/string/set")
    public ResponseResult<String> setRedisStringValue(@RequestBody @Validated RedisStringValueVO redisStringValue) {
        this.redisService.setStringValueExpire(redisStringValue.getKey(), redisStringValue.getValue(),
                redisStringValue.getExpireTime());
        return ResponseResult.success(redisStringValue.getKey());
    }

    @GetMapping("/string/get")
    public ResponseResult<String> getRedisStringValue(@RequestParam String key) {
        return ResponseResult.success(this.redisService.getStringValue(key));
    }
}
