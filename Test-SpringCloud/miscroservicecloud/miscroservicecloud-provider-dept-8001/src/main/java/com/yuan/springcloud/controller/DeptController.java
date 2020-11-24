package com.yuan.springcloud.controller;

import com.yuan.springcloud.entities.ApiResponseEntity;
import com.yuan.springcloud.entities.Dept;
import com.yuan.springcloud.service.DeptService;
import com.yuan.springcloud.util.ApiResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@Api(tags = "部门分类")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;


    @ApiOperation(value = "新增")
    @PostMapping("/dept/add")
    public ApiResponseEntity add(@RequestBody Dept dept) {
        deptService.add(dept);
        ApiResponseEntity entity = ApiResponseUtil.success();
        return entity;
    }


    @ApiOperation(value = "查询所有数据")
    @GetMapping("/dept/list")
    public List<Dept> findAll() {
        return deptService.findAll();
    }


    @ApiOperation(value = "部门查询")
    @GetMapping("/dept/get/{id}")
    public ApiResponseEntity findOneById(@PathVariable("id") Long id) {
        ApiResponseEntity entity = ApiResponseUtil.successWithData(deptService.findOneById(id));
        return entity;
    }

    @ApiOperation("数据导出")
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) {
        long start = System.currentTimeMillis();
        deptService.exportExcel(response);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("下载时长：" + time);
    }


    @ApiOperation(value = "文档模板下载")
    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        deptService.download(response);
    }


    @ApiOperation(value = "excel导入")
    @PostMapping("/import")
    public ApiResponseEntity importExcel(@ApiParam("文件") MultipartFile file) {
        deptService.importExcel(file);
        return ApiResponseUtil.success();
    }


}
