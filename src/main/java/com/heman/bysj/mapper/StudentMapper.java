package com.heman.bysj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.heman.bysj.model.entity.Student;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    //获取学生列表
    List<Student> listTest();

    //根据ID获取学生信息
    Student selectById(@Param("id") int id);
}
