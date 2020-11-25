package com.batch.writer;

import com.batch.model.StudentDto;
import com.batch.processer.BatchItemProcesser;
import com.batch.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchItemWriter implements ItemWriter<StudentDto>{

    private static final Logger logger = LoggerFactory.getLogger(BatchItemWriter.class);

    @Autowired
    private StudentService studentService;

    @Override
    public void write(List<? extends StudentDto> list) throws Exception {
          for(StudentDto studentDto:list){
              logger.info("写入到数据库的数据是：name="+studentDto.getName()+"age="+studentDto.getAge()+"数据一共有"+list.size()+"条");
              studentService.insertStudent(studentDto);
          }
    }
}
