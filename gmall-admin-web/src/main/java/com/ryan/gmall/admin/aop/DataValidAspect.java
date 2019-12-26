package com.ryan.gmall.admin.aop;

import com.ryan.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DataValidAspect {

    @Around("execution(* com.ryan.gmall.admin..*Controller.*(..))")
    public Object validAround(ProceedingJoinPoint point) {
        try {
            System.out.println("前置通知");
            Object proceed = point.proceed();
            System.out.println("返回通知");
        }catch (Throwable throwable) {
            System.out.println("异常通知");

        }finally {
            System.out.println("后置通知");
        }
        return new CommonResult().failed();
    }
}
