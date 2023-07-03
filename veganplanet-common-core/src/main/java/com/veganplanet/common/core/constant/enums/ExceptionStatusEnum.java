package com.veganplanet.common.core.constant.enums;

import com.veganplanet.common.core.response.ServiceStatus;
import lombok.Getter;

/**
 * 异常状态枚举
 *
 * @author wxh_work@163.com
 * @date 2023/2/2 18:18
 */
@Getter
public enum ExceptionStatusEnum implements ServiceStatus {

    SYSTEM_SERVICE_ERROR(0, 100000, "服务异常，请稍后重试"),
    LOGIN_AUTH(0 ,100001 , "用户未登录,请从新登录"),
    LOGIN_ERROR(0,100002 , "用户名或者密码错误"),
    BAD_REQUEST(0, 40000, "请求参数有误"),
    UNAUTHORIZED(0, 40100, "权限错误"),
    INVALID_TOKEN(0, 40101, "无效Token"),
    DUPLICATE_REQUEST(0, 40102, "重复提交，请稍后重试"),
    FORBIDDEN(0, 40300, "禁止的请求"),
    SERVICE_ERROR(0, 50000, "服务内部错误，请稍后重试"),
    SERVICE_BUSY(0, 50001, "系统繁忙，请稍后重试"), //在有锁保护的接口中获取锁失败
    SERVICE_UNABLE(0, 40100, "服务不可用，请稍后重试"),
    SERVICE_EXPIRATION(0, 40100, "请重新登录");


    /**
     * 服务状态
     */
    private final int status;
    /**
     * 状态码
     */
    private final int code;
    /**
     * 信息
     */
    private final String message;

    ExceptionStatusEnum(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
