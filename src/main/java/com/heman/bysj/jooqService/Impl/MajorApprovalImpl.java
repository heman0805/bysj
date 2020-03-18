package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.MajorapprovalRecord;
import com.heman.bysj.jooqService.MajorApproval;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.heman.bysj.jooq.tables.Majorapproval.MAJORAPPROVAL;
import java.util.List;

@Slf4j
@Service
public class MajorApprovalImpl implements MajorApproval {

    @Autowired
    private DSLContext dslContext;

    /**
     * 插入审批表
     * @param majorapprovalRecord
     * @return
     */
    @Override
    public int insert(MajorapprovalRecord majorapprovalRecord) {
        return dslContext.insertInto(MAJORAPPROVAL)
                .set(majorapprovalRecord)
                .execute();
    }

    /**
     * 根据学生申请ID更新当前专业教务处审批状态
     * @param cid
     * @param caResult
     * @param caReason
     * @return
     */
    @Override
    public int updateCurrentAcademicOfficeByCid(int cid, String caResult, String caReason) {
        return dslContext.update(MAJORAPPROVAL)
                .set(MAJORAPPROVAL.CARESULT,caResult)
                .set(MAJORAPPROVAL.CAREASON,caReason)
                .where(MAJORAPPROVAL.CID.eq(cid))
                .execute();
    }

    /**
     * 根据学生申请ID更新当前专业院长审批状态
     * @param cid
     * @param cdResult
     * @param cdReason
     * @return
     */
    @Override
    public int updateCurrentDeanByCid(int cid, String cdResult, String cdReason) {
        return dslContext.update(MAJORAPPROVAL)
                .set(MAJORAPPROVAL.CDRESULT,cdResult)
                .set(MAJORAPPROVAL.CDREASON,cdReason)
                .where(MAJORAPPROVAL.CID.eq(cid))
                .execute();
    }

    /**
     * 根据学生申请ID更新新专业教务处审批状态
     * @param cid
     * @param naResult
     * @param naReason
     * @return
     */
    @Override
    public int updateNewAcademicOfficeByCid(int cid, String naResult, String naReason) {
        return dslContext.update(MAJORAPPROVAL)
                .set(MAJORAPPROVAL.NARESULT,naResult)
                .set(MAJORAPPROVAL.NAREASON,naReason)
                .where(MAJORAPPROVAL.CID.eq(cid))
                .execute();
    }

    /**
     * 根据学生申请ID更新新专业院长审批状态
     * @param cid
     * @param ndResult
     * @param ndReason
     * @return
     */
    @Override
    public int updateNewDeanByCid(int cid, String ndResult, String ndReason) {
        return dslContext.update(MAJORAPPROVAL)
                .set(MAJORAPPROVAL.NDRESULT,ndResult)
                .set(MAJORAPPROVAL.NDREASON,ndReason)
                .where(MAJORAPPROVAL.CID.eq(cid))
                .execute();
    }

    /**
     * 根据学生申请ID更新学校教务处审批状态
     * @param cid
     * @param aoResult
     * @param aoReason
     * @return
     */
    @Override
    public int updateAcademicOfficeByCid(int cid, String aoResult, String aoReason) {
        return dslContext.update(MAJORAPPROVAL)
                .set(MAJORAPPROVAL.AORESULT,aoResult)
                .set(MAJORAPPROVAL.AOREASON,aoReason)
                .where(MAJORAPPROVAL.CID.eq(cid))
                .execute();
    }

    /**
     * 通过审批ID进行查找
     * @param aid
     * @return
     */
    @Override
    public MajorapprovalRecord selectByAid(int aid) {
        return dslContext.selectFrom(MAJORAPPROVAL)
                .where(MAJORAPPROVAL.AID.eq(aid))
                .fetchOne();
    }

    /**
     * 通过学生申请ID进行查找
     * @param cid
     * @return
     */
    @Override
    public MajorapprovalRecord selectByCid(int cid) {
        return dslContext.selectFrom(MAJORAPPROVAL)
                .where(MAJORAPPROVAL.CID.eq(cid))
                .fetchOne();
    }

    /**
     * 通过原专业教务处名进行查找
     * 查找state=0 即处于审批中
     * @param CurrentAcademicOffice
     * @return
     */
    @Override
    public List<MajorapprovalRecord> selectByCurrentAcademicOffice(String CurrentAcademicOffice) {
        return dslContext.selectFrom(MAJORAPPROVAL)
                .where(MAJORAPPROVAL.CURRENTACADEMICOFFICE.eq(CurrentAcademicOffice))
                .and(MAJORAPPROVAL.STATE.eq(0))
                .fetch();
    }

    /**
     * 通过原专业院长进行查找
     *查找state=0 即处于审批中
     * @param CurrentDean
     * @return
     */
    @Override
    public List<MajorapprovalRecord> selectByCurrentDean(String CurrentDean) {
        return dslContext.selectFrom(MAJORAPPROVAL)
                .where(MAJORAPPROVAL.CURRENTDEAN.eq(CurrentDean))
                .and(MAJORAPPROVAL.STATE.eq(0))
                .fetch();
    }

    /**
     * 通过新专业教务处名进行查找
    * 查找state=0 即处于审批中
     * @param NewAcademicOffice
     * @return
     */
    @Override
    public List<MajorapprovalRecord> selectByNewAcademicOffice(String NewAcademicOffice) {
        return dslContext.selectFrom(MAJORAPPROVAL)
                .where(MAJORAPPROVAL.NEWACADEMICOFFICE.eq(NewAcademicOffice))
                .and(MAJORAPPROVAL.STATE.eq(0))
                .fetch();
    }

    /**
     *  通过新专业院长进行查找
     * 查找state=0 即处于审批中
     * @param NewDean
     * @return
     */
    @Override
    public List<MajorapprovalRecord> selectByNewDean(String NewDean) {
        return dslContext.selectFrom(MAJORAPPROVAL)
                .where(MAJORAPPROVAL.NEWDEAN.eq(NewDean))
                .and(MAJORAPPROVAL.STATE.eq(0))
                .fetch();
    }

    /**
     * 通过学校教务处进行查找
     * 查找state=0 即处于审批中
     * @param AcademicOffice
     * @return
     */
    @Override
    public List<MajorapprovalRecord> selectByAcademicOffice(String AcademicOffice) {
        return dslContext.selectFrom(MAJORAPPROVAL)
                .where(MAJORAPPROVAL.ACADEMICOFFICE.eq(AcademicOffice))
                .and(MAJORAPPROVAL.STATE.eq(0))
                .fetch();
    }

    /**
     * 通过学生申请ID修改状态
     * 修改状态为1，即审批结束
     * @param cid
     * @param state
     * @return
     */
    @Override
    public int updateStateByCid(int cid) {
        return dslContext.update(MAJORAPPROVAL)
                .set(MAJORAPPROVAL.STATE,1)
                .where(MAJORAPPROVAL.CID.eq(cid))
                .execute();
    }
}
