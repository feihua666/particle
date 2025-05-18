package com.particle.data.infrastructure.company.service;

import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业法院公告内容 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
public interface IDataCompanyCourtAnnouncementContentService extends IBaseService<DataCompanyCourtAnnouncementContentDO> {

    /**
     * 根据法院公告表id查询
     * @param companyCourtAnnouncementId
     * @return
     */
    default DataCompanyCourtAnnouncementContentDO getByCompanyCourtAnnouncementId(Long companyCourtAnnouncementId) {
        Assert.notNull(companyCourtAnnouncementId,"companyCourtAnnouncementId 不能为空");
        return getOne(Wrappers.<DataCompanyCourtAnnouncementContentDO>lambdaQuery().eq(DataCompanyCourtAnnouncementContentDO::getCompanyCourtAnnouncementId, companyCourtAnnouncementId));
    }



    /**
     * 根据法院公告表id查询多个
     * @param companyCourtAnnouncementIds
     * @return
     */
    default List<DataCompanyCourtAnnouncementContentDO> listByCompanyCourtAnnouncementIds(List<Long> companyCourtAnnouncementIds) {
        Assert.notEmpty(companyCourtAnnouncementIds,"companyCourtAnnouncementIds 不能为空");
        return list(Wrappers.<DataCompanyCourtAnnouncementContentDO>lambdaQuery().in(DataCompanyCourtAnnouncementContentDO::getCompanyCourtAnnouncementId, companyCourtAnnouncementIds));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyCourtAnnouncementContentDO>lambdaUpdate().eq(DataCompanyCourtAnnouncementContentDO::getId, id)
                .set(DataCompanyCourtAnnouncementContentDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
