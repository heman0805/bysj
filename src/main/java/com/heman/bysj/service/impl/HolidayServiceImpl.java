package com.heman.bysj.service.impl;

import com.heman.bysj.activiti.Activiti_Holiday;
import com.heman.bysj.entity.HolidayTask;
import com.heman.bysj.enums.Role;
import com.heman.bysj.enums.TeacherPosition;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.pojos.HolidayCheck;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.*;
import com.heman.bysj.jooqService.*;
import com.heman.bysj.service.HolidayService;
import com.heman.bysj.service.LeaveService;
import com.heman.bysj.utils.Convert;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricProcessInstance;
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
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayDao holidayDao;
    @Autowired
    private Activiti_Holiday activiti_holiday;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private HolidayCheckDao holidayCheckDao;

    @Override
    public boolean startHoliday(Holiday holiday) {
        //1、启动请假流程实例
        String processInstanceId = activiti_holiday.startInstance(holiday);
        //2、插入请假表
        holiday.setProcessinstanceid(processInstanceId);
        holiday.setProcessstatus(1);//设置审批流程状态为审批中
        int result = holidayDao.insert(holiday);
        //3.完成请假单填写
        activiti_holiday.completeWrite(holiday);
        return result==1?true:false;
    }

    /**
     *      * 1、根据用户名及processDefinitionKey查询历史流程实例
     *      * 2、判断该流程是否完成
     *      * 3、返回流程 流程实例ID
     *      *
     * @param userId
     */
    public Map<String,List> userSearch(String userId,String role){
        //存放processInstanceID
        Map<String,List> result = new HashMap<>();
        List<String> end = new ArrayList<>();//已结束流程
        List<String> run = new ArrayList<>();//正在执行的流程
        //查询请假单进度
        System.out.println("进入service");
        //根据用户名及processDefinitionKey查询历史流程实例
        List<HistoricTaskInstance> holidayProcess = activiti_holiday.findHistoryTask("holidayProcess", userId);
        //学生与教师的userId可能重复 得到businessKey查Holiday表判断Role
        for (HistoricTaskInstance instance:holidayProcess) {
            String processInstanceId = instance.getProcessInstanceId();
            HistoricProcessInstance historyProcessInstance = activiti_holiday.findHistoryProcessInstance(processInstanceId);
            HolidayRecord holidayRecord = holidayDao.selectByFormId(historyProcessInstance.getBusinessKey());
            if(holidayRecord!=null){
                Holiday holiday = holidayRecord.into(Holiday.class);
                if (holiday.getRole().equals(role)) {//（教师与学生ID可能重复，采用角色进行区分）
                    //判断该流程是否完成
                    boolean complete = activiti_holiday.isProcessEnd(processInstanceId);
                    if(complete)
                        end.add(processInstanceId);
                    else
                        run.add(processInstanceId);
                }
            }
        }
        result.put("end",end);
        result.put("run",run);
        log.info("查询用户请假任务完成");
        return result;
    }
    public Holiday selectHolidayByProcessInstanceId(String processInstanceId){
        HolidayRecord holidayRecord = holidayDao.selectByProcessInstanceId(processInstanceId);
        if(holidayRecord!=null){
            return  holidayRecord.into(Holiday.class);
        }else
            return null;
    }
    public List<HolidayTask> teacherSeacherTaskList(Teacher teacher){
        String definitionKey = "holidayProcess";
        List<HolidayTask> holidayTasks= new ArrayList<>();
        //进行相关任务的拾取
        activiti_holiday.teacherClaimTask(definitionKey,teacher);
        //查询所有任务并返回
        List<Task> taskList = activiti_holiday.findTask(definitionKey,teacher);
        //根据任务查询Holiday表并封装返回
        for (Task task:taskList) {
           Holiday holiday = selectHolidayByProcessInstanceId(task.getProcessInstanceId());
            HolidayTask holidayTask = new HolidayTask();
           if(holiday.getRole().equals(UserRole.STUDENT)){//请假人为学生
               Student student = studentDao.selectById(holiday.getUserid()).into(Student.class);
               holidayTask = Convert.HolidayToHolidayTask(holiday,task.getId(),student.getName(),student.getCollege(),student.getProfession(),student.getClass_(),student.getGrade(),student.getSid(),student.getRole());
           }else{
               Teacher teacher1 = teacherDao.selectById(holiday.getUserid()).into(Teacher.class);
               holidayTask = Convert.HolidayToHolidayTask(holiday,task.getId(),teacher1.getName(),teacher1.getCollege(),teacher1.getProfession(),null,teacher1.getGrade(),teacher1.getTid(),teacher1.getRole());
           }
            holidayTasks.add(holidayTask);
        }
        return holidayTasks;
    }

    /**
     * 教师审批
     * 3、通过processInstanceID获得对应任务进行审批
     * 4、获得任务ID，封装至holidayCheck
     * 5、插入holidayCheck表
     */
    @Override
    public void holiday_Check(HolidayCheck holidayCheck) {
        //完成任务并获得任务ID
        String taskId = activiti_holiday.teacherCompleteTask("holidayProcess",holidayCheck.getProcessinstanceid());
        //封装任务ID
        holidayCheck.setTaskid(taskId);
        //插入holidayTask表
        HolidayCheckRecord holidayCheckRecord = new HolidayCheckRecord();
        holidayCheckRecord.from(holidayCheck);
        holidayCheckDao.insert(holidayCheckRecord);
        //修改Holiday表,设置审批状态为已完成
        holidayDao.complete(holidayCheck.getProcessinstanceid(),2);
    }
}
