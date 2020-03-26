package com.heman.bysj.controller;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class test {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    @Resource
    private IdentityService identityService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private HistoryService historyService;


    /**
     * 启动流程实例
     */
    @RequestMapping(value="/startTask")
    public void test2(){
        System.out.println("进入test");
        Map<String,Object> map = new HashMap<>();
        map.put("num",2);
        map.put("holidayName","赫嫚");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday2",map);
        System.out.println("流程部署ID"+processInstance.getDeploymentId());//null
        System.out.println("流程定义ID"+processInstance.getProcessDefinitionId());//holiday:1:4
        System.out.println("流程实例ID"+processInstance.getId());//2501
        System.out.println("活动ID"+processInstance.getActivityId());//null


    }

    //查询当前用户的任务并处理掉
    @RequestMapping(value="/query")
    public  void query() {
        //3.查询当前用户的任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("holiday2")
                .taskAssignee("赫嫚")
                .list();

        //4.处理任务,结合当前用户任务列表的查询操作的话,任务ID:task.getId()
        for (Task task : taskList) {
            taskService.complete(task.getId());

            //5.输出任务的id
            System.out.println("任务ID" + task.getId());
            System.out.println("任务处理人" + task.getAssignee());
        }

    }
        //查询当前用户的任务并处理掉
    @RequestMapping(value="/queryTask")
    public  void queryTask() {
        //3.查询当前用户的任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("holiday2")
                .taskCandidateGroup("Group_Instructor")
                .list();


        //4.处理任务,结合当前用户任务列表的查询操作的话,任务ID:task.getId()
        for (Task task:taskList) {
            /*taskService.complete(task.getId());*/
            taskService.claim(task.getId(),"张三");
            //5.输出任务的id
            System.out.println("任务ID"+task.getId());
            System.out.println("任务ID");
            System.out.println("任务处理人"+task.getAssignee());
        }

    }
    /**
     * 查询business
     */
    public void findBusinessKey(){
//1,使用任务ID，查询对象task

    List<Task> taskList = taskService.createTaskQuery()
            .processDefinitionKey("holiday2")
            .taskCandidateGroup("Group_Instructor")
            .list();
    //2.使用任务ID，获取实例ID
    for(Task task:taskList){
        //获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //3.使用流程实例，查询

        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        //4.使用流程实例对象获取BusinessKey

        String business_key = pi.getBusinessKey();

        //5、根据BusinessKey查询业务表，判断该任务用户是否和审批人是同一学院/专业
        //从而选择是否拾取
        taskService.claim(task.getId(),"张三");
    }

  /*  //5.获取Business_key对应的主键ＩＤ

    String id = "";

        if(StringUtils.isNotBlank(business_key)) {

            //截取字符串

            id = business_key.split("\\.")[1].toString();

        }*/

    }
}
