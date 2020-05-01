package com.heman.bysj.service.impl;

import com.heman.bysj.jooq.tables.records.ClassesRecord;
import com.heman.bysj.jooqService.ClassesDao;
import com.heman.bysj.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ClassesDao classesDao;
    @Override
    public List<String> searchAllCollege() {
        Result<Record1<String>> collegeRecords = classesDao.selectAllCollege();
        List<String> colleges = setList(collegeRecords,"college");
        /*if(collegeRecords.size()==0){
            log.info("无学院");
            return null;
        }
        for (Record1<String> college:collegeRecords) {
            colleges.add(college.get("college").toString());
        }
        log.info("学院名称列表：{}",colleges.toString());*/
        return colleges;
    }

    @Override
    public List<String> searchProfessionesByCollege(String college) {
        Result<Record1<String>> professionRecords = classesDao.selectProfessionByCollege(college);
        List<String> colleges = setList(professionRecords,"profession");
        return colleges;
    }

    @Override
    public List<String> searchClassesByProfession(String profession) {
        List<ClassesRecord> classesRecords = classesDao.selectClassByProfession(profession);
        if(classesRecords.size()==0){
            return null;
        }
        List<String> classes = new ArrayList<>();
        for (ClassesRecord class_:classesRecords) {
            classes.add(class_.getClass_());
        }
        return classes;
    }

    private List<String> setList(Result<Record1<String>> records, String param){
        if(records.size()==0){
            return null;
        }
        List<String> result = new ArrayList<>();
        for (Record1<String> item:records) {
            result.add(item.get(param).toString());
        }
        log.info("查询列表：{}",result.toString());
        return result;
    }
}
