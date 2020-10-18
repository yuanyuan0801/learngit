package com.yuan.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

/**
 * swagger2配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.yuan.springcloud"))
                    .paths(PathSelectors.any())
                    .build()
                    .directModelSubstitute(LocalDate.class,String.class);//序列化的时候用一个类型代替一个类型

    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("练习使用微服务")
                                    .contact(new Contact("com.yuan.springcloud","www.baidu.com","1224778842.qq.com"))
                                    .description("这是创建的swagger2，用于查看接口")
                                    .version("1.0")
                                    .build();
    }

}
