package com.heman.bysj.controller;

import com.heman.bysj.jooq.tables.records.LeaveRecord;
import com.heman.bysj.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class StudentLeaveController {
    @Autowired
    private LeaveService leaveService;



    @RequestMapping(value="/api/leave")
    @ResponseBody
    public void insertIntoLeave(){
        LeaveRecord leave = new LeaveRecord();
        leave.setNid(5);
        leave.setNtime(new Timestamp(System.currentTimeMillis()));
        leave.setStime(new Timestamp(System.currentTimeMillis()));
        //+3*24*60*60*1000l
        leave.setEtime(new Timestamp(System.currentTimeMillis()+2000));
        /*leave.setPos                ition(1);*/
        leave.setReason("学生请假不超过3天");
        leave.setType("事假");
        //按照不同类型参数调用leaveService
        String param = "teacher";
        if(param.equals("student")) {
           leaveService.insertLeave(leave, "student");
        }else if(param.equals("teacher")) {
            leaveService.insertLeave(leave, "teacher");
        }else{
            System.out.print("参数错误");
        }
    }
}
