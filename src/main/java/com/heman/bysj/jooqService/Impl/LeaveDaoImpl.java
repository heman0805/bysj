package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.LeaveRecord;
import com.heman.bysj.jooqService.LeaveDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.heman.bysj.jooq.tables.Leave.LEAVE;


@Service
public class LeaveDaoImpl  implements LeaveDao {

    @Autowired
    private DSLContext dslContext;
   // @Override
    public int insertIntoLeave(LeaveRecord leave) {
        return dslContext
                .insertInto(LEAVE)
                .set(leave)
                .execute();
    }
}
