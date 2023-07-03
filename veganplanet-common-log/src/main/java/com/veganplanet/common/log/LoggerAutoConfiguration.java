package com.veganplanet.common.log;

import com.veganplanet.common.log.aspect.LogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @author wxh_work@163.com
 * @date 2023/2/3 11:40
 */
@Configuration
class LoggerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(LogAspect.class)
    LogAspect logAspect() {
        return new LogAspect();
    }
}
