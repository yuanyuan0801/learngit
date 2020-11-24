package com.yuan.springcloud.dao;

import com.yuan.springcloud.entities.Dept;
import com.yuan.springcloud.entities.excel.DeptExportExcelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao {
    boolean add(Dept dept);
    List<Dept> findAll();
    Dept findOneById(Long id);
    boolean deleteById(Long id);

    List<Dept> findExist(@Param("dbName") String dbName,@Param("dbSource") String dbSource);

    List<DeptExportExcelEntity> findDataToExcel();
}
