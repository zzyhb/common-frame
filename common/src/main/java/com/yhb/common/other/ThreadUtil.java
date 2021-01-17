package com.yhb.common.other;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * DopThreadUtil.build().xxxPool()
 * 根据任务类型是IO密集型还是CPU密集型、CPU核数，来设置合理的线程池大小、队列大小、拒绝策略。
 * 建议：刚开始可以保守设置，最适合自己场景的参数需要压测和调优。
 *
 * @author LiChun
 * @version $Id$
 * @since 2020年05月11日 17:37
 */
public class ThreadUtil {

    private static volatile ThreadUtil threadUtil;

    private final ThreadPoolExecutor singleThreadPool = singlePool("dsb-single");

    private final ThreadPoolExecutor mqThreadPool = mqThreadPool("dsb-mq-pool");

    private ThreadUtil() {
    }

    /**
     * 用于mq发送 线程池
     *
     * @param poolName
     *
     * @return
     */
    private static ThreadPoolExecutor mqThreadPool(String poolName) {
        return new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat(poolName).build());
    }
    
    /**
     * 单线程池，用于application监听事件处理
     *
     * @param poolName
     *
     * @return
     */
    private static ThreadPoolExecutor singlePool(String poolName) {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat(poolName).build());
    }

    public static ThreadUtil build() {
        if (threadUtil == null) {
            synchronized (ThreadUtil.class) {
            }
            if (threadUtil == null) {
                threadUtil = new ThreadUtil();
            }
        }
        return threadUtil;
    }

    /**
     * 用于简单的任务异步
     *
     * @return
     */
    public ThreadPoolExecutor singlePool() {
        return singleThreadPool;
    }

    /**
     * 用于mq发送异步
     *
     * @return
     */
    public ThreadPoolExecutor mqThreadPool() {
        return mqThreadPool;
    }
}
