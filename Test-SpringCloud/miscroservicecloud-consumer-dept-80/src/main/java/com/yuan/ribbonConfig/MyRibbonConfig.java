package com.yuan.ribbonConfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRibbonConfig {

  @Bean
  public IRule getIrule(){
      return new RandomRule();
  }
}
