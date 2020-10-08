package com.yuan.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

//    @Bean
//    @LoadBalanced //spring cloud ribbon 是netflix ribbon 的客户端 ，负载均衡工具
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }

    @Bean
    public RestTemplate getRestTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        return factory;
    }

//    @Bean
//    public IRule getIrule(){
//      //  return  new RoundRobinRule();//默认轮询算法
//       // return  new RandomRule();//随机算法
//        return new RetryRule();//自动跳过挡掉的服务
//    }
}
