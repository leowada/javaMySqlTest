package com.ef.configuration;

import com.ef.accesslog.AccessLogManager;
import com.ef.arguments.ArgumentsParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public AccessLogManager accessLogManager() {
        return new AccessLogManager();
    }

    @Bean
    public ArgumentsParser argumentsParser() {
        return new ArgumentsParser();
    }

}
