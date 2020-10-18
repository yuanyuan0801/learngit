package com.yuan.springcloud.controller;

import com.yuan.springcloud.entities.ApiResponseEntity;
import com.yuan.springcloud.entities.Dept;
import com.yuan.springcloud.service.DeptService;
import com.yuan.springcloud.util.ApiResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Api(tags = "部门分类")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PostMapping("/dept/add")
    public ApiResponseEntity add(@RequestBody Dept dept){
        deptService.add(dept);
        ApiResponseEntity entity = ApiResponseUtil.success();
        return entity;
    }

    @GetMapping("/dept/list")
    public List<Dept> findAll(){
        return deptService.findAll();
    }


    @ApiOperation(value = "部门查询")
    @GetMapping("/dept/get/{id}")
    public ApiResponseEntity findOneById(@PathVariable("id") Long id){
        ApiResponseEntity entity = ApiResponseUtil.successWithData(deptService.findOneById(id));
        return entity;
    }

    @ApiOperation("数据导出")
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response){
       long start =  System.currentTimeMillis();
       deptService.exportExcel(response);
       long end = System.currentTimeMillis();
       long time = end-start;
        System.out.println("下载时长："+time);

    }





}
