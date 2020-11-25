package com.batch.processer;

import com.batch.model.Student;
import com.batch.model.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BatchItemProcesser implements ItemProcessor<Student,StudentDto> {

   private static final Logger logger = LoggerFactory.getLogger(BatchItemProcesser.class);
    private StudentDto studentDto;
    @Override
    public StudentDto process(Student student) throws Exception {
        logger.info("读取的数据是：name="+student.getName()+"age="+student.getAge());
        studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setAge(LocalDateTime.now().getMinute());
        return studentDto;
    }
}
