package com.heman.bysj.service.impl;

import com.heman.bysj.activiti.Activiti_Holiday;
import com.heman.bysj.activiti.Activiti_Major;
import com.heman.bysj.entity.MajorTask;
import com.heman.bysj.jooq.tables.pojos.*;
import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.jooq.tables.records.ExamineRecord;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooqService.*;
import com.heman.bysj.service.ChangeMajorsService;
import com.heman.bysj.utils.Convert;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ChangeMajorsServiceImpl  implements ChangeMajorsService{

    @Autowired
    private ChangeMajorsDao changeMajorsDao;
    @Autowired
    private Activiti_Major activiti_major;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ExamineDao examineDao;
    @Autowired
    private ClassesDao classesDao;

    @Override
    public boolean startMajor(Changemajors changemajors) {
        //1、启动请假流程实例
        String processInstanceId = activiti_major.startInstance(changemajors);
        //2、插入请假表
        changemajors.setProcessinstanceid(processInstanceId);
        changemajors.setProcessstatus(1);//设置审批流程状态为审批中
        ChangemajorsRecord changemajorsRecord = new ChangemajorsRecord();
        changemajorsRecord.from(changemajors);
        int result = changeMajorsDao.insert(changemajorsRecord);
        //3.完成请假单填写
        activiti_major.completeWrite(changemajors);
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

    public List<MajorTask> searchTasks(int id){
        log.info("登录用户ID："+id);
        Teacher teacher = teacherDao.selectById(id).into(Teacher.class);
        String definitionKey = "majorProcess";
        List<MajorTask> holidayTasks= new ArrayList<>();
        //进行相关任务的拾取
        activiti_major.teacherClaimTask(definitionKey,teacher);
        //查询所有任务并返回
        List<Task> taskList = activiti_major.findTask(definitionKey,teacher);
        //根据任务查询changeMajor表并封装返回
        for (Task task:taskList) {
            log.info("查询带处理任务ID："+task.getId());
            Changemajors changemajors = selectHolidayByProcessInstanceId(task.getProcessInstanceId());
            StudentRecord student = studentDao.selectById(changemajors.getUserid());
            MajorTask majorTask = Convert.changeMajorToMajorTask(student.getName(),student.getRole(),student.getSid(),student.getClass_(),task.getId(),changemajors);
            holidayTasks.add(majorTask);
        }
        return holidayTasks;

    }

    @Override
    public Changemajors selectHolidayByProcessInstanceId(String processInstanceId) {
        log.info("流程实例ID:"+processInstanceId);
        ChangemajorsRecord changemajorsRecord = changeMajorsDao.selectByProcessInstanceId(processInstanceId);
        if(changemajorsRecord!=null){
            return  changemajorsRecord.into(Changemajors.class);
        }else
            return null;
    }
    /**
     * 教师审批
     * 3、通过processInstanceID获得对应任务进行审批
     * 4、获得任务ID，封装至holidayCheck
     * 5、插入holidayCheck表
     */
    @Override
    public void major_Check(Examine examine) {
        //完成任务并获得任务ID
        String taskId = activiti_major.teacherCompleteTask("majorProcess",examine.getProcessinstanceid());
        //封装任务ID
        examine.setTaskid(taskId);
        //ExaminRecord
        ExamineRecord examineRecord = new ExamineRecord();
        examineRecord.from(examine);
        examineDao.insert(examineRecord);
        //修改Holiday表,设置审批状态为已完成
        if(activiti_major.isProcessEnd(examine.getProcessinstanceid())){//流程已走完
            changeMajorsDao.complete(examine.getProcessinstanceid(),2);
        }
    }
}
