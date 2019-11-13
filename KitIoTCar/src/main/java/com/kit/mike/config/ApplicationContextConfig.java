package com.kit.mike.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.kit.mike.*")
public class ApplicationContextConfig {
    @Bean(name="viewRosolver")
    public InternalResourceViewResolver getViewResource() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/resources/templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
