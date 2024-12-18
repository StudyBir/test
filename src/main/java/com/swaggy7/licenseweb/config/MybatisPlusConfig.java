package com.swaggy7.licenseweb.config;/**
 * @Author Swaggy7
 * @PackageName com.swaggy7.lisenceweb.config
 * @Date 2023/2/2 9:29
 * @describe TODO
 */

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: Swaggy7
 * @time: 2023/2/2 9:29
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.xlblog.blog.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}