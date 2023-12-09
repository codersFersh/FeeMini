package com.example.feeend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描Mapper文件夹
@MapperScan("com.example.feeend.mapper")
@SpringBootApplication
public class FeeEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeeEndApplication.class, args);
    }

}
