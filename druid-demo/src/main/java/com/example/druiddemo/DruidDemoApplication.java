package com.example.druiddemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.druiddemo.dao")
public class DruidDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DruidDemoApplication.class, args);
    }

}
