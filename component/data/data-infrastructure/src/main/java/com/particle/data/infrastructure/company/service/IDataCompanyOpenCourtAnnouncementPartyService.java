package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业开庭公告当事人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
public interface IDataCompanyOpenCourtAnnouncementPartyService extends IBaseService<DataCompanyOpenCourtAnnouncementPartyDO> {

    /**
     * 根据开庭公告表id查询
     * @param companyOpenCourtAnnouncementId
     * @return
     */
    default List<DataCompanyOpenCourtAnnouncementPartyDO> getByCompanyOpenCourtAnnouncementId(Long companyOpenCourtAnnouncementId) {
        Assert.notNull(companyOpenCourtAnnouncementId,"companyOpenCourtAnnouncementId 不能为空");
        return list(Wrappers.<DataCompanyOpenCourtAnnouncementPartyDO>lambdaQuery().eq(DataCompanyOpenCourtAnnouncementPartyDO::getCompanyOpenCourtAnnouncementId, companyOpenCourtAnnouncementId));
    }



    /**
     * 根据开庭公告表id查询多个
     * @param companyOpenCourtAnnouncementIds
     * @return
     */
    default List<DataCompanyOpenCourtAnnouncementPartyDO> getByCompanyOpenCourtAnnouncementIds(List<Long> companyOpenCourtAnnouncementIds) {
        Assert.notEmpty(companyOpenCourtAnnouncementIds,"companyOpenCourtAnnouncementIds 不能为空");
        return list(Wrappers.<DataCompanyOpenCourtAnnouncementPartyDO>lambdaQuery().in(DataCompanyOpenCourtAnnouncementPartyDO::getCompanyOpenCourtAnnouncementId, companyOpenCourtAnnouncementIds));
    }



    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyOpenCourtAnnouncementPartyDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyOpenCourtAnnouncementPartyDO>lambdaQuery()
                .eq(DataCompanyOpenCourtAnnouncementPartyDO::getPartyCompanyId, companyId)
        );
    }

    /**
     * 根据 法院公告id 和 dataMd5 查询
     * @param companyOpenCourtAnnouncementId
     * @param dataMd5
     * @return
     */
    default public DataCompanyOpenCourtAnnouncementPartyDO getByCompanyOpenCourtAnnouncementIdAndDataMd5(Long companyOpenCourtAnnouncementId,String dataMd5) {
        Assert.notNull(companyOpenCourtAnnouncementId,"companyOpenCourtAnnouncementId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyOpenCourtAnnouncementPartyDO>lambdaQuery()
                .eq(DataCompanyOpenCourtAnnouncementPartyDO::getCompanyOpenCourtAnnouncementId, companyOpenCourtAnnouncementId)
                .eq(DataCompanyOpenCourtAnnouncementPartyDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyOpenCourtAnnouncementPartyDO>lambdaUpdate().eq(DataCompanyOpenCourtAnnouncementPartyDO::getId, id)
                .set(DataCompanyOpenCourtAnnouncementPartyDO::getLatestHandleAt, LocalDateTime.now()));
    }





}
