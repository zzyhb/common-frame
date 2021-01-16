package com.yhb.redis.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.yhb.redis.service.RedisService;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;

/**
 * @author fusu
 * @since 2021/1/16 11:27 上午
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    private final static String SUCCESS = "OK";

    private final JedisPool jedisPool;

    public RedisServiceImpl(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public boolean setStringValueExpire(String key, String value, Integer expireTime) {
        String result = this.jedisPool.getResource().setex(key, expireTime, value);
        log.info("RedisServiceImpl setStringValueExpire result : {}", result);
        return Objects.equals(SUCCESS, result);
    }

    @Override
    public String getStringValue(String key) {
        return this.jedisPool.getResource().get(key);
    }
}
