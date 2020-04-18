package com.heman.bysj.service;

import com.heman.bysj.entity.ProjectTask;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Project;

import java.util.List;

public interface ProjectService {
    boolean startProject(Project project);
    List<ProjectTask> searchTasks(int id);
    Project selectHolidayByProcessInstanceId(String processInstanceId);
    void project_Check(Examine examine);
    List<ProjectTask> selectResult(int tid,String college,String profession,int param);
}
