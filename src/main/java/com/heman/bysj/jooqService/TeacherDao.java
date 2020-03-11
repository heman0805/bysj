package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.TeacherRecord;

public interface TeacherDao {
    //通过专业及职位进行查询
    TeacherRecord selectByProfessionAndPosition(String profession,  String position);
    //通过ID进行查询
    TeacherRecord selectById(int id);
}
