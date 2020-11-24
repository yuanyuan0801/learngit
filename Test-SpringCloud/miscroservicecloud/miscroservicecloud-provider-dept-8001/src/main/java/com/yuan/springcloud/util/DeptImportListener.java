package com.yuan.springcloud.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yuan.springcloud.dao.DeptDao;
import com.yuan.springcloud.entities.Dept;
import com.yuan.springcloud.entities.excel.DeptImportExcelEntity;
import org.eclipse.jetty.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yuanxiya
 * @Description 读取需要上传的excel文档
 * @Date 2020/10/19 22:55
 */
public class DeptImportListener extends AnalysisEventListener<DeptImportExcelEntity> {

    List<String> errorList = new ArrayList<>();
    List<DeptImportExcelEntity> deptList = new ArrayList<>();

    private DeptDao deptDao;

    public DeptImportListener(DeptDao deptDao){
        this.deptDao = deptDao;
    }


    @Override
    public void invoke(DeptImportExcelEntity deptImportExcelEntity, AnalysisContext analysisContext) {
        System.out.println("=========================");
        int currentRowNum = analysisContext.readRowHolder().getRowIndex()+1;
        System.out.println("当前数据"+deptImportExcelEntity.getDbNo()+deptImportExcelEntity.getDbName()+deptImportExcelEntity.getDbSource());
        if(checkNullRow(deptImportExcelEntity)){
            return;
        }
        if(!checkRow(deptImportExcelEntity,currentRowNum)){
            if(!checkExist(deptImportExcelEntity,currentRowNum)){
                deptList.add(deptImportExcelEntity);
            }
        }
    }

    private boolean checkNullRow(DeptImportExcelEntity deptImportExcelEntity) {
        System.out.println(deptImportExcelEntity.getDbName()+"------------------");
        System.out.println(deptImportExcelEntity.getDbSource()+"--------------");

        return StringUtil.isBlank(deptImportExcelEntity.getDbName())&&StringUtil.isBlank(deptImportExcelEntity.getDbSource());
    }

    private boolean checkExist(DeptImportExcelEntity deptImportExcelEntity,int currentRowNum) {
        System.out.println(deptImportExcelEntity.getDbName()+"------------------");
        System.out.println(deptImportExcelEntity.getDbSource()+"--------------");
        String dbName = deptImportExcelEntity.getDbName();
        String dbSource = deptImportExcelEntity.getDbSource();
        List<Dept> depts = deptDao.findExist(dbName,dbSource);
        if(!depts.isEmpty()){
            errorList.add(currentRowNum+"行数据已存在");
            return true;
        }

        return false;

    }


    private boolean checkRow(DeptImportExcelEntity deptImportExcelEntity,int currentRowNum) {
          if(deptImportExcelEntity.getDbName()==null || StringUtil.isBlank(deptImportExcelEntity.getDbName())){
                errorList.add(currentRowNum+"行部门名称为空");
                return true;
          }
          if(deptImportExcelEntity.getDbSource()==null||StringUtil.isBlank(deptImportExcelEntity.getDbSource())){
              errorList.add(currentRowNum+"行数据库名称为空");
              return true;
          }

          return false;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<String> getErrorList(){
        return errorList;
    }

    public List<DeptImportExcelEntity> getDeptImportList(){
        return deptList;
    }
}
