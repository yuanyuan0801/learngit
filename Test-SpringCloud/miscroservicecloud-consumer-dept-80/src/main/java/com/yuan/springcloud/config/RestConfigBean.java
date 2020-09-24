package com.yuan.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfigBean {

    @Bean
    @LoadBalanced //spring cloud ribbon 是netflix ribbon 的客户端 ，负载均衡工具
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public IRule getIrule(){
//      //  return  new RoundRobinRule();//默认轮询算法
//       // return  new RandomRule();//随机算法
//        return new RetryRule();//自动跳过挡掉的服务
//    }
}
