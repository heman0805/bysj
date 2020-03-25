package com.heman.bysj.controller;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class test {
    @Resource
    private RuntimeService runtimeService;
/*

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private HistoryService historyService;
*/

    @RequestMapping(value="/test")
    public void test2(){
        System.out.println("进入test");
/*
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RunService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String,Object> map = new HashMap<>();
        map.put("num",4);
        //3.创建流程实例  流程定义的key需要知道 holiday
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday",map);

        System.out.println("流程部署ID"+processInstance.getDeploymentId());//null
        System.out.println("流程定义ID"+processInstance.getProcessDefinitionId());//holiday:1:4
        System.out.println("流程实例ID"+processInstance.getId());//2501
        System.out.println("活动ID"+processInstance.getActivityId());//null
*/

        Map<String,Object> map = new HashMap<>();
        map.put("num",4);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday",map);
        System.out.println("流程部署ID"+processInstance.getDeploymentId());//null
        System.out.println("流程定义ID"+processInstance.getProcessDefinitionId());//holiday:1:4
        System.out.println("流程实例ID"+processInstance.getId());//2501
        System.out.println("活动ID"+processInstance.getActivityId());//null

        //        securityUtil.logInAs("salaboy");
       /* ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("holiday")
                .withVariable("num",4)
                .build());//启动流程实例
*/
        // System.out.println(processInstance.getId());

    }
}
