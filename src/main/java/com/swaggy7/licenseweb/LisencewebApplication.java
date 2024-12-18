package com.swaggy7.licenseweb;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.swaggy7.licenseweb.mapper")
@SpringBootApplication
public class LisencewebApplication {
    private static Logger logger = Logger.getLogger(LisencewebApplication.class);
    public static void main(String[] args) {
        logger.debug("debug！！！");
        logger.error("error!!!");
        logger.info("info!!!!1");
        SpringApplication.run(LisencewebApplication.class, args);
    }

}
