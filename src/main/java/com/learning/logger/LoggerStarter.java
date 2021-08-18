package com.learning.logger;

import com.learning.logger.core.LoggerAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(LoggerAspect.class)
public class LoggerStarter {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "logger.method.enable", havingValue="true")
    LoggerAspect starterService() {
        return new LoggerAspect();
    }

}
