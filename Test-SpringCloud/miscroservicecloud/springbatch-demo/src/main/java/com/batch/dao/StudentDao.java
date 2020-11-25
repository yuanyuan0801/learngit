package com.batch.dao;


import com.batch.model.Student;
import com.batch.model.StudentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {

    boolean insertStudentList(@Param("list")List<StudentDto> studentDtoList);

    boolean insertStudent(@Param("dto")StudentDto dto);

}
