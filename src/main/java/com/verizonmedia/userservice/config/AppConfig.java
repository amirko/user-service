package com.verizonmedia.userservice.config;

import com.verizonmedia.userservice.conversion.UserConverter;
import com.verizonmedia.userservice.conversion.UserDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ConversionService conversionService () {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new UserConverter());
        service.addConverter(new UserDtoConverter());
        return service;
    }
}
