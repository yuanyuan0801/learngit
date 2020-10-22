package com.yuan.springcloud.entities.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;


/**
 * @Author yuanxiya
 * @Description 将excel文档中的数据导入到数据库
 * @Date 2020/10/19 22:42
 */
@Data
public class DeptImportExcelEntity extends BaseRowModel{
    @ExcelProperty(value = "序列号",index = 0)
    private Long dbNo;
    @ExcelProperty(value = "部门",index = 1)
    private String dbName;
    @ExcelProperty(value = "数据库",index = 2)
    private String dbSource;
}
