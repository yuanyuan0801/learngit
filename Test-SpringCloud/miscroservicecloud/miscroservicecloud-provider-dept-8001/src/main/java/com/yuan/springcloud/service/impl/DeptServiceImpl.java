package com.yuan.springcloud.service.impl;

import com.yuan.springcloud.dao.DeptDao;
import com.yuan.springcloud.entities.Dept;
import com.yuan.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean add(Dept dept) {
        return deptDao.add(dept);
    }

    @Override
    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    @Override
    public Dept findOneById(Long id) {
        return deptDao.findOneById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return deptDao.deleteById(id);
    }
}
