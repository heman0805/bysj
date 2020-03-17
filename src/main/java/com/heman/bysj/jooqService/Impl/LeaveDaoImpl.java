package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.LeaveRecord;
import com.heman.bysj.jooqService.LeaveDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.heman.bysj.jooq.tables.Leave.LEAVE;

@Slf4j
@Service
public class LeaveDaoImpl  implements LeaveDao {

    @Autowired
    private DSLContext dslContext;

    /**
     * 插入请假表
     * @param leave
     * @return
     */
    @Override
    public int insertIntoLeave(LeaveRecord leave) {
        return dslContext
                .insertInto(LEAVE)
                .set(leave)
                .execute();
    }

    /**
     * 通过lid查找
     * @param lid
     * @return
     */
    @Override
    public LeaveRecord selectByLid(int lid) {
        return dslContext.selectFrom(LEAVE)
                .where(LEAVE.LID.eq(lid))
                .fetchOne();
    }

    /**
     *通过审批人ID查询请假List
     * @param tid
     * @return
     */
    @Override
    public List<LeaveRecord> selectListByTid(int tid) {
        return dslContext.selectFrom(LEAVE)
                .where(LEAVE.TID.eq(tid))
                .fetch();
    }

    /**
     * result : 0-审批中 1-通过 2-拒绝
     * @param result
     * @param remark
     * @return
     */
    @Override
    public int updateResultAndRemark(int lid, int result, String remark) {
        return dslContext.update(LEAVE)
                .set(LEAVE.RESULT,result)
                .set(LEAVE.REMARK,remark)
                .where(LEAVE.LID.eq(lid))
                .execute();
    }
}
