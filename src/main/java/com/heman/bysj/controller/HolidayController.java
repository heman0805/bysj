package com.heman.bysj.controller;

import com.heman.bysj.activiti.Activiti_Holiday;
import com.heman.bysj.entity.HolidayByClass;
import com.heman.bysj.entity.HolidayHistory;
import com.heman.bysj.entity.HolidayProgress;
import com.heman.bysj.entity.HolidayTask;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.pojos.HolidayCheck;
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

        Teacher teacher = userService.selectTeacherById(tid).into(Teacher.class);
        System.out.println("登录教师ID："+tid+"，登录教师所在学院："+teacher.getCollege()+"，登录教师所在专业："+teacher.getProfession()+"，登录教师所在分组："+teacher.getGroup());
        Map<String,Object> map = new HashMap<>();
        List<HolidayTask> holidayTasks = holidayService.teacherSeacherTaskList(teacher);
        map.put("holidayTasks",holidayTasks);
        return map;
    }

    /**
     * 通过processInstanceID查找Holiday信息
     * @return
     */
    @RequestMapping(value="/user/holiday/holidayTask/{processInstanceId}")
    @ResponseBody
    public HolidayTask selectHolidayByProcessId(@PathVariable("processInstanceId") String processInstanceId){
        Holiday holiday = holidayService.selectHolidayByProcessInstanceId(processInstanceId);
        HolidayTask holidayTask = new HolidayTask();
        if(holiday.getRole().equals(UserRole.STUDENT)){
            Student student = userService.selectStudentById(holiday.getUserid());
            holidayTask = Convert.HolidayToHolidayTask(holiday,null,student.getUsername(),student.getCollege(),student.getProfession(),student.getClass_(),student.getGrade(),student.getSid(),student.getRole());
        }else{
            Teacher teacher = userService.selectTeacherById(holiday.getUserid()).into(Teacher.class);
            holidayTask = Convert.HolidayToHolidayTask(holiday,null,teacher.getUsername(),teacher.getCollege(),teacher.getProfession(),null,teacher.getGrade(),teacher.getTid(),teacher.getRole());
        }

        return holidayTask;
    }

    /**
     * 教师审批
     * 1、获得审批表单
     * 2、进行封装
     * 3、通过processInstanceID获得对应任务进行审批
     * 4、获得任务ID，封装至holidayCheck
     * 5、插入holidayCheck表
     */
    @RequestMapping(value="/user/holiday/holidayCheck",method = RequestMethod.POST)
    @ResponseBody
    public String holiday_check(@RequestBody Map<String,Map> params){
        System.out.println("收到的请假单审批结果："+params.get("data").toString());
        HolidayCheck holidayCheck = Convert.formToHolidayCheck(params.get("data"));
        System.out.println("转换后的请假单审批结果"+holidayCheck);
        holidayService.holiday_Check(holidayCheck);
        String msg = "审批完成";
        return msg;
    }

    /**
     * 查询个人请假历史记录
     * 1、封装用户名及角色
     * 2、根据用户名及角色查询holiday表获得相关请假数据及processInstanceID
     * 3、根据processInstanceID查询check表 获得请假结果
     * 4、返回结果
     * @return
     */
    @RequestMapping(value="/user/holiday/holidayHistory/{userId}/{role}")
    @ResponseBody
    public List<HolidayHistory> holidayHistory(@PathVariable("userId") int userId,@PathVariable("role") String role ){
        List<HolidayHistory> holidayHistorys = holidayService.holidayHistory(userId,role);
        return holidayHistorys;
    }

    /**
     * 通过专业获得班级名列表
     * @param profession
     * @return
     */
    @RequestMapping(value="/user/holiday/classes/{profession}")
    @ResponseBody
    public List<String> selectClassByProfession(@PathVariable("profession") String profession ){
        List<String> classes = holidayService.selectClassByProfession(profession);
        if(classes==null){
        }
        return classes;
    }

    /**
     * 通过学院获得专业名列表
     * @param college
     * @return
     */
    @RequestMapping(value="/user/holiday/profession/{college}")
    @ResponseBody
    public List<String> selectProfessionByCollege(@PathVariable("college") String college ){
        List<String> classes = holidayService.selectProfessionByCollege(college);
        if(classes==null){
        }
        return classes;
    }

    /**
     * 通过班级查询请假列表
     * 1、通过班级查询sid
     * 2、根据学生ID查找holiday_check表
     * 3、进行数据封装
     * @param class_
     * @return
     */
    @RequestMapping(value="/user/holiday/searchHoliday/{param}/{class}")
    @ResponseBody
    public List<HolidayByClass> selectHolidayByClass(@PathVariable("param") String param,@PathVariable("class") String class_ ){
        List<HolidayByClass> result = holidayService.selectHolidayByClass(param,class_);
        return result;
    }

    /**
     * 查找教师请假信息
     * @param
     * @return
     */
    @RequestMapping(value="/user/holiday/searchTeacherHoliday/{param}/{profession}")
    @ResponseBody
    public List<HolidayByClass> searchTeacherHoliday(@PathVariable("param") String param,@PathVariable("profession") String colpro ){
//        List<HolidayByClass> result = holidayService.selectHolidayByClass(class_);
        List<HolidayByClass> result = holidayService.searchTeacherHoliday(param,colpro);
        return result;
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
