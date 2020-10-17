package com.yuan.springcloud.service;


import com.yuan.springcloud.entities.Dept;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DeptService {
    boolean add(Dept dept);
    List<Dept> findAll();
    Dept findOneById(Long id);
    boolean deleteById(Long id);

    void exportExcel(HttpServletResponse response);

}
