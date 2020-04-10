package com.heman.bysj.controller;

import com.heman.bysj.entity.MajorTask;
import com.heman.bysj.jooq.tables.pojos.Changemajors;
import com.heman.bysj.jooq.tables.pojos.Examine;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.service.ChangeMajorsService;
import com.heman.bysj.service.UserService;
import com.heman.bysj.utils.Convert;
import lombok.extern.slf4j.Slf4j;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ChangeMajorsController {

    @Autowired
    private ChangeMajorsService changeMajorsService;
    @Autowired
    private UserService userService;
    /**
     * 学生请假申请
     * 1、根据参数获得请假表单、学生信息
     * 2、启动请假流程实例
     * 3、完成填写请假单任务
     * 4、插入请假表及请假审批表
     * @param params
     */
    @RequestMapping(value="/user/major/insert",method = RequestMethod.POST)
    public Map<String,Object> insert(@RequestBody Map<String, Map> params){
        String msg = "";
        Map<String,Object> map = new HashMap<>();
        //数据类型转化
        Changemajors changemajors = Convert.mapToChangeMajors(params);
        //提交请假表单
        boolean result = changeMajorsService.startMajor(changemajors);
        if(result){
            msg = "请假申请成功";

        }else{
            msg = "请假申请失败";
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 学院教务办查找待审批申请
     * 1、获取登录用户信息（ID、学院）
     * 2、activiti根据用户组查找任务
     * 3、通过任务获取businessID
     * 4、通过businessID查找changeMajors表获取当前所在学院
     * 5、学院相同，拾取任务，返回任务列表
     * @param tid
     * @return
     */
    @RequestMapping(value="/user/major/search/{id}")
    public List<MajorTask> seacherTasks(@PathVariable("id") int  tid){
        log.info("查询转专业任务");
        return changeMajorsService.searchTasks(tid);

    }

    /**
     * 通过processInstanceID查找Holiday信息
     * @return
     */
    @RequestMapping(value="/user/major/searchByProcessInstanceId/{processInstanceId}")
    @ResponseBody
    public MajorTask selectHolidayByProcessId(@PathVariable("processInstanceId") String processInstanceId){
        Changemajors changemajors = changeMajorsService.selectHolidayByProcessInstanceId(processInstanceId);
        MajorTask majorTask = new MajorTask();
        Student student = userService.selectStudentById(changemajors.getUserid());
        majorTask = Convert.changeMajorToMajorTask(student.getName(),student.getRole(),student.getSid(),student.getClass_(),null,changemajors);
        return majorTask;
    }
    /**
     * 教师审批
     * 1、获得审批表单
     * 2、进行封装
     * 3、通过processInstanceID获得对应任务进行审批
     * 4、获得任务ID，封装至examine
     * 5、封装至examine
     */
    @RequestMapping(value="/user/major/majorCheck",method = RequestMethod.POST)
    @ResponseBody
    public String holiday_check(@RequestBody Map<String,Map> params){
        System.out.println("收到的请假单审批结果："+params.get("data").toString());
        Examine majorCheck = Convert.formToHolidayCheck(params.get("data"));
        System.out.println("转换后的请假单审批结果"+majorCheck);
        changeMajorsService.major_Check(majorCheck);
        String msg = "审批完成";
        return msg;
    }

}
