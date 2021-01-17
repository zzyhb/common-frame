package com.yhb.redis.service.impl;

import com.yhb.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author fusu
 * @since 2021/1/16 11:27 上午
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    private final static String SUCCESS = "OK";

    private final RedisTemplate<String, String> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setStringValueExpire(String key, String value, Integer expireTime) {
        ValueOperations<String, String> opsForValue = this.redisTemplate.opsForValue();
        opsForValue.set(key, value, expireTime);
    }

    @Override
    public String getStringValue(String key) {
        ValueOperations<String, String> opsForValue = this.redisTemplate.opsForValue();
        return opsForValue.get(key);
    }
}
