package com.ryan.gmall.admin.aop;

import com.ryan.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一处理所有异常，给前端返回500的json
 * <p>
 * 当有环绕通知的时候，目标方法出现的异常一定要抛出去
 */
@Slf4j
//二合一写法
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {ArithmeticException.class})
    public Object handlerException(Exception exception) {
        log.error("系统全局异常感知，信息：{}", exception.getStackTrace());
        return new CommonResult().validateFailed("数学问题");
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public Object handlerException02(Exception exception) {
        log.error("系统全局异常感知，信息：{}", exception.getMessage());
        return new CommonResult().validateFailed("空指针了");
    }
}
