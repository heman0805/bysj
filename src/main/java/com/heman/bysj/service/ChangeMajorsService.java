package com.heman.bysj.service;

import com.heman.bysj.entity.MajorTask;
import com.heman.bysj.jooq.tables.pojos.Changemajors;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Teacher;

import java.util.List;
import java.util.Map;

public interface ChangeMajorsService {
    boolean startMajor(Changemajors changemajors);
    List<MajorTask> searchTasks(int id);
    Changemajors selectHolidayByProcessInstanceId(String processInstanceId);
    void major_Check(Examine examine);
}
