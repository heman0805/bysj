package com.heman.bysj.activiti;

import com.heman.bysj.jooq.tables.pojos.Changemajors;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.jooqService.ChangeMajorsDao;
import com.heman.bysj.jooqService.ExamineDao;
import com.heman.bysj.jooqService.StudentDao;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class Activiti_Major {
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ChangeMajorsDao changeMajorsDao;
    @Autowired
    private ExamineDao examineDao;
    /**
     * 启动请假流程
     * @param changemajors
     */
    public  String startInstance(Changemajors changemajors){
        String businessKey = changemajors.getCid();
        Map<String,Object> map = new HashMap<>();
        map.put("name",changemajors.getUserid().toString());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("majorProcess",businessKey,map);
        log.info("转专业申请流程启动成功，申请人id:{}",changemajors.getUserid());
        return processInstance.getId();
    }
    /**
     * 查询并完成任务
     * @param changemajors
     */
    public void completeWrite(Changemajors changemajors){
        //1、通过userID查询任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("majorProcess")
                .taskAssignee(changemajors.getUserid().toString())
                .list();
        for (Task task:taskList) {
            taskService.complete(task.getId());
        }
        log.info("完成转专业申请任务");
    }
    /**
     * 教师进行任务拾取
     * 1、查找当前用户任务
     * 2、通过任务获取流程实例id
     * 3、通过流程实例ID查找changeMajors表得到对应currentCollege
     * 4、判断是否应为当前用户审批
     * 5、进行拾取(处理人使用teacher.Tid)
     * @param definitionKey
     * @param teacher 登录教师信息（eg:辅导员-Group_Instructor）
     */
    public void teacherClaimTask(String definitionKey, Teacher teacher){
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(definitionKey)
                .taskCandidateGroup(teacher.getGroup())
                .list();
        for (Task task:taskList) {
            log.info("待拾取任务ID："+task.getId());
            String processInstanceId = task.getProcessInstanceId();
            ChangemajorsRecord changemajorsRecord = changeMajorsDao.selectByProcessInstanceId(processInstanceId);

            if(changemajorsRecord.getCurrentcollege().equals(teacher.getCollege())){
                   taskService.claim(task.getId(),teacher.getTid().toString());
            }
        }
    }
    /**
     * 查询待处理任务（待审批任务）
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
                .taskAssignee(teacher.getTid().toString())
                .list();

        System.out.println("查询待办任务列表为："+taskList);
        return taskList;
    }


    /**
     * 查询历史任务
     * @param processDefinitionKey 流程定义key
     * @param taskAssignee  任务代理人
     * @return
     */
    public List<HistoricTaskInstance> findHistoryTask(String processDefinitionKey, String taskAssignee){
        List<HistoricTaskInstance> list = historyService//与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询
                .processDefinitionKey(processDefinitionKey)
                .taskAssignee(taskAssignee)//指定历史任务的办理人
                .list();
        log.info("任务办理人：{}，任务列表",taskAssignee,list);
        return list;
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
    /**查询流程状态（判断流程正在执行，还是结束）*/
    public boolean isProcessEnd(String processInstanceId){
        //去正在执行的任务表查询
        ProcessInstance pi = runtimeService//表示正在执行的流程实例和执行对象
                .createProcessInstanceQuery()//创建流程实例查询
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        if(pi==null){
            log.info("流程实例ID：{},该流程实例已完成");
            return true;
        }
        else{
            log.info("流程实例ID：{},该流程实例未完成");
            return false;
        }
    }

}
