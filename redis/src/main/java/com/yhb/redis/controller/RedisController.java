package com.yhb.redis.controller;

import com.yhb.common.result.ResponseResult;
import com.yhb.redis.service.RedisService;
import com.yhb.redis.util.RedisDistributedLock;
import com.yhb.redis.vo.RedisListValueVO;
import com.yhb.redis.vo.RedisStringValueVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fusu
 * @since 2021/1/16 11:34 上午
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final static String REDIS_TEST_DOWNLOAD = "redis_test_download";

    private final RedisService redisService;

    private final RedisDistributedLock redisDistributedLock;

    public RedisController(RedisService redisService, RedisDistributedLock redisDistributedLock) {
        this.redisService = redisService;
        this.redisDistributedLock = redisDistributedLock;
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

    @PostMapping("/list/set")
    public ResponseResult<Boolean> setRedisListValue(@RequestBody @Validated RedisListValueVO redisListValueVO) {
        return ResponseResult.success(this.redisService.setListValueExpire(redisListValueVO.getKey(), redisListValueVO.getValue(),
                redisListValueVO.getExpireTime()));
    }

    @GetMapping("/list/get")
    public ResponseResult<List<Object>> getRedisListValue(@RequestParam String key) {
        return ResponseResult.success(this.redisService.getAllListValue(key));
    }

    @GetMapping("/test/download")
    public ResponseResult<Void> getRedisListValue() {
        Boolean boo = this.redisDistributedLock.tryLock(REDIS_TEST_DOWNLOAD);
        if (!boo) {
            return ResponseResult.fail("此操作正在进行中，请勿重复操作！");
        }
        try {
            // 模拟文件下载耗时操作
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.redisDistributedLock.unlock(REDIS_TEST_DOWNLOAD);
        return ResponseResult.success("你的文件正在下载中请稍后", null);
    }
}
