package com.heman.bysj.service.impl;

import com.heman.bysj.activiti.Activiti_Project;
import com.heman.bysj.entity.ProjectTask;
import com.heman.bysj.entity.User;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Project;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.ExamineRecord;
import com.heman.bysj.jooq.tables.records.ProjectRecord;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.ExamineDao;
import com.heman.bysj.jooqService.ProjectDao;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.jooqService.TeacherDao;
import com.heman.bysj.service.ProjectService;
import com.heman.bysj.utils.Convert;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private Activiti_Project activiti_project;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ExamineDao examineDao;
    @Override
    public boolean startProject(Project project) {
        //1、启动转专业流程实例
        String processInstanceId = activiti_project.startInstance(project);
        //2、插入转专业表
        project.setProcessinstanceid(processInstanceId);
        project.setProcessstatus(1);//设置审批流程状态为审批中
        ProjectRecord projectRecord = new ProjectRecord();
        projectRecord.from(project);
        int result = projectDao.insert(projectRecord);
        //3.完成转专业表单填写
        activiti_project.completeWrite(project);
        return result==1?true:false;
    }

    /**
     * 学院教务办查找待审批申请
     * 1、获取登录用户信息（ID、学院）
     * 2、activiti根据用户组查找任务
     * 3、通过任务获取businessID
     * 4、通过businessID查找changeMajors表获取当前所在学院
     * 5、学院相同，拾取任务，返回任务列表
     * @param id
     * @return
     */
    @Override

    public List<ProjectTask> searchTasks(int id){
        log.info("登录用户ID："+id);
        Teacher teacher = teacherDao.selectById(id).into(Teacher.class);
        String definitionKey = "projectProcess";
        List<ProjectTask> projectTask= new ArrayList<>();
        //进行相关任务的拾取
        activiti_project.teacherClaimTask(definitionKey,teacher);
        //查询所有任务并返回
        List<Task> taskList = activiti_project.findTask(definitionKey,teacher);
        //根据任务查询changeMajor表并封装返回
        for (Task task:taskList) {
            Project project = selectHolidayByProcessInstanceId(task.getProcessInstanceId());
            User user = new User();
            if(project.getRole().equals(UserRole.STUDENT)){
                StudentRecord student = studentDao.selectById(project.getUserid());
                user = Convert.studentToUser(student.into(Student.class));
            }else if(project.getRole().equals(UserRole.TEACHER)){
                TeacherRecord teacherRecord = teacherDao.selectById(project.getUserid());
                user = Convert.teacherToUser(teacherRecord.into(Teacher.class));
            }
            ProjectTask toProjectTask = Convert.changeProjectToProjectTask(project,user,task.getId());
            projectTask.add(toProjectTask);
        }
        return projectTask;
    }
    @Override
    public Project selectHolidayByProcessInstanceId(String processInstanceId) {
        log.info("流程实例ID:"+processInstanceId);
        ProjectRecord projectRecord = projectDao.selectByProcessInstanceId(processInstanceId);
        if(projectRecord!=null){
            return  projectRecord.into(Project.class);
        }else
            return null;
    }

    @Override
    public void project_Check(Examine examine) {
        //完成任务并获得任务ID
        String taskId = "";
        if(examine.getCheckresult().equals("通过")){
            taskId = activiti_project.teacherCompleteTask("projectProcess",examine.getProcessinstanceid());
            projectDao.complete(examine.getProcessinstanceid());
        }else if(examine.getCheckresult().equals("拒绝")){
            taskId = activiti_project.stopProcessInstance("processProcess",examine.getProcessinstanceid());
            projectDao.stopRunProcessInstance(examine.getProcessinstanceid());
        }
        //封装任务ID
        examine.setTaskid(taskId);
        //ExaminRecord
        ExamineRecord examineRecord = new ExamineRecord();
        examineRecord.from(examine);
        examineDao.insert(examineRecord);
    }

    @Override
    public List<ProjectTask> selectResult(int tid,String college, String profession,int param) {
        List<ProjectTask> result = new ArrayList<>();
        User user = new User();
        Teacher teacher = teacherDao.selectById(tid).into(Teacher.class);
        if(teacher.getPosition().equals("辅导员")){//教师角色为辅导员，查看该专业所有学生的申请
            List<ProjectRecord> projectRecords = projectDao.selectByRoleAndProcessStatus(UserRole.STUDENT,param);
            for (ProjectRecord project:projectRecords) {
                Student student = studentDao.selectById(project.getUserid()).into(Student.class);
                if(student.getProfession().equals(teacher.getProfession())){
                    user = Convert.studentToUser(student);
                    ProjectTask projectTask = Convert.changeProjectToProjectTask(project.into(Project.class),user,null);
                    result.add(projectTask);
                }
            }
        }
        else if(teacher.getPosition().equals("系主任")){//教师角色为系主任，查看该专业所有教师的申请
            List<ProjectRecord> projectRecords = projectDao.selectByRoleAndProcessStatus(UserRole.TEACHER,param);
            for (ProjectRecord project:projectRecords) {
                Teacher teacher1 = teacherDao.selectById(project.getUserid()).into(Teacher.class);
                if(teacher1.getProfession().equals(teacher.getProfession())){
                    user = Convert.teacherToUser(teacher1);
                    ProjectTask projectTask = Convert.changeProjectToProjectTask(project.into(Project.class),user,null);
                    result.add(projectTask);
                }
            }
        }
        else if(teacher.getPosition().equals("副院长")){//教师角色为副院长，查看该学院所有学生及教师的申请
            List<ProjectRecord> projectRecords = projectDao.selectByProcessStatus(param);
            for (ProjectRecord project:projectRecords) {
                if(project.getRole().equals(UserRole.TEACHER)){
                    Teacher teacher1 = teacherDao.selectById(project.getUserid()).into(Teacher.class);
                    if(teacher1.getCollege().equals(teacher.getCollege())){
                        user = Convert.teacherToUser(teacher1);
                        ProjectTask projectTask = Convert.changeProjectToProjectTask(project.into(Project.class),user,null);
                        result.add(projectTask);
                    }
                }
                else if(project.getRole().equals(UserRole.STUDENT)){
                    Student Student = studentDao.selectById(project.getUserid()).into(Student.class);
                    if(Student.getCollege().equals(teacher.getCollege())){
                        user = Convert.studentToUser(Student);
                        ProjectTask projectTask = Convert.changeProjectToProjectTask(project.into(Project.class),user,null);
                        result.add(projectTask);
                    }
                }

            }

        }else{//查询所有申请
            List<ProjectRecord> projectRecords = projectDao.selectByProcessStatus(param);
            for (ProjectRecord project:projectRecords) {
                if(project.getRole().equals(UserRole.TEACHER)){
                    Teacher teacher1 = teacherDao.selectById(project.getUserid()).into(Teacher.class);
                        user = Convert.teacherToUser(teacher1);
                        ProjectTask projectTask = Convert.changeProjectToProjectTask(project.into(Project.class),user,null);
                        result.add(projectTask);
                }
                else if(project.getRole().equals(UserRole.STUDENT)){
                    Student Student = studentDao.selectById(project.getUserid()).into(Student.class);
                        user = Convert.studentToUser(Student);
                        ProjectTask projectTask = Convert.changeProjectToProjectTask(project.into(Project.class),user,null);
                        result.add(projectTask);
                }
            }
        }
        return result;
    }
}
