package com.heman.bysj.controller;

import com.heman.bysj.model.entity.Leave;
import com.heman.bysj.service.LeaveService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
public class StudentLeaveController {
    @Autowired
    private LeaveService leaveService;



    @RequestMapping(value="/api/leave")
    @ResponseBody
    public void insertIntoLeave(){
        Leave leave = new Leave();
        leave.setNid(1);
        leave.setnTime(new Timestamp(System.currentTimeMillis()));
        leave.setsTime(new Timestamp(System.currentTimeMillis()));
        //+3*24*60*60*1000l
        leave.seteTime(new Timestamp(System.currentTimeMillis()+2000+3*24*60*60*1000l));
        /*leave.setPosition(1);*/
        leave.setReason("教师请假超过3天");
        leave.setType("事假");
        //按照不同类型参数调用leaveService
        String param = "teacher";
        if(param.equals("student")) {
            leaveService.insertIntoLeave(leave, "student");
        }else if(param.equals("teacher")) {
            leaveService.insertIntoLeave(leave, "teacher");
        }else{
            System.out.print("参数错误");
        }
    }
}
