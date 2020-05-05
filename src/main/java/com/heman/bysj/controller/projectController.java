package com.heman.bysj.controller;

import com.heman.bysj.entity.ProjectHistory;
import com.heman.bysj.entity.ProjectProcess;
import com.heman.bysj.entity.ProjectTask;
import com.heman.bysj.enums.UserGroup;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Project;
import com.heman.bysj.service.ProjectService;
import com.heman.bysj.utils.Convert;
import com.heman.bysj.utils.Page;
import com.heman.bysj.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class projectController {
    @Autowired
    private ProjectService projectService;

    /**
     * 项目立项申请
     * 1、根据参数获得申请单、用户信息
     * 2、启动申请流程实例
     * 3、完成填写申请任务
     * 4、插入项目申请及项目审批表
     * @param params
     */
    @RequestMapping(value="/user/project/insert",method = RequestMethod.POST)
    public Map<String,Object> insert(@RequestBody Map<String, Map> params){
        String msg = "";
        Map<String,Object> map = new HashMap<>();
        //数据类型转化
        Project project = Convert.mapToProject(params);
        //判断是否已有申请
        String group = params.get("user").get("group").toString();
        int param,id;
        String role;

        if(group.equals(UserGroup.GROUP_TEACHER)) {
            param=1;
            id = Integer.parseInt(params.get("user").get("tid").toString());
        }
        else {
            param=0;
            id = Integer.parseInt(params.get("user").get("sid").toString());
        }
        List<Project> projects = projectService.selectByUserIdAndRole(id, params.get("user").get("role").toString());
        if(projects.size()!=0) {
            msg = "请勿重复提交";
            map.put("msg",msg);
            return map;
        }
        //提交申请表单
        boolean result = projectService.startProject(project,param);
        if(result){
            msg = "申请成功";

        }else{
            msg = "申请失败";
        }
        map.put("msg",msg);
        return map;
    }
    /**
     * 辅导员/系主任查找待审批申请
     * 1、获取登录用户信息（ID、学院）
     * 2、activiti根据用户组查找任务
     * 3、通过任务获取busiknessID
     * 4、通过businessID查找project表获取当前所在学院
     * 5、学院相同，拾取任务，返回任务列表
     * @param id
     * @return
     */
    @RequestMapping(value="/user/project/search/{id}/{page}/{size}")
    public Page seacherTasks(@PathVariable("id") int  id
            , @PathVariable("page") int pageNum,
                             @PathVariable("size") int limit){
        log.info("查询项目申请任务");
        List<ProjectTask> list = projectService.searchTasks(id);
        PageUtils pageUtils = new PageUtils();
        Page page = pageUtils.resultPage(list,pageNum,limit);
        return page;

    }
    /**
     * 教师审批
     * 1、获得审批表单
     * 2、进行封装
     * 3、通过processInstanceID获得对应任务进行审批
     * 4、获得任务ID，封装至examine
     * 5、封装至examine
     */
    @RequestMapping(value="/user/project/projectCheck",method = RequestMethod.POST)
    @ResponseBody
    public String projectCheck(@RequestBody Map<String,Map> params){
        System.out.println("收到的请假单审批结果："+params.get("data").toString());
        Examine projectCheck = Convert.formToHolidayCheck(params.get("data"));
        System.out.println("转换后的请假单审批结果"+projectCheck);
        projectService.project_Check(projectCheck);
        String msg = "审批完成";
        return msg;
    }
    /**
     * 查找申请结果
     * @param
     * @return
     */
    @RequestMapping(value="/user/project/result/{tid}/{college}/{profession}/{param}/{page}/{size}")
    @ResponseBody
    public Page selectProject(@PathVariable("tid") int tid,
                            @PathVariable("college") String college,@PathVariable("profession") String profession,
                              @PathVariable("param") int param,
                              @PathVariable("page") int pageNum,@PathVariable("size") int limit){
        log.info("查找学院:{}",college);
        if(college.equals("null")){
            log.info("college==null");
            college=null;
        }
        if(profession.equals("null")){
            log.info("profession==null");
            profession=null;
        }
        List<ProjectTask> holidayByClasses = projectService.selectResult(tid,college,profession,param);
        log.info("selectMajor.size()={}",holidayByClasses.size());
        PageUtils pageUtils = new PageUtils();
        Page page = pageUtils.resultPage(holidayByClasses,pageNum,limit);
        return page;
    }

    /**
     * 个人查询正在执行的请假进度
     * 1、根据用户名及processDefinitionKey查询历史流程实例
     * 2、判断该流程是否完成
     * 3、返回未完成流程 流程实例ID
     * 4、查询业务表封装数据返回给前端请假表内容
     * /test/{page}/{size}
     */
    @RequestMapping(value="/user/project/userSearch/{uid}/{role}")
    @ResponseBody
    public Map<String, List> userSearch(@PathVariable("uid")int userId,@PathVariable("role")String role){
        System.out.println("进入方法");
        Map<String, List> listMap = new HashMap<>();
        //查询正在执行的流程
        List<ProjectProcess> result = new ArrayList<>();
        List<ProjectProcess> projectProcess = projectService. userSearch(userId,role);
        for (ProjectProcess item:projectProcess) {
            result.add(item);
        }
        listMap.put("project",result);
        return listMap;
    }
    @RequestMapping(value="/user/project/history/{userId}/{role}/{page}/{size}")
    @ResponseBody
    public Page history(@PathVariable("userId") int userId,@PathVariable("role") String role
            ,@PathVariable("page") int pageNum,
                        @PathVariable("size") int limit){
        List<ProjectHistory> projectHistories = projectService.projectHistory(userId, role);
        PageUtils pageUtils = new PageUtils();
        Page page = pageUtils.resultPage(projectHistories,pageNum,limit);
        return page;
    }
}
