package com.heman.bysj.utils;

import com.heman.bysj.entity.HolidayProgress;
import com.heman.bysj.entity.HolidayTask;
import com.heman.bysj.entity.MajorTask;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class Convert {

    static float oneDay = 24*60*60*1000f;

    public static Holiday formToHoliday(Map<String,Object> map,Map<String,Object> user){
        Holiday holiday = new Holiday();
        String role = user.get("role").toString();
        String userId = role.equals("学生")? "sid" :"tid";
        Timestamp beginTime = Timestamp.valueOf(map.get("beginTime").toString());
        Timestamp endTime = Timestamp.valueOf(map.get("endTime").toString());

        holiday.setFormid(UUID.randomUUID().toString());//设置表单ID，主键
        holiday.setUserid(Integer.parseInt(user.get(userId).toString()));
        holiday.setRole(role);
        holiday.setBegintime(beginTime);
        holiday.setEndtime(endTime);
        holiday.setVacationtype(map.get("vacationType").toString());
        holiday.setReason(map.get("reason").toString());
        holiday.setProcessstatus(0);//设置流程状态为申请
        holiday.setCreatetime(new Timestamp(System.currentTimeMillis()));
        holiday.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        Double d = Math.ceil((endTime.getTime()-beginTime.getTime())/oneDay);
        Integer day = Integer.parseInt(new java.text.DecimalFormat("0").format(d));
        holiday.setDays(day);
        return holiday;
    }
    public static Student getStudent(Map<String,Object> user){
        Student student = new Student();

        student.setSid(Integer.parseInt(user.get("sid").toString()));
        student.setName(user.get("name").toString());
        student.setUsername(user.get("username").toString());
        student.setPassword(user.get("password").toString());
        student.setGrade(Integer.parseInt(user.get("grade").toString()));
        student.setProfession(user.get("profession").toString());
        student.setCollege(user.get("college").toString());
        student.setClass_(user.get("class_").toString());
        student.setTid(Integer.parseInt(user.get("tid").toString()));
        student.setRole(user.get("role").toString());
        student.setGroup(user.get("group").toString());

        return student;

    }

    public static Teacher getTeacher(Map<String,Object> user){
        Teacher teacher = new Teacher();

        teacher.setTid(Integer.parseInt(user.get("tid").toString()));
        teacher.setName(user.get("name").toString());
        teacher.setUsername(user.get("username").toString());
        teacher.setPassword(user.get("password").toString());
        teacher.setGrade(Integer.parseInt(user.get("grade").toString()));
        teacher.setProfession(user.get("profession").toString());
        teacher.setCollege(user.get("college").toString());
        teacher.setPosition(user.get("position").toString());
        teacher.setRole(user.get("role").toString());
        teacher.setGroup(user.get("group").toString());

        return teacher;

    }
    /**
     * @param timestamp  参数格式timestamp
     * @return
     */
    private static Timestamp formDateToTimeStamp(String timestamp){

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            format.setLenient(false);
            //要转换字符串 str_test
            try {
                Timestamp ts = new Timestamp(format.parse(timestamp).getTime());
                return ts;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        return null;
        }
    public static HolidayProgress holidayToHolidayProgress(Holiday holiday){
        HolidayProgress holidayProgress = new HolidayProgress();
        holidayProgress.setBeginTime(new Date(holiday.getBegintime().getTime()));;
        holidayProgress.setCreateTime(new Date(holiday.getCreatetime().getTime()));;
        holidayProgress.setEndTime(new Date(holiday.getEndtime().getTime()));;
        holidayProgress.setDays(holiday.getDays());
        holidayProgress.setProcessInstanceId(holiday.getProcessinstanceid());;
        holidayProgress.setVacationType(holiday.getVacationtype());
        if(holiday.getDays()>3){
            if(holiday.getRole().equals(UserRole.STUDENT))
                holidayProgress.setProcessStatus("书记审批中");
            else holidayProgress.setProcessStatus("院长审批中");
        }else{
            if(holiday.getRole().equals(UserRole.STUDENT))
                holidayProgress.setProcessStatus("辅导员审批中");
            else holidayProgress.setProcessStatus("系主任审批中");
        }
        return holidayProgress;
    }
    public static HolidayTask HolidayToHolidayTask(Holiday holiday,String taskId,String userName,String college,String profession,String class_,int grade,int userId,String role){
        HolidayTask holidayTask = new HolidayTask();
        holidayTask.setBeginTime(new Date(holiday.getBegintime().getTime()));
        holidayTask.setEndTime(new Date(holiday.getEndtime().getTime()));
        holidayTask.setCreateTime(new Date(holiday.getCreatetime().getTime()));
        holidayTask.setDays(holiday.getDays());
        holidayTask.setProcessInstanceId(holiday.getProcessinstanceid());
        holidayTask.setReason(holiday.getReason());
        holidayTask.setVacationType(holiday.getVacationtype());
        holidayTask.setTaskId(taskId);
        holidayTask.setName(userName);
        holidayTask.setCollege(college);
        holidayTask.setProfession(profession);
        holidayTask.setClass_(class_);
        holidayTask.setGrade(grade);
        holidayTask.setUserId(userId);
        holidayTask.setRole(role);
        return holidayTask;
    }
    public static Examine formToHolidayCheck(Map<String,Object> form){
        Examine holidayCheck = new Examine();
        holidayCheck.setCheckid(UUID.randomUUID().toString());
        holidayCheck.setCheckresult(form.get("checkResult").toString());
        holidayCheck.setChecktime(new Timestamp(System.currentTimeMillis()));
        if(form.get("opinion")==null)
            holidayCheck.setOpinion(null);
        else holidayCheck.setOpinion(form.get("opinion").toString());
        holidayCheck.setProcessinstanceid(form.get("processInstanceId").toString());
        holidayCheck.setRole(form.get("role").toString());
        holidayCheck.setUserid((int)form.get("userId"));
        return holidayCheck;
    }

    public static Changemajors mapToChangeMajors(Map<String,Map> params){
        Map<String,Object> form = params.get("form");
        Map<String,Object> user = params.get("user");
        Student student = getStudent(user);
        Changemajors changemajors = new Changemajors();

        if(form.get("post")!=null){
            changemajors.setPost(form.get("post").toString());
        }
        if(form.get("society")!=null){
            changemajors.setSociety(form.get("society").toString());
        }
        changemajors.setCid(UUID.randomUUID().toString());
        changemajors.setCreatetime(new Timestamp(System.currentTimeMillis()));
        changemajors.setCurrentclass(student.getClass_());
        changemajors.setCurrentprofession(student.getProfession());
        changemajors.setCurrentcollege(student.getCollege());
        changemajors.setGpa(Double.valueOf(form.get("gpa").toString()));
        changemajors.setNewcollege(form.get("newCollege").toString());
        changemajors.setNewprofession(form.get("newProfession").toString());
        changemajors.setProcessstatus(0);
        changemajors.setRank(form.get("rank").toString());
        changemajors.setReason(form.get("reason").toString());
        changemajors.setUserid(student.getSid());
        changemajors.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        if(form.get("contest").toString()!=null)
            changemajors.setContest(form.get("contest").toString());
        return changemajors;
    }
    public static MajorTask changeMajorToMajorTask(String name,String role,int userId,String class_,String taskId,Changemajors changemajors){
        MajorTask majorTask = new MajorTask();

        majorTask.setPost(changemajors.getPost());
        majorTask.setSociety(changemajors.getSociety());
        majorTask.setUserId(userId);
        majorTask.setRole(role);
        majorTask.setClass_(class_);
        majorTask.setContest(changemajors.getContest());
        majorTask.setCreateTime(changemajors.getCreatetime());
        majorTask.setCurrentProfession(changemajors.getCurrentprofession());
        majorTask.setGpa(changemajors.getGpa());
        majorTask.setName(name);
        majorTask.setNewCollege(changemajors.getNewcollege());
        majorTask.setNewProfession(changemajors.getNewprofession());
        majorTask.setProcessInstanceId(changemajors.getProcessinstanceid());
        majorTask.setRank(changemajors.getRank());
        majorTask.setReason(changemajors.getReason());
        majorTask.setTaskId(taskId);
        return majorTask;
    }
}
