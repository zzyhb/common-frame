package com.yhb.redis;

import com.google.common.collect.Lists;
import com.yhb.common.other.ThreadUtil;
import com.yhb.redis.util.RedisDistributedLock;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created on 2021/2/6 13:28
 *
 * @author Yhb
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class Test {

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    @org.junit.Test
    public void testLock() {
        long startTime = System.currentTimeMillis();
        String key = UUID.randomUUID().toString().replace("-", "");
        List<Integer> list = Lists.newArrayList();
        List<Future<?>> futureList = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            Future<?> future = ThreadUtil.build().getCommonThreadPool().submit(() -> {
                this.redisDistributedLock.lock(key);
                for (int j = 0; j < 10000; j++) {
                    list.add(j);
                }
                this.redisDistributedLock.unlock(key);
            });
            futureList.add(future);
        }
        for (Future<?> future : futureList) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("list大小为：" + list.size() + "方法执行耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }
}
