package com.heman.bysj.service.impl;

import com.heman.bysj.jooq.tables.records.LeaveRecord;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.LeaveDao;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.jooqService.TeacherDao;
import com.heman.bysj.enums.Role;
import com.heman.bysj.enums.TeacherPosition;
import com.heman.bysj.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;

    private Long THREE_DAYS = 3*24*60*60*1000l;

    /**
     * 1、根据参数（param）判断请假人为 学生/教师
     * 2、若请假不超过3天，直接由 辅导员/系主任 审批
     * 3、若请假超过3天，则由 书记/院长 审批
     * @param leave
     * @param param
     */
    @Override
    public String insertLeave(LeaveRecord leave , String param) {
        //根据请假人角色 学生/教师
        if(param.equals("student")){
            StudentRecord student = studentDao.selectById(leave.getNid());//获取请假学生信息
            log.info("当前请假学生的姓名为：{}",student.getName());
            if(student==null){
                log.info("当前请假用户不存在");
                return "fail";
            }
            Long endTime = leave.getEtime().getTime();
            Long startTime = leave.getStime().getTime();
            if(endTime-startTime<THREE_DAYS){//请假时间少于3天
                leave.setTid(student.getTid());
                log.info("请假时间小于三天");
            }else{//请假时间超过3天由书记进行审批(职位+专业)
                log.info("请假时间超过三天");
                String profession = student.getProfession();//获取学生所在专业名称
                TeacherRecord teacher = teacherDao.selectByProfessionAndPosition(profession, TeacherPosition.SHU_JI);
                leave.setTid(teacher.getTid()); //设置审批教师ID为书记ID
                log.info("审批教师为：{}",teacher.getName());
            }
            leave.setRole(Role.STUDENT);
            int result = leaveDao.insertIntoLeave(leave);
            log.info("插入结果：{}",result);

        }

        else if(param.equals("teacher")){ //请假人为教师，查教师表获取其上级领导信息
            TeacherRecord teacher = teacherDao.selectById(leave.getNid());//根据ID获取请假人信息
            log.info("当前请假教师为：{}",teacher.getName());
            if(teacher==null){
                log.info("该用户不存在");
                return "fail";
            }
            Long endTime = leave.getEtime().getTime();
            Long startTime = leave.getStime().getTime();
            if(endTime-startTime<THREE_DAYS){//请假时间小于3天
                log.info("请假时间小于三天");
                TeacherRecord xi_zhu_ren = teacherDao.selectByProfessionAndPosition(teacher.getProfession(),TeacherPosition.XI_ZHU_REN);
                leave.setTid(xi_zhu_ren.getTid());
                log.info("审批教师为：{}",xi_zhu_ren.getName());
            }else{
                log.info("请假时间超过三天");
                TeacherRecord yuan_zhang = teacherDao.selectByProfessionAndPosition(teacher.getProfession(),TeacherPosition.YUAN_ZHANG);
                leave.setTid(yuan_zhang.getTid());
                log.info("审批教师为：{}",yuan_zhang.getName());
            }
            leave.setRole(Role.TEACHER);
            int result = leaveDao.insertIntoLeave(leave);
            log.info("插入结果为：{}",result);
        }
        return "success";
    }


}
