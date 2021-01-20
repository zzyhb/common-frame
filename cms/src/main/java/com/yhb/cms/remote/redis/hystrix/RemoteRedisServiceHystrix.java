package com.yhb.cms.remote.redis.hystrix;

import com.yhb.cms.remote.redis.RemoteRedisService;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/1/20 22:56
 *
 * @author Yhb
 */
@Component
public class RemoteRedisServiceHystrix implements RemoteRedisService {

    @Override
    public String test() {
        return "remote服务失败了 你调用的是我";
    }
}
