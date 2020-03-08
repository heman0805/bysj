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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    private Long THREE_DAYS = 3*24*60*60*1000l;
    @Override
    public void insertIntoLeave(Leave leave , String param) {
        //1、根据请假人信息及请假时长设置审批人信息
        if(param.equals("student")){  // 请假人为学生，查学生表获取其教师信息
            //请假时间不超过3天，直接由辅导员审批
            Student student = studentMapper.selectById(leave.getNid());
            //3、设置审批教师ID
            if(leave.geteTime().getTime()-leave.getsTime().getTime()<THREE_DAYS){
                leave.setTid(student.getTid());
            }else{//请假时间超过3天由书记进行审批(职位+专业)
                //获取学生所在专业名称
                String profession = student.getProfession();
                //根据专业及职位获取书记的ID
                Teacher teacher = teacherMapper.selectByProfessionAndPosition(profession, TeacherPosition.SHU_JI);
                //设置审批教师ID为书记ID
                leave.setTid(teacher.getTid());
            }
            leave.setrole(Role.STUDENT);
            leaveMapper.insertIntoLeave(leave);
        }

        else if(param.equals("teacher")){//请假人为教师，查教师表获取其上级领导信息
            //3、设置审批教师ID
            //根据ID获取请假人信息
            Teacher teacher = teacherMapper.selectById(leave.getNid());
            if(leave.geteTime().getTime()-leave.getsTime().getTime()<THREE_DAYS){//请假时间小于3天
                //根据请假人所在专业获取所在专业系主任信息
                Teacher xi_zhu_ren = teacherMapper.selectByProfessionAndPosition(teacher.getProfession(),TeacherPosition.XI_ZHU_REN);
                leave.setTid(xi_zhu_ren.getTid());
            }else{
                Teacher yuan_zhang = teacherMapper.selectByProfessionAndPosition(teacher.getProfession(),TeacherPosition.YUAN_ZHANG);
                leave.setTid(yuan_zhang.getTid());
            }
            leave.setrole(Role.TEACHER);
            leaveMapper.insertIntoLeave(leave);
        }
    }
}
