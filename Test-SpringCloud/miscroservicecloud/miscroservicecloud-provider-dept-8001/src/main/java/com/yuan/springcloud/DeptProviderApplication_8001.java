package com.yuan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DeptProviderApplication_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderApplication_8001.class,args);
    }
}
