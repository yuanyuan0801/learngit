package com.yuan.springcloud.controller;

import com.yuan.springcloud.entities.ApiResponseEntity;
import com.yuan.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    public static final String REST_URL="http://localhost:8001";
  //  public static final String REST_URL="http://MISCROSERVICECLOUD-PROVIDER-DEPT";

//    @RequestMapping("/consumer/dept/add")
//    public boolean add(Dept dept){
//        return restTemplate.postForObject(REST_URL+"/dept/add",dept, Boolean.class);
//    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> findAll(){
        return restTemplate.getForObject(REST_URL+"/dept/list",List.class);
    }

//    @RequestMapping("/consumer/dept/get/{id}")
//    public Dept findOneById(@PathVariable("id") Long id){
//        return restTemplate.getForObject(REST_URL+"/dept/get/"+id,Dept.class);
//    }


    /**
     * 练习使用restTemplate远程接口调用
     * @return
     */
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept exerciseCallHttp(@PathVariable("id") Long id){
        ResponseEntity<ApiResponseEntity> responseEntity = restTemplate.getForEntity(REST_URL+"/dept/get/{id}",ApiResponseEntity.class,1L);
        if (responseEntity==null){
            System.out.println("返回值有问题");
        }

        Dept dept = responseEntity.getBody().getDept();
        return dept;

    }

    /**
     * 练习使用restTemplate远程接口调用(同上面的新增方法一样效果)
     * @return
     */
    @RequestMapping("/consumer/dept/add")
    public ApiResponseEntity add(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        headers.add("Accept",MediaType.APPLICATION_JSON.toString());

        Dept dept = new Dept();
        dept.setDbNo(33L);
        dept.setDbName("bbb");
        dept.setDbSource("bbb");
        HttpEntity<Dept> entity = new HttpEntity<>(dept,headers);
        ResponseEntity<ApiResponseEntity> responseEntity = restTemplate.postForEntity(REST_URL+"/dept/add",entity,ApiResponseEntity.class);
        return responseEntity.getBody();
    }





}
