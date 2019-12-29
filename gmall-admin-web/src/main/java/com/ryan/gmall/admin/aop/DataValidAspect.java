package com.ryan.gmall.admin.aop;

import com.ryan.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Slf4j
@Aspect
@Component
public class DataValidAspect {

    /**
     * 目标方法的异常，一般都需要再次抛出去，让别人感知
     * @param point
     * @return
     */
    @Around("execution(* com.ryan.gmall.admin..*Controller.*(..))")
    public Object validAround(ProceedingJoinPoint point) {
        Object proceed = null;
        try {
            Object[] args = point.getArgs();
            for(Object obj:args) {
                if(obj instanceof BindingResult) {
                    BindingResult r = (BindingResult)obj;
                    if(r.getErrorCount()>0) {
                        return new CommonResult().validateFailed(r);
                    }
                }
            }
            //System.out.println("前置通知");
            log.debug("检验切面介入工作。。。");
            //相当于反射中的method.invoke
            proceed = point.proceed(point.getArgs());
            log.debug("检验切面将目标方法已经放行。。。{}",proceed);
            //System.out.println("返回通知");
        }catch (Throwable throwable) {
            System.out.println("异常通知");
            throw  new RuntimeException();

        }finally {
            System.out.println("后置通知");
        }
        return new CommonResult().failed();
    }
}
