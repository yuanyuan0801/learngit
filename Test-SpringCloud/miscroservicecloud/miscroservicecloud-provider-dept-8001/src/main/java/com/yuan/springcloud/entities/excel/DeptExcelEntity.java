package com.yuan.springcloud.entities.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;


@Data
public class DeptExcelEntity extends BaseRowModel {

    @ExcelProperty(value = "序列号id",index = 0)
    private Long dbNo;
    @ExcelProperty(value = "部门",index = 1)
    private String dbName;
    @ExcelProperty(value = "数据库",index = 2)
    private String dbSource;
}
