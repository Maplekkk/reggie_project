package com.maple;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@MapperScan("com.maple.mapper")
@ServletComponentScan
@EnableTransactionManagement
public class ReggieProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieProjectApplication.class, args);
    }
}
