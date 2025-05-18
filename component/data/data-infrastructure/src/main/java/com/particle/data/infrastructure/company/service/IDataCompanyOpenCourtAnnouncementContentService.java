package com.particle.data.infrastructure.company.service;

import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业开庭公告内容 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
public interface IDataCompanyOpenCourtAnnouncementContentService extends IBaseService<DataCompanyOpenCourtAnnouncementContentDO> {

    /**
     * 根据企业开庭公告id查询
     * @param companyOpenCourtAnnouncementId
     * @return
     */
    default DataCompanyOpenCourtAnnouncementContentDO getByCompanyOpenCourtAnnouncementId(Long companyOpenCourtAnnouncementId) {
        Assert.notNull(companyOpenCourtAnnouncementId,"companyOpenCourtAnnouncementId 不能为空");
        return getOne(Wrappers.<DataCompanyOpenCourtAnnouncementContentDO>lambdaQuery().eq(DataCompanyOpenCourtAnnouncementContentDO::getCompanyOpenCourtAnnouncementId, companyOpenCourtAnnouncementId));
    }



    /**
     * 根据企业开庭公告id查询多个
     * @param companyOpenCourtAnnouncementIds
     * @return
     */
    default List<DataCompanyOpenCourtAnnouncementContentDO> listByCompanyOpenCourtAnnouncementIds(List<Long> companyOpenCourtAnnouncementIds) {
        Assert.notEmpty(companyOpenCourtAnnouncementIds,"companyOpenCourtAnnouncementIds 不能为空");
        return list(Wrappers.<DataCompanyOpenCourtAnnouncementContentDO>lambdaQuery().in(DataCompanyOpenCourtAnnouncementContentDO::getCompanyOpenCourtAnnouncementId, companyOpenCourtAnnouncementIds));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyOpenCourtAnnouncementContentDO>lambdaUpdate().eq(DataCompanyOpenCourtAnnouncementContentDO::getId, id)
                .set(DataCompanyOpenCourtAnnouncementContentDO::getLatestHandleAt, LocalDateTime.now()));
    }









}
