package com.veganplanet.common.log.aspect;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

/**
 * description
 *
 * @author wxh_work@163.com
 * @date 2023/2/3 11:40
 */
@Slf4j
@Aspect
public class LogAspect {

    private static final String REQUEST_MAPPING_WITHIN
            = "(@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.GetMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.PutMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.PatchMapping)"
            + "||"
            + "@annotation(org.springframework.web.bind.annotation.DeleteMapping))";

    /**
     * 1.包含{@link org.springframework.web.bind.annotation.RequestMapping}与组合 注解
     */
    @Pointcut(value = REQUEST_MAPPING_WITHIN)
    private void requestMappingPointcut() {

    }

    @Before(value = "requestMappingPointcut()")
    public void logBeforeRequest(JoinPoint joinPoint) {
        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            return;
        }

        final ObjectMapper objectMapper = this.getObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerFor(new TypeReference<>() {
            })
                    .writeValueAsString(joinPoint.getArgs());
        } catch (Exception ignored) {

        }

        final HttpServletRequest request = attributes.getRequest();

//        IP
        log.info("IP = {}", request.getRemoteAddr());
//        URL
        log.info("URL = {}", request.getRequestURL());
//        METHOD
        log.info("METHOD = {}", request.getMethod());
//        CLASS_METHOD
        log.info("CLASS_METHOD = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        if (Objects.nonNull(json)) {
//            JSON_ARGS
            log.info("JSON_ARGS = {}", json);
        } else {
//            ARGS
            log.info("ARGS = {}", joinPoint.getArgs());
        }

    }

    private ObjectMapper getObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(simpleDateFormat);

        return objectMapper;
    }
}
