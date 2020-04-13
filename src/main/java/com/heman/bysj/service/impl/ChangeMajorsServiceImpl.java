package com.heman.bysj.service.impl;

import com.heman.bysj.activiti.Activiti_Holiday;
import com.heman.bysj.activiti.Activiti_Major;
import com.heman.bysj.entity.ChangeMajorResult;
import com.heman.bysj.entity.MajorProgress;
import com.heman.bysj.entity.MajorTask;
import com.heman.bysj.jooq.tables.pojos.*;
import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.jooq.tables.records.ExamineRecord;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
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
        //1、启动转专业流程实例
        String processInstanceId = activiti_major.startInstance(changemajors);
        //2、插入转专业表
        changemajors.setProcessinstanceid(processInstanceId);
        changemajors.setProcessstatus(1);//设置审批流程状态为审批中
        ChangemajorsRecord changemajorsRecord = new ChangemajorsRecord();
        changemajorsRecord.from(changemajors);
        int result = changeMajorsDao.insert(changemajorsRecord);
        //3.完成转专业表单填写
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
        String taskId = "";
        if(examine.getCheckresult().equals("通过")){
            taskId = activiti_major.teacherCompleteTask("majorProcess",examine.getProcessinstanceid());
            changeMajorsDao.complete(examine.getProcessinstanceid());
        }else if(examine.getCheckresult().equals("拒绝")){
            taskId = activiti_major.stopProcessInstance("majorProcess",examine.getProcessinstanceid());
            changeMajorsDao.stopRunProcessInstance(examine.getProcessinstanceid());
        }
        //封装任务ID
        examine.setTaskid(taskId);
        //ExaminRecord
        ExamineRecord examineRecord = new ExamineRecord();
        examineRecord.from(examine);
        examineDao.insert(examineRecord);

    }

    /**
     *       1、根据用户查询业务表获取businessKey
     *       3、根据业务表及examine表封装审核数据
     *      *
     * @param userId
     */
    @Override
    public List<MajorProgress> userSearch(int userId){
        //存放结果
        List<MajorProgress> result = new ArrayList<>();
        //查询请假单进度
        System.out.println("进入service");
        //根据用户id查询业务表
        List<ChangemajorsRecord> changemajorsRecords = changeMajorsDao.selectByUserId(userId);
        if(changemajorsRecords.size()==0)
            return null;
        for (ChangemajorsRecord changemajorsRecord: changemajorsRecords) {//循环每个申请任务
            Changemajors changemajors = changemajorsRecord.into(Changemajors.class);
            //通过流程ID查找历史任务
            List<HistoricTaskInstance> historicTaskInstances = activiti_major.findHistoryTaskByBusinessKey(changemajors.getProcessinstanceid());
            for (HistoricTaskInstance hti:historicTaskInstances) {
                MajorProgress majorProgress = new MajorProgress();
                ExamineRecord examineRecord = examineDao.selectByTaskId(hti.getId());
                if(examineRecord!=null){
                    Examine examine = examineRecord.into(Examine.class);
                    majorProgress.setProcessInstanceId(changemajors.getProcessinstanceid());
                    majorProgress.setTaskId(examine.getTaskid());
                    majorProgress.setNewCollege(changemajors.getNewcollege());
                    majorProgress.setNewProfession(changemajors.getNewprofession());
                    majorProgress.setOpinion(examine.getOpinion());
                    majorProgress.setCheckTime(examine.getChecktime());
                    majorProgress.setCheckResult(examine.getCheckresult());
                    Teacher teacher = teacherDao.selectById(Integer.parseInt(hti.getAssignee())).into(Teacher.class);
                    majorProgress.setTaskStatus(teacher.getCollege()+"/"+teacher.getPosition()+"/审批完成");
                    result.add(majorProgress);
                }

            }
        }
        log.info("查询用户转账任务完成");
        System.out.println(result.toArray());
        return result;
    }

    /**
     * 通过学院名称查找转专业申请结果
     * 1、查找changeMajor表
     * （where currentCollege = college and processStatus=6 or processStatus=7）
     * @param college
     * @return
     */
    @Override
    public List<Changemajors> selectMajor(String college) {
        List<Changemajors> changemajorsList = new ArrayList<>();

        return null;
    }

    /**
     * 通过专业查找转专业结果
     * @param profession
     * @return
     */
    @Override
    public List<ChangeMajorResult> getByProfession(String profession) {
        List<ChangeMajorResult> result = new ArrayList<>();
        List<ChangemajorsRecord> changemajorsRecords = changeMajorsDao.selectByProfessionAndProcessStatus(profession, 6);
        System.out.println("profession:"+profession+",审核通过列表："+changemajorsRecords);
        for (ChangemajorsRecord record:changemajorsRecords) {
            Changemajors changemajors = record.into(Changemajors.class);
            ChangeMajorResult changeMajorResult = new ChangeMajorResult();
            Student student = studentDao.selectById(changemajors.getUserid()).into(Student.class);
            changeMajorResult.setName(student.getName());
            changeMajorResult.setNumber(student.getUsername());
            changeMajorResult.setSex(student.getSex());
            changeMajorResult.setNewProfession(changemajors.getNewprofession());
            changeMajorResult.setGpa(changemajors.getGpa());
            result.add(changeMajorResult);
        }
        return result;
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public void setClass(List<ChangeMajorResult> list) {
    }
}
