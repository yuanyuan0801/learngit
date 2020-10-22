package com.yuan.springcloud.entities.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

  /**
   * @Author yuanxiya
   * @Description 将数据库数据导出到excel中时，文件对应的实体类
   * @Date 2020/10/19 22:40
   */
@Data
public class DeptExportExcelEntity extends BaseRowModel {

    @ExcelProperty(value = "序列号id",index = 0)
    private Long dbNo;
    @ExcelProperty(value = "部门",index = 1)
    private String dbName;
    @ExcelProperty(value = "数据库",index = 2)
    private String dbSource;
}
