package com.yhb.redis.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author fusu
 * @since 2021/1/16 11:50 上午
 */
@Data
public class RedisStringValueVO {

    @NotNull(message = "key can't be null")
    private String key;

    @NotNull(message = "value can't be null")
    private String value;

    @NotNull(message = "expireTime can't be null")
    private Integer expireTime;

}
