package com.yuan.springcloud.controller;

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
    public boolean add(Dept dept){
        return deptService.add(dept);
    }

    @GetMapping("/dept/list")
    public List<Dept> findAll(){
        return deptService.findAll();
    }

    @GetMapping("/dept/get/{id}")
    public Dept findOneById(@PathVariable("id") Long id){
        System.out.println("这是firstBranch上的东西");

        System.out.println("这是master上的东西");
        return deptService.findOneById(id);
    }



}
