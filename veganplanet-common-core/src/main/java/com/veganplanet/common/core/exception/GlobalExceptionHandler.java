package com.veganplanet.common.core.exception;

import cn.hutool.json.JSONUtil;
import com.veganplanet.common.core.response.Res;
import com.veganplanet.common.core.response.ServiceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>程序异常处理</p>
 *
 * @author wxh_work@163.com
 * @date 2023/2/2 20:31
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {



    //自定义异常处理
    @ExceptionHandler(VeganplanetException.class)
    @ResponseBody
    public Res customVeganplanetException(VeganplanetException e) {
        log.error("自定义异常处理：VeganplanetException", e);
        return Res.get(e.getServiceStatus(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Res defaultErrorHandler(Exception e) {
        log.error("全局异常：Exception", e);
        e.printStackTrace();
        return Res.get(ServiceStatus.GENERAL.SERVICE_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Res handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("全局异常：MethodArgumentNotValidException,参数：{}", JSONUtil.toJsonStr(e.getBindingResult().getTarget()), e);
        final List<FieldError> errors = e.getBindingResult().getFieldErrors();
        errors.forEach(error -> {
            log.warn("对象:{},字段名:{},提示信息:{}", error.getObjectName(), error.getField(), error.getDefaultMessage());
        });
        if (!errors.isEmpty()) {
            final String message = String.format("%s", errors.get(0).getDefaultMessage());
            return Res.badRequest(message);
        } else {
            return Res.badRequest("参数校验异常");
        }
    }

}
