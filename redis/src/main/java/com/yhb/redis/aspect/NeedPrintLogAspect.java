package com.yhb.redis.aspect;

import com.yhb.redis.exception.GlobalExceptionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/1/17 15:28
 *
 * @author Yhb
 */
@Component
@Aspect
@Slf4j
public class NeedPrintLogAspect {

    @Pointcut("execution(* com.yhb.redis.controller.*.*(..)) || @annotation(com.yhb.redis.annotation.NeedPrintLog)")
    private void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder sb = new StringBuilder(300);
        // 目标方法所在类全限定名
        String targetName = joinPoint.getSignature().getDeclaringType().getName();
        // 类名+方法名
        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String[] paramsName = ((MethodSignature) joinPoint.getSignature()).getParameterNames();

        sb.append(" 方法:【").append(target).append("】 ").append(" 入参:【");
        if (args != null && paramsName != null && args.length > 0 && paramsName.length > 0) {
            for (int i = 0; i < paramsName.length; i++) {
                sb.append(" ").append(paramsName[i]).append(" = ").append(args[i]).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        // 记录入参日志
        sb.append("】");
        log.info(sb.toString());
        // 调用目标方法
        Object result = joinPoint.proceed();
        // 记录出参日志
        log.info(" 出参:【" + result + "】");
        // 调用结果返回
        return result;
    }

}
