package com.yuan.springcloud.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.yuan.springcloud.dao.DeptDao;
import com.yuan.springcloud.entities.Dept;
import com.yuan.springcloud.entities.excel.DeptExportExcelEntity;
import com.yuan.springcloud.entities.excel.DeptImportExcelEntity;
import com.yuan.springcloud.util.DeptImportListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptDao deptDao;

    public boolean add(Dept dept) {
        return deptDao.add(dept);
    }

    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    public Dept findOneById(Long id) {
        return deptDao.findOneById(id);
    }

    public boolean deleteById(Long id) {
        return deptDao.deleteById(id);
    }


    public void exportExcel(HttpServletResponse response){
        try {
            String fileName="部门情况.xlsx";
            List<DeptExportExcelEntity> list = deptDao.findDataToExcel();
            ServletOutputStream sos = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(sos,ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1,0, DeptExportExcelEntity.class);
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


    public void download(HttpServletResponse response){
        try {
            //1、获取要下载的文件流
            String fileName = "StudyNote.doc";
            fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
            //  String filePath = ResourceUtils.CLASSPATH_URL_PREFIX+"document/";
            // String filePath = System.getProperty("user.dir")+"/src/main/resources/document/";
            // ClassPathResource classPathResource = new ClassPathResource("/document/");
            String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"document/"+fileName;
            System.out.println(filePath+"-------------");
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);


            //2、下载设置
            //让浏览器收到这份资源的时候， 以下载的方式提醒用户，而不是直接展示
            //Content-Disposition属性有两种类型
            //inline ：将文件内容直接显示在页面
            //attachment：弹出对话框让用户下载
            response.setHeader("Content-Disposition","attachment:filename="+fileName);
            response.setContentType("application/octet-stream");//告诉浏览器输出内容为流
            response.setCharacterEncoding("utf-8");

            //3、设置输出流
            ServletOutputStream sos =  response.getOutputStream();

            //4、下载
            byte[] bytes = new byte[1024];
            int line = 0;
            while ((line=fis.read(bytes))!=-1){
                sos.write(bytes,0,line);
            }

            sos.flush();
            sos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


     /**
      * @Author yuanxiya
      * @Description 将excel文件中的数据导入到数据库
      * @Date 2020/10/21 22:15
      */
    public boolean importExcel(MultipartFile file){
        DeptImportListener listener = new DeptImportListener(deptDao);
        try {
           InputStream fileInputStream = file.getInputStream();
            EasyExcel.read(fileInputStream,DeptImportExcelEntity.class,listener).sheet(1).headRowNumber(0).doRead();
            List<Dept> excelEntityList = checkExcelList(listener);
            if(excelEntityList!=null && !excelEntityList.isEmpty()){
               for(Dept dept:excelEntityList){
                   deptDao.add(dept);
               }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return   true;

    }

    private List<Dept> checkExcelList(DeptImportListener listener) {
        List<String> errorList = listener.getErrorList();
        List<DeptImportExcelEntity> excelEntityList = listener.getDeptImportList();
        if(errorList !=null && !errorList.isEmpty()){
           StringBuilder builder = new StringBuilder();
           for (String s:errorList){
               builder.append(s+",");
           }
            System.out.println("数据有错误："+builder.toString());
           return null;
        }

        List<Dept> deptList = new ArrayList<>();
        for(DeptImportExcelEntity excelEntity:excelEntityList){
            Dept dept = new Dept();
            dept.setDbName(excelEntity.getDbName());
            dept.setDbSource(excelEntity.getDbSource());
            deptList.add(dept);
        }

        return deptList;
    }


}
