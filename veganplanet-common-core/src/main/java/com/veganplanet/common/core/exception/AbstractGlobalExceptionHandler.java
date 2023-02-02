package com.veganplanet.common.core.exception;

import cn.hutool.http.HttpStatus;
import com.veganplanet.common.core.response.Res;
import com.veganplanet.common.core.response.ServiceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>程序异常处理</p>
 *
 * @author wxh_work@163.com
 * @date 2023/2/2 20:31
 */
@Slf4j
@Order(-1)
public abstract class AbstractGlobalExceptionHandler {


    @ExceptionHandler(value = AbstractCustomizedRunTimeException.class)
    @ResponseBody
    public Res handleCustomRunTimeException(AbstractCustomizedRunTimeException e) {
        log.error(e.getMessage(), e);
        return Res.get(ServiceStatus.GENERAL.BAD_REQUEST, e.getServiceStatus().getMessage());
    }
}
