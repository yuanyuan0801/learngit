package com.batch.service;

import com.batch.dao.StudentDao;
import com.batch.model.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Transactional
    public void insertStudentList(List<StudentDto> list){
         studentDao.insertStudentList(list);
    }


    public void insertStudent(StudentDto dto){
        studentDao.insertStudent(dto);
    }
}
