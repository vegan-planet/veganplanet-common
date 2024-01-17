package com.veganplanet.common.core.exception;

import com.veganplanet.common.core.response.ServiceStatus;
import lombok.Data;

/**
 * <p>全局异常处理</p>
 *
 * @author wxh_work@163.com
 * @date 2023/2/2 16:42
 */
@Data
public class VeganplanetException extends RuntimeException  {
    private static final long serialVersionUID = 1L;
    private final ServiceStatus serviceStatus;

    public VeganplanetException (ServiceStatus serviceStatus) {
        this(serviceStatus,serviceStatus.getMessage());
    }
    public VeganplanetException (final String message) {
        this(ServiceStatus.GENERAL.SERVICE_ERROR,message);
    }
    public VeganplanetException(ServiceStatus serviceStatus,final String message) {
        super(message);
        this.serviceStatus = serviceStatus;
    }

}
