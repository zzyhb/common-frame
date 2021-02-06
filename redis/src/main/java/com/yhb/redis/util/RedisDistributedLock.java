package com.yhb.redis.util;

import com.yhb.common.result.ResponseResult;
import com.yhb.redis.exception.ApiException;
import com.yhb.redis.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/2/6 13:08
 *
 * @author Yhb
 */
@Component
public class RedisDistributedLock {

    private final RedisService redisService;

    public RedisDistributedLock(RedisService redisService) {
        this.redisService = redisService;
    }

    public Boolean tryLock(String key) {
        return this.redisService.setStringValueExpireIfAbsent(key, "lock", 20);
    }

    public void lock(String key) {
        Boolean boo;
        do {
            boo = this.redisService.setStringValueExpireIfAbsent(key, "lock", 20);
        } while (!boo);
    }

    public void unlock(String key) {
        Boolean deleteSuccess = this.redisService.deleteValue(key);
        if (!deleteSuccess) {
            throw new ApiException("多次删除redis无果  建议人工干涉 以免造成分布式锁线程阻塞");
        }
    }
}
