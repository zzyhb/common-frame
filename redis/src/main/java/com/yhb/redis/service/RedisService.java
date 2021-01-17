package com.yhb.redis.service;

import java.util.List;

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
     */
    void setStringValueExpire(String key, String value, Integer expireTime);

    /**
     * 获取string类型的值
     *
     * @param key
     *         键
     *
     * @return 值
     */
    String getStringValue(String key);

    /********************/
    /* 2、hash类型操作 */
    /*******************/
    /**
     * 存储hash类型的值
     */
    Boolean setHashValueExpire(String key, String field, String value, Integer expireTime);

    /**
     * 获取hash类型的值
     */
    Object getHashValue(String key, String field);

    /********************/
    /* 3、list类型操作 */
    /*******************/
    /**
     * 存储list类型的值
     */
    Boolean setListValueExpire(String key, List<Object> value, Integer expireTime);

    /**
     * 获取list类型的值
     */
    List<Object> getListValue(String key, Long startIndex, Long stopIndex);

    /********************/
    /* 4、set类型操作 */
    /*******************/
    /**
     * 存储set类型的值
     */
    Boolean setSetValueExpire(String key, Object value, Integer expireTime);

    /**
     * 获取set类型的值
     */
    Object getSetValue(String key);

    /********************/
    /* 3、sortedSet类型操作 */
    /*******************/
    /**
     * 存储sortedSet类型的值
     */
    Boolean setSortedSetValueExpire(String key, String value, Double weight, Integer expireTime);

    /**
     * 获取sortedSet类型的值
     */
    Object getSortedSetValue(String key);

}
