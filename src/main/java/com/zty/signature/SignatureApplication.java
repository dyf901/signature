package com.zty.signature;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zty.signature")
@MapperScan("com.zty.signature.dao")
public class SignatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignatureApplication.class, args);
    }

}
