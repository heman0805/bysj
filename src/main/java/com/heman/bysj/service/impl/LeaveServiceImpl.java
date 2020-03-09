package com.heman.bysj.service.impl;

import com.heman.bysj.mapper.LeaveMapper;
import com.heman.bysj.mapper.StudentMapper;
import com.heman.bysj.mapper.TeacherMapper;
import com.heman.bysj.model.entity.Leave;
import com.heman.bysj.model.entity.Student;
import com.heman.bysj.model.entity.Teacher;
import com.heman.bysj.model.enums.Role;
import com.heman.bysj.model.enums.TeacherPosition;
import com.heman.bysj.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    private Long THREE_DAYS = 3*24*60*60*1000l;

    /**
     * 1、根据参数（param）判断请假人为 学生/教师
     * 2、若请假不超过3天，直接由 辅导员/系主任 审批
     * 3、若请假超过3天，则由 书记/院长 审批
     * @param leave
     * @param param
     */
    @Override
    public String insertIntoLeave(Leave leave , String param) {
        //根据请假人角色 学生/教师
        if(param.equals("student")){
            Student student = studentMapper.selectById(leave.getNid());//获取请假学生信息
            if(student==null){
                log.info("当前请假用户不存在");
                return "fail";
            }
            if(leave.geteTime().getTime()-leave.getsTime().getTime()<THREE_DAYS){//请假时间少于3天
                leave.setTid(student.getTid());
            }else{//请假时间超过3天由书记进行审批(职位+专业)
                String profession = student.getProfession();//获取学生所在专业名称
                Teacher teacher = teacherMapper.selectByProfessionAndPosition(profession, TeacherPosition.SHU_JI);
                leave.setTid(teacher.getTid()); //设置审批教师ID为书记ID
            }
            leave.setrole(Role.STUDENT);
            leaveMapper.insertIntoLeave(leave);
        }

        else if(param.equals("teacher")){ //请假人为教师，查教师表获取其上级领导信息
            Teacher teacher = teacherMapper.selectById(leave.getNid());//根据ID获取请假人信息
            if(teacher==null){
                log.info("该用户不存在");
                return "fail";
            }
            if(leave.geteTime().getTime()-leave.getsTime().getTime()<THREE_DAYS){//请假时间小于3天
                Teacher xi_zhu_ren = teacherMapper.selectByProfessionAndPosition(teacher.getProfession(),TeacherPosition.XI_ZHU_REN);
                leave.setTid(xi_zhu_ren.getTid());
            }else{
                Teacher yuan_zhang = teacherMapper.selectByProfessionAndPosition(teacher.getProfession(),TeacherPosition.YUAN_ZHANG);
                leave.setTid(yuan_zhang.getTid());
            }
            leave.setrole(Role.TEACHER);
            leaveMapper.insertIntoLeave(leave);
        }
        return "success";
    }
}
