package com.yhb.redis.service;

/**
 * @author fusu
 * @since 2021/1/16 11:23 上午
 */
public interface RedisService {

    /********************/
    /* 1、string类型操作 */
    /*******************/
    /**
     * 存储string类型的值
     *
     * @param key
     *         键
     * @param value
     *         值
     * @param expireTime
     *         过期时间
     *
     * @return 结果
     */
    boolean setStringValueExpire(String key, String value, Integer expireTime);

    /**
     * 获取string类型的值
     *
     * @param key
     *         键
     *
     * @return 值
     */
    String getStringValue(String key);

}
