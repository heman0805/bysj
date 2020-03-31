package com.heman.bysj.controller;

import com.heman.bysj.activiti.Activiti_Holiday;
import com.heman.bysj.entity.HolidayProgress;
import com.heman.bysj.entity.HolidayTask;
import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.LeaveRecord;
import com.heman.bysj.jooqService.TeacherDao;
import com.heman.bysj.service.HolidayService;
import com.heman.bysj.service.LeaveService;
import com.heman.bysj.service.UserService;
import com.heman.bysj.utils.Convert;
import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class HolidayController {

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private HolidayService holidayService;
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
    @RequestMapping(value="/user/holiday/insert",method = RequestMethod.POST)
    public Map<String,Object> holiday(@RequestBody Map<String,Map> params){
        String msg = "";
        Map<String,Object> map = new HashMap<>();
        //数据类型转化
        Map<String,Object> form = params.get("form");
        Map<String,Object> user = params.get("user");
        Holiday holiday = Convert.formToHoliday(form,user);
        //根据用户角色封装用户
        /*if(user.get("role").toString().equals("学生")){
            Student student = Convert.getStudent(user);
        }else if(user.get("role").toString().equals("教师")){
            Teacher teacher = Convert.getTeacher(user);
        }else{
            msg = "登录信息有误";
            map.put("msg",msg);
            return map;
        }*/

        //提交请假表单
        boolean result = holidayService.startHoliday(holiday);
        if(result){
            msg = "请假申请成功";

        }else{
            msg = "请假申请失败";
        }
        map.put("msg",msg);
        return map;
       /* //启动请假流程实例
        activiti_holiday.startInstance(holiday);
        //完成请假单填写
        activiti_holiday.completeWrite(holiday);*/
    }
    /**
     * 个人查询正在执行的请假进度
     * 1、根据用户名及processDefinitionKey查询历史流程实例
     * 2、判断该流程是否完成
     * 3、返回未完成流程 流程实例ID
     * 4、查询业务表返回给前端请假表内容
     * /test/{page}/{size}
     */
    @RequestMapping(value="/user/holiday/userSearch/{uid}/{role}")
    @ResponseBody
   // public  Map<String,Object> userSearch(@RequestBody Map<String,Map> params){
    public Map<String, List> userSearch(@PathVariable("uid")String userId,@PathVariable("role")String role){
        System.out.println("进入方法");
        //查询正在执行的请假流程
        Map<String, List> listMap = new HashMap<>();
        List<HolidayProgress> holidays = new ArrayList<>();
        List<String> run = holidayService.userSearch(userId, role).get("run");
        //查询业务表得到详细请假信息
        for (String processInstanceId:run) {
            Holiday h = holidayService.selectHolidayByProcessInstanceId(processInstanceId);
            if(h!=null) {
                HolidayProgress holidayProgress = Convert.holidayToHolidayProgress(h);
                holidays.add(holidayProgress);
            }
        }
        listMap.put("holiday",holidays);
        return listMap;
    }

    /**
     * 教师查询待办请假业务
     * 1、查询未拾取任务进行拾取
     * 2、查询所有所属任务
     * @return
     */
    /*@RequestMapping(value="/user/holiday/teacherSearch/{uid}/{role}")*/
    @RequestMapping(value="/user/holiday/teacherSearch/{tid}")
    @ResponseBody
    public Map<String,Object> teacherSearchList(@PathVariable("tid") int tid){
        /*Teacher teacher = new Teacher();
        teacher.setTid(Integer.parseInt(tid));
        teacher.setCollege(college);
        teacher.setProfession(profession);
        teacher.setGroup(group);*/
        Teacher teacher = userService.selectTeacherById(tid).into(Teacher.class);
        System.out.println("登录教师ID："+tid+"，登录教师所在学院："+teacher.getCollege()+"，登录教师所在专业："+teacher.getProfession()+"，登录教师所在分组："+teacher.getGroup());
        Map<String,Object> map = new HashMap<>();
        List<HolidayTask> holidayTasks = holidayService.teacherSeacherTaskList(teacher);
        map.put("holidayTasks",holidayTasks);
        return map;
    }


    /**
     * 通过tid查找请假列表
     */
    @RequestMapping(value="/api/leave/findLeaveList")
    @ResponseBody
    public Object leaveList(@RequestBody Map<String,Map> params){
        int tid = 4;
        JSONObject json = new JSONObject() ;
        List<LeaveRecord> list = leaveService.leaveList(tid);
        for (LeaveRecord l:list) {
            System.out.print(l.toString());
        }
       return  json.put("list",list);

    }
    /**
     * 通过lid查找具体请假信息
     */
    @RequestMapping(value="/api/leave/findLeaveByLid")
    @ResponseBody
    public Object findLeaveByLid(){
        int lid = 1;
        JSONObject json = new JSONObject() ;
        LeaveRecord list = leaveService.selectLeaveByLid(lid);
        System.out.print(list.toString());

        return  json.put("list",list);

    }
    /**
     * 审批函数
     */
    @RequestMapping(value="/api/leave/updateLeaveByLid")
    @ResponseBody
    public void updateLeaveByLid(){
        int lid = 1;
        int result = 1;
        String remark = "同意请假申请";
        int re = leaveService.updateLeaveByLid(lid,result,remark);
        System.out.print(re);


    }
}
