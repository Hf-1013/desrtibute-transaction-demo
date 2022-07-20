package com.quicktron.producer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.quicktron.producer.mapper")
public class TransactionApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class,args);
    }
}
