package com.yhb.redis.annotation;

import java.lang.annotation.*;

/**
 * Created on 2021/1/17 15:27
 *
 * @author Yhb
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedPrintLog {
}
