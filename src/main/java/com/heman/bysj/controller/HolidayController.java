package com.heman.bysj.controller;

import com.heman.bysj.jooq.tables.records.LeaveRecord;
import com.heman.bysj.service.LeaveService;
import org.jooq.JSON;
import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class HolidayController {
    @Autowired
    private LeaveService leaveService;

/*
    public void holiday(@RequestBody ){

    }*/
    /**
     * 请假
     */
    @RequestMapping(value="/api/leave/leave")
    @ResponseBody
    public void insertIntoLeave(){
        LeaveRecord leave = new LeaveRecord();
        leave.setNid(5);
        leave.setNtime(new Timestamp(System.currentTimeMillis()));
        leave.setStime(new Timestamp(System.currentTimeMillis()));
        //+3*24*60*60*1000l
        leave.setEtime(new Timestamp(System.currentTimeMillis()+2000));
        leave.setReason("教师请假不超过3天");
        leave.setResult(0);
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

    /**
     * 通过tid查找请假列表
     */
    @RequestMapping(value="/api/leave/findLeaveList")
    @ResponseBody
    public Object leaveList(){
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
