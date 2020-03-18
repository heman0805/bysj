package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.jooqService.ChangeMajorsDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import static com.heman.bysj.jooq.tables.Changemajors.CHANGEMAJORS;
@Slf4j
@Service
public class ChangeMajorsDaoImpl implements ChangeMajorsDao {

    @Autowired
    private DSLContext dslContext;

    /**
     * 插入学生转专业申请表
     * @param changemajorsRecord
     * @return
     */
    @Override
    public int insert(ChangemajorsRecord changemajorsRecord) {
        return dslContext
                .insertInto(CHANGEMAJORS)
                .set(changemajorsRecord)
                .execute();
    }

    /**
     * 更新当前申请状态
     * @param cid
     * @param state
     * @return
     */
    @Override
    public int updateState(int cid, String state) {
        return dslContext.update(CHANGEMAJORS)
                .set(CHANGEMAJORS.STATE,state)
                .where(CHANGEMAJORS.CID.eq(cid))
                .execute();
    }

    /**
     * 更新申请结果
     * @param cid
     * @param result
     * @param post
     * @param refuseReason
     * @return
     */
    @Override
    public int updateResult(int cid, int result, String post, String refuseReason) {
        return dslContext.update(CHANGEMAJORS)
                .set(CHANGEMAJORS.RESULT,result)
                .set(CHANGEMAJORS.POST,post)
                .set(CHANGEMAJORS.REFUSEREASON,refuseReason)
                .where(CHANGEMAJORS.CID.eq(cid))
                .execute();
    }

    /**
     * 通过ID查找转专业申请
     * @param sid
     * @return
     */
    @Override
    public ChangemajorsRecord selectBySid(int sid) {
        return dslContext.selectFrom(CHANGEMAJORS)
                .where(CHANGEMAJORS.SID.eq(sid))
                .fetchOne();
    }
}
