package com.yuan.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author yuanxiya
 * @Description 启动类
 * @Date 2020/11/24 22:49
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class);
    }
}
