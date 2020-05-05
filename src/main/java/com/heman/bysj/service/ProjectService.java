package com.heman.bysj.service;

import com.heman.bysj.entity.ProjectHistory;
import com.heman.bysj.entity.ProjectProcess;
import com.heman.bysj.entity.ProjectTask;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Project;

import java.util.List;

public interface ProjectService {
    boolean startProject(Project project,int group);
    List<ProjectTask> searchTasks(int id);
    Project selectHolidayByProcessInstanceId(String processInstanceId);
    void project_Check(Examine examine);
    List<ProjectTask> selectResult(int tid,String college,String profession,int param);
    List<Project> selectByUserIdAndRole(int id,String role);
    List<ProjectProcess> userSearch(int userId,String role);
    List<ProjectHistory> projectHistory(int userId, String role);
}
