package com.veganplanet.common.core.auth;


import cn.hutool.json.JSONUtil;
import com.veganplanet.common.core.request.model.AuthUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Aspect
@Component
public class LoginAspect {

    /**
     *作用在类上生效
     */
    @Around("@within(login)")
    public Object clazz(ProceedingJoinPoint pjp, Login login) throws Throwable {
        return checkLogin(pjp, login);
    }

    /**
     *作用在方法上生效
     */
    @Around("@annotation(login)")
    public Object menthod(ProceedingJoinPoint pjp, Login login) throws Throwable {
        return checkLogin(pjp, login);
    }


    /**
     * 登录逻辑校验
     *
     * @param pjp   切点
     * @return
     * @throws Throwable
     */
    public Object checkLogin(ProceedingJoinPoint pjp,Login login) throws Throwable {
        AuthContext.clearLoginInfo();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userData = URLDecoder.decode(request.getHeader("user-data"),StandardCharsets.UTF_8.toString());
        log.info("登录用户信息:{}", userData);
        AuthUserInfo authUserInfo= JSONUtil.toBean(userData, AuthUserInfo.class);
        AuthContext.setLoginInfo(authUserInfo);
        return pjp.proceed();
    }

}
