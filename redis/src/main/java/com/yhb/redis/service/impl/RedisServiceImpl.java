package com.yhb.redis.service.impl;

import com.yhb.redis.annotation.NeedPrintLog;
import com.yhb.redis.annotation.Retry;
import com.yhb.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fusu
 * @since 2021/1/16 11:27 上午
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ValueOperations<String, Object> valueRedisOperations;

    private final HashOperations<String, String, Object> hashRedisOperations;

    private final ListOperations<String, Object> listRedisOperations;

    private final SetOperations<String, Object> setRedisOperations;

    private final ZSetOperations<String, Object> sortedSetRedisOperations;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate,
                            ValueOperations<String, Object> valueRedisOperations,
                            HashOperations<String, String, Object> hashRedisOperations,
                            ListOperations<String, Object> listRedisOperations,
                            SetOperations<String, Object> setRedisOperations,
                            ZSetOperations<String, Object> sortedSetRedisOperations) {
        this.redisTemplate = redisTemplate;
        this.valueRedisOperations = valueRedisOperations;
        this.hashRedisOperations = hashRedisOperations;
        this.listRedisOperations = listRedisOperations;
        this.setRedisOperations = setRedisOperations;
        this.sortedSetRedisOperations = sortedSetRedisOperations;
    }

    @Override
    @NeedPrintLog
    public Boolean setStringValueExpireIfAbsent(String key, String value, Integer expireTime) {
        return this.valueRedisOperations.setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public void setStringValueExpire(String key, String value, Integer expireTime) {
        this.valueRedisOperations.set(key, value, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public String getStringValue(String key) {
        Object result = this.valueRedisOperations.get(key);
        return result != null ? result.toString() : null;
    }

    @Override
    public Boolean setHashValueExpire(String key, String field, String value, Integer expireTime) {
        this.hashRedisOperations.put(key, field, value);
        return this.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public Object getHashValue(String key, String field) {
        return this.hashRedisOperations.get(key, field);
    }

    @Override
    public Boolean setListValueExpire(String key, List<Object> value, Integer expireTime) {
        this.listRedisOperations.leftPushAll(key, value);
        return this.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public List<Object> getAllListValue(String key) {
        Long finalIndex = this.listRedisOperations.size(key);
        Assert.notNull(finalIndex, "redis list size get error");
        return this.listRedisOperations.range(key, 0, 1);
    }

    @Override
    public List<Object> getRangedListValue(String key, Long startIndex, Long stopIndex) {
        return this.listRedisOperations.range(key, startIndex, stopIndex);
    }

    @Override
    public Boolean setSetValueExpire(String key, Object value, Integer expireTime) {
        this.setRedisOperations.add(key, value);
        return this.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public Object getSetValue(String key) {
        return this.setRedisOperations.pop(key);
    }

    @Override
    public Boolean setSortedSetValueExpire(String key, String value, Double weight, Integer expireTime) {
        this.sortedSetRedisOperations.add(key, value, weight);
        return this.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public Object getSortedSetValue(String key) {
        return this.setRedisOperations.pop(key);
    }

    @Override
    @Retry(times = 3)
    public Boolean deleteValue(String key) {
        return this.redisTemplate.delete(key);
    }

}
