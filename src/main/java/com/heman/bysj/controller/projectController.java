package com.heman.bysj.controller;

import com.heman.bysj.entity.ProjectTask;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Project;
import com.heman.bysj.service.ProjectService;
import com.heman.bysj.utils.Convert;
import com.heman.bysj.utils.Page;
import com.heman.bysj.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        //提交申请表单

        boolean result = projectService.startProject(project);
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


}
