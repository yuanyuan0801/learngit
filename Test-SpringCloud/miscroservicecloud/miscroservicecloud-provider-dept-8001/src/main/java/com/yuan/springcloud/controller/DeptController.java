package com.yuan.springcloud.controller;

import com.yuan.springcloud.entities.ApiResponseEntity;
import com.yuan.springcloud.entities.Dept;
import com.yuan.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PostMapping("/dept/add")
    public ApiResponseEntity add(@RequestBody Dept dept){
       deptService.add(dept);
        ApiResponseEntity entity = new ApiResponseEntity();
        entity.setCode("0");
        entity.setMessage("请求执行成功");
        return entity;
    }

    @GetMapping("/dept/list")
    public List<Dept> findAll(){
        return deptService.findAll();
    }

    @GetMapping("/dept/get/{id}")
    public ApiResponseEntity findOneById(@PathVariable("id") Long id){
        System.out.println("这是firstBranch上的东西");
        System.out.println("这是master上的东西");
        ApiResponseEntity entity = new ApiResponseEntity();
        entity.setCode("0");
        entity.setMessage("请求执行成功");
        entity.setDept(deptService.findOneById(id));
        return entity;
    }





}
