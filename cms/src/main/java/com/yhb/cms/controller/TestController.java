package com.yhb.cms.controller;

import com.yhb.cms.entity.Member;
import com.yhb.cms.mapper.MemberMapper;
import com.yhb.cms.remote.redis.RemoteRedisService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TestController {

    private final RemoteRedisService remoteRedisService;

    private final MemberMapper memberMapper;

    public TestController(RemoteRedisService remoteRedisService,
                          MemberMapper memberMapper) {
        this.remoteRedisService = remoteRedisService;
        this.memberMapper = memberMapper;
    }

    @GetMapping("test")
    public String test() {
        Member member = memberMapper.selectById(1);
        log.info(member.toString());
        return this.remoteRedisService.test();
    }
}
