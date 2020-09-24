package com.yuan.springcloud.service;


import com.yuan.springcloud.entities.Dept;

import java.util.List;

public interface DeptService {
    boolean add(Dept dept);
    List<Dept> findAll();
    Dept findOneById(Long id);
}
