package com.yhb.cms.remote.redis;

import com.yhb.cms.remote.redis.hystrix.RemoteRedisServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created on 2021/1/20 22:34
 *
 * @author Yhb
 */
@FeignClient(name = "redis", fallback = RemoteRedisServiceHystrix.class)
public interface RemoteRedisService {

    @GetMapping(value = "/redis/test")
    String test();
}
