package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.ProjectRecord;

import java.util.List;

public interface ProjectDao {
    int insert(ProjectRecord project);
    ProjectRecord selectByProcessInstanceId(String processInstanceId);
    void complete(String proceInstanceId);
    void stopRunProcessInstance(String processInstanceId);
    List<ProjectRecord> selectByRoleAndProcessStatus(String role,int processStatus);
    List<ProjectRecord> selectByProcessStatus(int processStatus);
}
