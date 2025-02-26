package com.study.spring6.week6.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EtagFilterConfig {

    @Bean
    public FilterRegistrationBean<EtagFilter> etagFilter() {
        FilterRegistrationBean<EtagFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EtagFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
