package com.yuan.springcloud.dao;

import com.yuan.springcloud.entities.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao {
    boolean add(Dept dept);
    List<Dept> findAll();
    Dept findOneById(Long id);
    boolean deleteById(Long id);
}
