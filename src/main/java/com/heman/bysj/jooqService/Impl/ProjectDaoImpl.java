package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.pojos.Project;
import com.heman.bysj.jooq.tables.records.ProjectRecord;
import com.heman.bysj.jooqService.ProjectDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.heman.bysj.jooq.tables.Project.PROJECT;
@Slf4j
@Service
public class ProjectDaoImpl implements ProjectDao {
    @Autowired
    private DSLContext dslContext;

    @Override
    public int insert(ProjectRecord project) {
        return dslContext.insertInto(PROJECT)
                .set(project)
                .execute();

    }

    @Override
    public ProjectRecord selectByProcessInstanceId(String processInstanceId) {
        return dslContext.selectFrom(PROJECT)
                .where(PROJECT.PROCESSINSTANCEID.eq(processInstanceId))
                .fetchOne();
    }

    @Override
    public void complete(String proceInstanceId) {
        dslContext.update(PROJECT)
                .set(PROJECT.PROCESSSTATUS,PROJECT.PROCESSSTATUS.add(1))
                .set(PROJECT.UPDATETIME,new Timestamp(System.currentTimeMillis()))
                .where(PROJECT.PROCESSINSTANCEID.eq(proceInstanceId))
                .execute();
    }

    @Override
    public void stopRunProcessInstance(String processInstanceId) {
        dslContext.update(PROJECT)
                .set(PROJECT.PROCESSSTATUS,6)
                .set(PROJECT.UPDATETIME,new Timestamp(System.currentTimeMillis()))
                .where(PROJECT.PROCESSINSTANCEID.eq(processInstanceId))
                .execute();
    }

    @Override
    public List<ProjectRecord> selectByUserIdAndRole(int id, String role) {
        return dslContext.selectFrom(PROJECT)
                .where(PROJECT.USERID.eq(id))
                .and(PROJECT.ROLE.eq(role))
                .and(PROJECT.PROCESSSTATUS.notEqual(5))
                .and(PROJECT.PROCESSSTATUS.notEqual(6))
                .fetch();
    }

    @Override
    public List<ProjectRecord> selectByRoleAndProcessStatus(String role,int processStatus) {
        return dslContext.selectFrom(PROJECT)
                .where(PROJECT.ROLE.eq(role))
                .and(PROJECT.PROCESSSTATUS.eq(processStatus))
                .fetch();
    }

    @Override
    public List<ProjectRecord> selectByProcessStatus(int processStatus) {
        return dslContext.selectFrom(PROJECT)
                .where(PROJECT.PROCESSSTATUS.eq(processStatus))
                .fetch();
    }

    @Override
    public List<ProjectRecord> selectByUserId(int id) {
        return dslContext.selectFrom(PROJECT)
                .where(PROJECT.USERID.eq(id))
                .fetch();
    }
}
