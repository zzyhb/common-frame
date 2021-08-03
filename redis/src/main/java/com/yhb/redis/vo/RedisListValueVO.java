package com.yhb.redis.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author fusu
 * @since 2021/1/16 11:50 上午
 */
@Data
public class RedisListValueVO {

    @NotNull(message = "key can't be null")
    private String key;

    @NotNull(message = "value can't be null")
    private List<Temp> value;

    @NotNull(message = "expireTime can't be null")
    private Integer expireTime;

    @Data
    public static class Temp {
        private String name;

        private Integer age;
    }

}
