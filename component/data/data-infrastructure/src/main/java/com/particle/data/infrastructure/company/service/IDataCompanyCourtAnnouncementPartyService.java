package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业法院公告当事人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
public interface IDataCompanyCourtAnnouncementPartyService extends IBaseService<DataCompanyCourtAnnouncementPartyDO> {

    /**
     * 根据法院公告表id查询
     * @param companyCourtAnnouncementId
     * @return
     */
    default List<DataCompanyCourtAnnouncementPartyDO> getByCompanyCourtAnnouncementId(Long companyCourtAnnouncementId) {
        Assert.notNull(companyCourtAnnouncementId,"companyCourtAnnouncementId 不能为空");
        return list(Wrappers.<DataCompanyCourtAnnouncementPartyDO>lambdaQuery().eq(DataCompanyCourtAnnouncementPartyDO::getCompanyCourtAnnouncementId, companyCourtAnnouncementId));
    }



    /**
     * 根据法院公告表id查询多个
     * @param companyCourtAnnouncementIds
     * @return
     */
    default List<DataCompanyCourtAnnouncementPartyDO> getByCompanyCourtAnnouncementIds(List<Long> companyCourtAnnouncementIds) {
        Assert.notEmpty(companyCourtAnnouncementIds,"companyCourtAnnouncementIds 不能为空");
        return list(Wrappers.<DataCompanyCourtAnnouncementPartyDO>lambdaQuery().in(DataCompanyCourtAnnouncementPartyDO::getCompanyCourtAnnouncementId, companyCourtAnnouncementIds));
    }



    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyCourtAnnouncementPartyDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyCourtAnnouncementPartyDO>lambdaQuery()
                .eq(DataCompanyCourtAnnouncementPartyDO::getPartyCompanyId, companyId)
        );
    }

    /**
     * 根据 法院公告id 和 dataMd5 查询
     * @param companyCourtAnnouncementId
     * @param dataMd5
     * @return
     */
    default public DataCompanyCourtAnnouncementPartyDO getByCompanyCourtAnnouncementIdAndDataMd5(Long companyCourtAnnouncementId,String dataMd5) {
        Assert.notNull(companyCourtAnnouncementId,"companyCourtAnnouncementId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyCourtAnnouncementPartyDO>lambdaQuery()
                .eq(DataCompanyCourtAnnouncementPartyDO::getCompanyCourtAnnouncementId, companyCourtAnnouncementId)
                .eq(DataCompanyCourtAnnouncementPartyDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyCourtAnnouncementPartyDO>lambdaUpdate().eq(DataCompanyCourtAnnouncementPartyDO::getId, id)
                .set(DataCompanyCourtAnnouncementPartyDO::getLatestHandleAt, LocalDateTime.now()));
    }




}
