package com.yuan.springcloud.controller;

import com.yuan.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    public static final String REST_URL="http://localhost:8001";
  //  public static final String REST_URL="http://MISCROSERVICECLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL+"/dept/add",dept, Boolean.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> findAll(){
        return restTemplate.getForObject(REST_URL+"/dept/list",List.class);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept findOneById(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL+"/dept/get/"+id,Dept.class);
    }


}
