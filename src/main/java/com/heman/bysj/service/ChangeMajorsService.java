package com.heman.bysj.service;

import com.heman.bysj.entity.ChangeMajorResult;
import com.heman.bysj.entity.MajorProgress;
import com.heman.bysj.entity.MajorTask;
import com.heman.bysj.jooq.tables.pojos.Changemajors;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Teacher;

import java.util.List;
import java.util.Map;

public interface ChangeMajorsService {
    boolean startMajor(Changemajors changemajors);
    List<MajorTask> searchTasks(int id);
    Changemajors selectMajorByProcessInstanceId(String processInstanceId);
    void major_Check(Examine examine);
    List<MajorProgress> userSearch(int userId);
    List<ChangeMajorResult> selectMajor(String college);
    int setClass(List<ChangeMajorResult> list);
    List<ChangeMajorResult> getByProfession(String profession,String param,int grade);
    void download(String college);
    boolean selectMajorByUserId(int id);
    
}
