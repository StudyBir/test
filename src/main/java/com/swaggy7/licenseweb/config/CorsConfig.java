package com.swaggy7.licenseweb.config;/**
 * @Author Swaggy7
 * @PackageName com.swaggy7.licenseweb.config
 * @Date 2023/2/2 14:12
 * @describe TODO
 * @description:
 * @author: Swaggy7
 * @time: 2023/2/2 14:12
 */

/**
 * @description:
 * @author: Swaggy7
 * @time: 2023/2/2 14:12
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hxc
 * @dateTime: 2021-12-2
 * @description: 跨域配置
 * */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域
        registry.addMapping("/**")
                .allowedOrigins("*")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                //设置允许的方法
                .allowedMethods("*")
                .maxAge(3600);
    }
}