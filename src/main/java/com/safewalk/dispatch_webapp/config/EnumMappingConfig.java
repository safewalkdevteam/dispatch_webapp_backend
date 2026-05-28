package com.safewalk.dispatch_webapp.config;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.lang.NonNull;

@Configuration
public class EnumMappingConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        ApplicationConversionService.configure(registry);
    }
}
