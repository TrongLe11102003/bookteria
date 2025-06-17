package com.devteria.profile.configuration;

import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Encoder;

@Configuration
public class FeignConfiguration {
    @Bean
    public Encoder multiPartFormEncoder() {
        return new SpringFormEncoder();
    }
}
