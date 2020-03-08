package com.heman.bysj.mapper;

import com.heman.bysj.model.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherMapper {
    //通过专业及职位进行查询
    Teacher selectByProfessionAndPosition(@Param("profession") String profession,@Param("position") String position);
    //通过ID进行查询
    Teacher selectById(@Param("id")int id);
}
