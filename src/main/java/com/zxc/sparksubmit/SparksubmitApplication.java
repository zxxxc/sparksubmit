package com.zxc.sparksubmit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zxc.sparksubmit.mapper")
public class SparksubmitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparksubmitApplication.class, args);
    }
}

