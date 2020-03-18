package com.heman.bysj.controller;

import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.service.ChangeMajorsService;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangeMajorsController {

    @Autowired
    private ChangeMajorsService changeMajorsService;

    @RequestMapping(value="/api/ChangeMajors/insert")
    @ResponseBody
    void insert(){
        ChangemajorsRecord changemajorsRecord = new ChangemajorsRecord();
        changemajorsRecord.setSid(1);
        changemajorsRecord.setApplyreason("申请转专业");
        changemajorsRecord.setCurrentprofession("通信工程");
        changemajorsRecord.setNewprofession("计算机学院");
        changemajorsRecord.setGpa(3.5);
        changemajorsRecord.setRank("前5%");
        changemajorsRecord.setState("当前所在专业教务处审核中");

        changeMajorsService.insert(changemajorsRecord);
    }
    @RequestMapping(value="/api/ChangeMajors/updateState")
    @ResponseBody
    void updateState(){
        changeMajorsService.updateState(3,"当前所在专业院长审核中");

    }
    @RequestMapping(value="/api/ChangeMajors/updateResult")
    @ResponseBody
    void updateResult(){
        int cid = 3;
        int result =0;
        String post = "当前所在学院院长";
        String refuseReason = "条件不符合";

        changeMajorsService.updateResult(cid,result,post,refuseReason);
    }
    @RequestMapping(value="/api/ChangeMajors/selectBySid")
    @ResponseBody
    void selectBySid(){
        ChangemajorsRecord changemajorsRecord = changeMajorsService.selectBySid(1);
        System.out.print(changemajorsRecord);
    }
}
