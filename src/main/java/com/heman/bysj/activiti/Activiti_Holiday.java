package com.heman.bysj.activiti;

import com.heman.bysj.enums.UserGroup;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.HolidayRecord;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.HolidayDao;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.jooqService.TeacherDao;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Activiti_Holiday {
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;

    @Resource
    private HolidayDao holidayDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    /*
     * 启动请假流程
     * @param holiday
     */
    public  String startInstance(Holiday holiday){

        System.out.println("启动请假流程");
        String businessKey = holiday.getFormid();

        Map<String,Object> map = new HashMap<>();
        map.put("num",holiday.getDays());
        map.put("username0",holiday.getUserid().toString());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayProcess",businessKey,map);
        System.out.println("请假流程启动成功");
        log.info("请假流程启动成功，请假人id:{}，请假人角色:{}",holiday.getUserid(),holiday.getRole());
        return processInstance.getId();
    }

    /*
     * 查询并完成任务
     * @param holiday
     */
    public void completeWrite(Holiday holiday){
        //1、通过userID查询任务（学生，教师 ID可能相同,assginee=userId 查询结果可能包含其他人的任务）
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("holidayProcess")
                .taskAssignee(holiday.getUserid().toString())
                .list();
        for (Task task:taskList) {
            //2、通过任务获取流程实例id
            String processInstanceId = task.getProcessInstanceId();
            //3.使用流程实例，查询
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            //4.使用流程实例对象获取BusinessKey
            String business_key = pi.getBusinessKey();
            //5、根据BusinessKey查询业务表，判断该任务用户是否和审批人是同一角色
             HolidayRecord holidayRecord = holidayDao.selectByFormId(business_key);
             if(holidayRecord!=null){
                 Holiday holiday1 = holidayRecord.into(Holiday.class);
                 if (holiday.getRole().equals(holiday1.getRole())) {//（教师与学生ID可能重复，采用角色进行区分）
                    taskService.complete(task.getId());
                }
            }
        }
        System.out.println("完成请假单填写任务");
        log.info("完成请假单填写任务");
    }

    /**
     * 查询历史任务
     * @param processDefinitionKey 流程定义key
     * @param taskAssignee  任务代理人
     * @return
     */
    public List<HistoricTaskInstance> findHistoryTask(String processDefinitionKey,String taskAssignee){
        System.out.println("进入findHistoryTask");
        List<HistoricTaskInstance> list = historyService//与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询
                .processDefinitionKey(processDefinitionKey)
                .taskAssignee(taskAssignee)//指定历史任务的办理人
                .list();
        log.info("查询历史任务完成");
        log.info("任务办理人：{}，任务列表",taskAssignee,list);
        return list;
    }
    /**查询历史流程实例*/
    public HistoricProcessInstance findHistoryProcessInstance(String processInstanceId){
        HistoricProcessInstance pi = historyService//与历史数据（历史表）相关的Service
                .createHistoricProcessInstanceQuery()//创建历史流程实例查询
                .processDefinitionKey("holidayProcess")
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        log.info("查询历史流程实例完成,流程ID：{}，流程实例：{}",processInstanceId,pi);
        return pi;
    }
    /**查询流程状态（判断流程正在执行，还是结束）*/
    public boolean isProcessEnd(String processInstanceId){
        //去正在执行的任务表查询
        ProcessInstance pi = runtimeService//表示正在执行的流程实例和执行对象
                .createProcessInstanceQuery()//创建流程实例查询
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        if(pi==null){
            System.out.println("该流程实例走完");
            log.info("流程实例ID：{},该流程实例已完成");
            return true;
        }
        else{
            System.out.println("该流程实例还没走完");
            log.info("流程实例ID：{},该流程实例未完成");
            return false;
        }
    }

    /**
     * 教师进行任务拾取
     * 1、查找当前用户任务
     * 2、通过任务获取流程实例id
     * 3、通过流程实例ID查找holiday表得到对应userid及role
     * 4、查询对应用户表判断是否应为当前用户审批
     * 5、进行拾取(任务处理人设置为teacher.username--username唯一)
     * @param definitionKey
     * @param teacher 登录教师信息（eg:辅导员-Group_Instructor）
     */
    public void teacherClaimTask(String definitionKey, Teacher teacher){
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(definitionKey)
                .taskCandidateGroup(teacher.getGroup())
                .list();
        for (Task task:taskList) {
            //任务释ssssssss放

            String processInstanceId = task.getProcessInstanceId();
            HolidayRecord holidayRecord = holidayDao.selectByProcessInstanceId(processInstanceId);
            if(holidayRecord.getRole().equals(UserRole.TEACHER)){
                TeacherRecord teacherRecord = teacherDao.selectById(holidayRecord.getUserid());
                    //小于3天，系主任审批，申请人为教师，判断与当前用户所在专业是否一致（系主任）
                    if(holidayRecord.getDays()<=3){
                        if(teacherRecord.getProfession().equals(teacher.getProfession())&&teacher.getGroup().equals(UserGroup.Group_DEPARTMENTDIRECTOR)){
                            taskService.claim(task.getId(),teacher.getUsername());
                            log.info("任务拾取成功，任务ID：{},userId:{}",task.getId(),teacher.getTid());
                        }
                    }else{//大于3天，学院院长审批，判断是否为同一学院（院长）
                        if(teacherRecord.getCollege().equals(teacher.getCollege())&&teacher.getGroup().equals(UserGroup.GROUP_DEAN)){
                            taskService.claim(task.getId(),teacher.getUsername());
                            log.info("任务拾取成功，任务ID：{},userId:{}",task.getId(),teacher.getTid());
                        }
                    }
            }
            else if(holidayRecord.getRole().equals(UserRole.STUDENT)){
                StudentRecord student = studentDao.selectById(holidayRecord.getUserid());
                //小于3天，辅导员审批，申请人为学生，判断与当前用户所在专业是否一致,学生所对应的额辅导员为该审批人
                if(holidayRecord.getDays()<=3){
                    if(student.getTid().equals(teacher.getTid())&&student.getProfession().equals(teacher.getProfession())&&teacher.getGroup().equals(UserGroup.GROUP_INSTRUCTOR)){
                        taskService.claim(task.getId(),teacher.getUsername());
                        log.info("任务拾取成功，任务ID：{},userId:{}",task.getId(),teacher.getTid());
                    }
                }else{//大于3天，学院书记审批，判断是否为同一学院
                    if(student.getCollege().equals(teacher.getCollege())&&teacher.getGroup().equals(UserGroup.GROUP_SECRETARY)){
                        taskService.claim(task.getId(),teacher.getUsername());
                        log.info("任务拾取成功，任务ID：{},userId:{}",task.getId(),teacher.getTid());
                    }
                }
            }
        }
    }
    /**
     * 教师进行任务拾取
     * 1、查找当前用户任务
     * 2、通过任务获取流程实例id
     * 3、通过流程实例ID查找holiday表得到对应userid及role
     * 4、查询对应用户表判断是否应为当前用户审批
     * 5、放回
     * @param definitionKey
     * @param teacher 登录教师信息（eg:辅导员-Group_Instructor）
     */
    public void putPool(String definitionKey, Teacher teacher){
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(definitionKey)
                .taskAssignee(teacher.getUsername())
                .list();
        System.out.println("释放请假任务，查询任务数量："+taskList.size());
        for (Task task:taskList) {
            //任务释ssssssss放

            System.out.println("任务ID："+task.getId());
            String processInstanceId = task.getProcessInstanceId();
            HolidayRecord holidayRecord = holidayDao.selectByProcessInstanceId(processInstanceId);
            if(holidayRecord.getRole().equals(UserRole.TEACHER)){
                TeacherRecord teacherRecord = teacherDao.selectById(holidayRecord.getUserid());
                //小于3天，系主任审批，申请人为教师，判断与当前用户所在专业是否一致（系主任）
                if(holidayRecord.getDays()<=3){
                    if(teacherRecord.getProfession().equals(teacher.getProfession())&&teacher.getGroup().equals(UserGroup.Group_DEPARTMENTDIRECTOR)){
                        taskService.setAssignee(task.getId(),null);
                    }
                }else{//大于3天，学院院长审批，判断是否为同一学院（院长）
                    if(teacherRecord.getCollege().equals(teacher.getCollege())&&teacher.getGroup().equals(UserGroup.GROUP_DEAN)){
                        taskService.setAssignee(task.getId(),null);
                    }
                }
            }
            else if(holidayRecord.getRole().equals(UserRole.STUDENT)){
                StudentRecord student = studentDao.selectById(holidayRecord.getUserid());
                //小于3天，辅导员审批，申请人为学生，判断与当前用户所在专业是否一致,学生所对应的额辅导员为该审批人
                if(holidayRecord.getDays()<=3){
                    if(student.getTid().equals(teacher.getTid())&&student.getProfession().equals(teacher.getProfession())&&teacher.getGroup().equals(UserGroup.GROUP_INSTRUCTOR)){
                        taskService.setAssignee(task.getId(),null);
                    }
                }else{//大于3天，学院书记审批，判断是否为同一学院
                    if(student.getCollege().equals(teacher.getCollege())&&teacher.getGroup().equals(UserGroup.GROUP_SECRETARY)){
                        taskService.setAssignee(task.getId(),null);
                    }
                }
            }
        }
    }

    /**
     * 教师查询待处理任务（待审批任务）
     * @param processDefinitionKey
     * @param teacher
     * @return
     */
    public List<Task> findTask(String processDefinitionKey,Teacher teacher){
        //3.查询当前用户的任务
        //任务拾取后通过assignee进行查找

        System.out.println("进入查询任务方法");
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                //.taskCandidateGroup(teacher.getGroup())
                .taskAssignee(teacher.getUsername())
                .list();

        System.out.println("查询待办任务列表为："+taskList);
        return taskList;
    }

    /**
     * 通过processDefinitionKey及processInstanceID查找任务并完成任务
     * @param processDefinitionKey
     * @param processInstanceId
     * @return
     */
    public String teacherCompleteTask(String processDefinitionKey,String processInstanceId){
         Task task = taskService.createTaskQuery()
                 .processDefinitionKey(processDefinitionKey)
                 .processInstanceId(processInstanceId)
                 .singleResult();
        taskService.complete(task.getId());
        return task.getId();
    }
}
