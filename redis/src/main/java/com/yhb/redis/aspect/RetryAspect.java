package com.yhb.redis.aspect;

import com.yhb.redis.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created on 2021/1/17 15:28
 *
 * @author Yhb
 */
@Component
@Aspect
@Slf4j
public class RetryAspect {

    @Pointcut("@annotation(com.yhb.redis.annotation.Retry)")
    private void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName=joinPoint.getSignature().getName();
        Class<?> classTarget=joinPoint.getTarget().getClass();
        Class<?>[] par=((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Method objMethod=classTarget.getMethod(methodName, par);
        Retry retry=objMethod.getAnnotation(Retry.class);
        int time = 0;
        Object result;
        try {
            result = joinPoint.proceed();
            if (result instanceof Boolean && !((Boolean) result)) {
                do {
                    result = joinPoint.proceed();
                    time ++;
                } while (time < retry.times());
            }
        } catch (Exception e) {
            do {
                result = joinPoint.proceed();
                time ++;
            } while (time < retry.times());
        }
        // 调用结果返回
        return result;
    }

}
