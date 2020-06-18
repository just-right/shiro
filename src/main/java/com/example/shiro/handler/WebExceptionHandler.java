package com.example.shiro.handler;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: ExceptionHandler
 * @description 异常统一处理类
 * @author: luffy
 * @date: 2020/6/10 18:57
 * @version:V1.0
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    @ExceptionHandler
    public String authorizationExceptionHandler(AuthorizationException e){
        return "无访问权限";
    }

    @ExceptionHandler
    public String exceptionHandler(Exception e){
        return "服务器异常！";
    }
}
