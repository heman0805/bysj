package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.pojos.Project;
import com.heman.bysj.jooq.tables.records.ProjectRecord;

import java.util.List;

public interface ProjectDao {
    int insert(ProjectRecord project);
    ProjectRecord selectByProcessInstanceId(String processInstanceId);
    void complete(String proceInstanceId);
    void stopRunProcessInstance(String processInstanceId);
    //查找正在执行的任务
    List<ProjectRecord> selectByUserIdAndRole(int id,String role);
    List<ProjectRecord> selectByRoleAndProcessStatus(String role,int processStatus);
    List<ProjectRecord> selectByProcessStatus(int processStatus);
    List<ProjectRecord> selectByUserId(int id);
    List<ProjectRecord> selectByUserIdAndRoleAndProcessStatus(int userId,String role);

}
