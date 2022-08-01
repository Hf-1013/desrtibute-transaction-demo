package com.quicktron.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author houfeng
 * @Date 2022/7/22 16:20
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.quicktron.consumer.mapper")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
