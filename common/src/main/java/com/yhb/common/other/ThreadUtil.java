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

    private final ThreadPoolExecutor singleThreadPool = singleThreadPool();

    private final ThreadPoolExecutor commonThreadPool = commonThreadPool();

    private ThreadUtil() {
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
     * 单线程线程池
     *
     * @return
     */
    public ThreadPoolExecutor getSinglePool() {
        return singleThreadPool;
    }

    /**
     * 通用线程池
     *
     * @return
     */
    private static ThreadPoolExecutor commonThreadPool() {
        return new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("common-pool").build());
    }
    
    /**
     * 单线程池
     *
     * @return
     */
    private static ThreadPoolExecutor singleThreadPool() {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("single").build());
    }

    /**
     * 通用线程池
     *
     * @return
     */
    public ThreadPoolExecutor getCommonThreadPool() {
        return commonThreadPool;
    }
}
