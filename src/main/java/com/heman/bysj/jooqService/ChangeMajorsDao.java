package com.heman.bysj.jooqService;

import com.heman.bysj.entity.ChangeMajorResult;
import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;

import java.util.List;

public interface ChangeMajorsDao {
    int insert(ChangemajorsRecord changemajorsRecord);
    ChangemajorsRecord selectByProcessInstanceId(String processInstanceId);
    void complete(String processInstanceId);
    void stopRunProcessInstance(String processInstanceId);
    List<ChangemajorsRecord> selectByUserId(int userId);
    List<ChangemajorsRecord> selectByProfessionAndProcessStatus(String profession,int processStatus);
    List<ChangemajorsRecord> selectByCollegeAndProcessStatus(String college,int processStatus);
    List<ChangemajorsRecord> selectByCollegeAndNotComplete(String college);
    void updateProcessStatusCompleteByUserId(int userId);
    List<ChangemajorsRecord> selectByProcessStatus(int processStatus);
}
