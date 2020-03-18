package com.heman.bysj.service.impl;

import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.jooqService.ChangeMajorsDao;
import com.heman.bysj.service.ChangeMajorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChangeMajorsServiceImpl implements ChangeMajorsService {

    @Autowired
    private ChangeMajorsDao changeMajorsDao;

    @Override
    public int insert(ChangemajorsRecord changemajorsRecord) {
        return changeMajorsDao.insert(changemajorsRecord);
    }

    @Override
    public int updateState(int cid, String state) {
        return changeMajorsDao.updateState(cid,state);
    }

    @Override
    public int updateResult(int cid, int result, String post, String refuseReason) {
        return changeMajorsDao.updateResult(cid,result,post,refuseReason);
    }

    @Override
    public ChangemajorsRecord selectBySid(int sid) {
        return changeMajorsDao.selectBySid(sid);
    }
}
