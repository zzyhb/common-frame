package com.yhb.cms.controller;

import com.yhb.cms.remote.redis.RemoteRedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2021/1/20 22:29
 *
 * @author Yhb
 */
@RestController
@RequestMapping("test")
public class TestController {

    private final RemoteRedisService remoteRedisService;

    public TestController(RemoteRedisService remoteRedisService) {
        this.remoteRedisService = remoteRedisService;
    }

    @GetMapping("test")
    public String test() {
        return this.remoteRedisService.test();
    }
}
