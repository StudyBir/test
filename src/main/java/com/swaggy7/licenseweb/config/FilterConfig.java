package com.swaggy7.licenseweb.config;

import com.swaggy7.licenseweb.filter.LogFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {
	
	@Value("${mc.ip}")
	private String mcIp;
	
	@Bean
    public FilterRegistrationBean<LogFilter> pathFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter(mcIp));
        registrationBean.addUrlPatterns("/*"); // 设置过滤的路径
        registrationBean.setOrder(1); // 设置顺序
        return registrationBean;
    }
}
