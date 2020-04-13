package com.heman.bysj.service.impl;

import com.heman.bysj.activiti.Activiti_Holiday;
import com.heman.bysj.entity.HolidayByClass;
import com.heman.bysj.entity.HolidayHistory;
import com.heman.bysj.entity.HolidayTask;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.*;
import com.heman.bysj.jooqService.*;
import com.heman.bysj.service.HolidayService;
import com.heman.bysj.utils.Convert;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private ExamineDao examineDao;
    @Autowired
    private ClassesDao classesDao;

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
               System.out.println("查询到的教师信息："+teacher1);
               System.out.println("查询到的任务信息:"+task);
               holidayTask = Convert.HolidayToHolidayTask
                       (holiday,
                               task.getId(),
                               teacher1.getName(),
                               teacher1.getCollege(),
                               teacher1.getProfession(),
                               null,
                               0,
                               teacher1.getTid(),
                               teacher1.getRole());
           }
            holidayTasks.add(holidayTask);
        }
        return holidayTasks;
    }

    /**
     * 教师审批
     * 3、通过processInstanceID获得对应任务进行审批
     * 4、获得任务ID，封装至Examine
     * 5、封装至Examine
     */
    @Override
    public void holiday_Check(Examine holidayCheck) {
        //完成任务并获得任务ID
        String taskId = activiti_holiday.teacherCompleteTask("holidayProcess",holidayCheck.getProcessinstanceid());
        //封装任务ID
        holidayCheck.setTaskid(taskId);
        //ExaminRecord
        ExamineRecord holidayCheckRecord = new ExamineRecord();
        holidayCheckRecord.from(holidayCheck);
        examineDao.insert(holidayCheckRecord);
        //修改Holiday表,设置审批状态为已完成
        holidayDao.complete(holidayCheck.getProcessinstanceid(),2);
    }

    /**
     * 查询个人请假历史记录
     * 1、封装用户名及角色
     * 2、根据用户名及角色查询holiday表获得相关请假数据及processInstanceID
     * 3、根据processInstanceID查询check表 获得请假结果
     * 4、返回结果
     * @param userId , role
     * @return
     */
    @Override
    public List<HolidayHistory> holidayHistory(int userId, String role) {
        List<HolidayRecord> holidayRecords = holidayDao.selectByUserIdAndRoleAndProcessStatus(userId,role);
        if(holidayRecords.size()==0){
            log.info("该用户无请假记录，用户ID：{}，角色：{}",userId,role);
            return null;
        }
        List<HolidayHistory> list = new ArrayList<>();
        for (HolidayRecord holiday:holidayRecords) {
            Examine holidayCheck = examineDao.selectByProcessInstanceId(holiday.getProcessinstanceid()).into(Examine.class);
            HolidayHistory holidayHistory = new HolidayHistory();
            holidayHistory.setBeginTime(holiday.getBegintime());
            holidayHistory.setEndTime(holiday.getEndtime());
            holidayHistory.setDays(holiday.getDays());
            holidayHistory.setVacationType(holiday.getVacationtype());
            holidayHistory.setReason(holiday.getReason());
            holidayHistory.setCheckResult(holidayCheck.getCheckresult());
            holidayHistory.setOpinion(holidayCheck.getOpinion());
            list.add(holidayHistory);
        }
        return list;
    }

    /**
     * 查询班级列表
     * @param profession
     * @return
     */
    @Override
    public List<String> selectClassByProfession(String profession) {
        List<ClassesRecord> classesRecords = classesDao.selectClassByProfession(profession);
        if(classesRecords.size()==0){
            log.info("无该专业：{}",profession);
            return null;
        }
        List<String> classes = new ArrayList<>();
        for (ClassesRecord class_:classesRecords) {
            classes.add(class_.getClass_());
        }
        return classes;
    }

    /**
     * 查询专业列表
     * @param college
     * @return
     */
    @Override
    public List<String> selectProfessionByCollege(String college) {
        Result<Record1<String>> classesRecords = classesDao.selectProfessionByCollege(college);
        if(classesRecords.size()==0){
            log.info("无该学院：{}",college);
            return null;
        }
        List<String> classes = new ArrayList<>();
        for (Record1<String> class_:classesRecords) {
            classes.add(class_.get("profession").toString());
        }
        return classes;
    }

    /**
     * 通过班级查询请假列表
     * 1、通过班级查询sid
     * 2、根据学生ID查找holiday_check表
     * 3、进行数据封装
     * @param
     * @return
     */
    @Override
    public List<HolidayByClass> selectHolidayByClass(int tid,String param,String clpro) {
        List<HolidayByClass> holidayByClasses = new ArrayList<>();
        List<StudentRecord> studentRecords = new ArrayList<>();
        TeacherRecord teacherRecord = teacherDao.selectById(tid);
        if(param.equals("class"))
            studentRecords = studentDao.selectByClass(clpro);
        else if(param.equals("profession"))
            studentRecords = studentDao.selectByProfession(clpro);
        else if(param.equals("college"))
            studentRecords = studentDao.selectByCollege(clpro);
        for (StudentRecord studentRecord:studentRecords) {//循环每条学生信息
                if(teacherRecord.getGrade()==null||(teacherRecord.getGrade()!=null&&teacherRecord.getGrade().equals(studentRecord.getGrade()))) {
                    //每个学生的请假记录
                    List<HolidayRecord> holidays = holidayDao.selectByUidAndRole(studentRecord.getSid(), UserRole.STUDENT);
                    if (holidays.size() == 0 || holidays == null) {
                        continue;
                    }
                    for (HolidayRecord holidayRecord : holidays) {//处理每条请假记录
                        //通过请假记录查询请假结果表，
                        ExamineRecord holidayCheckRecord = examineDao.selectByProcessInstanceId(holidayRecord.getProcessinstanceid());
                        HolidayByClass holiday = new HolidayByClass();

                        System.out.println("查询的审批结果："+holidayCheckRecord);
                        if(holidayCheckRecord==null){
                            holiday.setCheckResult("审批中");
                        }else{
                            holiday.setCheckResult(holidayCheckRecord.getCheckresult());
                            holiday.setOpinion(holidayCheckRecord.getOpinion());
                        }
                        holiday.setBeginTime(holidayRecord.getBegintime());
                        holiday.setEndTime(holidayRecord.getEndtime());
                        holiday.setClass_(studentRecord.getClass_());
                        holiday.setDays(holidayRecord.getDays());
                        holiday.setName(studentRecord.getName());
                        holiday.setReason(holidayRecord.getReason());
                        holiday.setVacationType(holidayRecord.getVacationtype());
                        holidayByClasses.add(holiday);
                    }
                }
        }

        return holidayByClasses;
    }

    @Override
    public List<HolidayByClass> searchTeacherHoliday(String param,String colpro) {
        log.info("进入查询任务");
        List<HolidayByClass> holidayByClasses = new ArrayList<>();
        List<TeacherRecord> teacherRecords = new ArrayList<>();
        if(param.equals("college")){
            teacherRecords = teacherDao.selectByCollege(colpro);
            log.info("教师数量："+teacherRecords.size());
        }
        else if(param.equals("profession"))
            teacherRecords = teacherDao.selectByProfession(colpro);
        for (TeacherRecord teacherRecord:teacherRecords) {
            log.info("请假人ID："+teacherRecord.getTid());
            //每个教师的请假记录
            List<HolidayRecord> holidays = holidayDao.selectByUidAndRole(teacherRecord.getTid(),UserRole.TEACHER);
            if(holidays.size()==0||holidays==null){
                continue;
            }
            for (HolidayRecord holidayRecord:holidays) {//处理每条请假记录
                log.info("请假单ID："+holidayRecord.getFormid());
                ExamineRecord holidayCheckRecord = examineDao.selectByProcessInstanceId(holidayRecord.getProcessinstanceid());
                HolidayByClass holiday = new HolidayByClass();
                if(holidayCheckRecord==null){
                    holiday.setCheckResult("审批中");
                }else{
                    holiday.setCheckResult(holidayCheckRecord.getCheckresult());
                    holiday.setOpinion(holidayCheckRecord.getOpinion());
                }
                holiday.setProfession(teacherRecord.getProfession());
                holiday.setBeginTime(holidayRecord.getBegintime());
                holiday.setEndTime(holidayRecord.getEndtime());
                holiday.setDays(holidayRecord.getDays());
                holiday.setName(teacherRecord.getName());
                holiday.setReason(holidayRecord.getReason());
                holiday.setVacationType(holidayRecord.getVacationtype());
                holidayByClasses.add(holiday);
            }
        }

        return holidayByClasses;
    }
}
