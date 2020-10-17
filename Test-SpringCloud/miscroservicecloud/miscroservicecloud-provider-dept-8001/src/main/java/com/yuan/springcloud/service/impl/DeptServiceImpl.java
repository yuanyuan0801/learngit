package com.yuan.springcloud.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.yuan.springcloud.dao.DeptDao;
import com.yuan.springcloud.entities.Dept;
import com.yuan.springcloud.entities.excel.DeptExcelEntity;
import com.yuan.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    @Override
    public void exportExcel(HttpServletResponse response){
        try {
            String fileName="部门情况.xlsx";
            List<DeptExcelEntity> list = deptDao.findDataToExcel();
            ServletOutputStream sos = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(sos,ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1,0, DeptExcelEntity.class);
            sheet.setAutoWidth(true);
            sheet.setSheetName("sheet1名称");
            writer.write(list,sheet);
            response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
            writer.finish();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            sos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }







}
