package com.particle.data.infrastructure.company.service;

import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业送达公告内容 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
public interface IDataCompanyDeliveryAnnouncementContentService extends IBaseService<DataCompanyDeliveryAnnouncementContentDO> {

    /**
     * 根据企业送达公告id查询
     * @param companyDeliveryAnnouncementId
     * @return
     */
    default DataCompanyDeliveryAnnouncementContentDO getByCompanyDeliveryAnnouncementId(Long companyDeliveryAnnouncementId) {
        Assert.notNull(companyDeliveryAnnouncementId,"companyDeliveryAnnouncementId 不能为空");
        return getOne(Wrappers.<DataCompanyDeliveryAnnouncementContentDO>lambdaQuery().eq(DataCompanyDeliveryAnnouncementContentDO::getCompanyDeliveryAnnouncementId, companyDeliveryAnnouncementId));
    }



    /**
     * 根据企业送达公告id查询多个
     * @param companyDeliveryAnnouncementIds
     * @return
     */
    default List<DataCompanyDeliveryAnnouncementContentDO> getByCompanyDeliveryAnnouncementIds(List<Long> companyDeliveryAnnouncementIds) {
        Assert.notEmpty(companyDeliveryAnnouncementIds,"companyDeliveryAnnouncementIds 不能为空");
        return list(Wrappers.<DataCompanyDeliveryAnnouncementContentDO>lambdaQuery().in(DataCompanyDeliveryAnnouncementContentDO::getCompanyDeliveryAnnouncementId, companyDeliveryAnnouncementIds));
    }


    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyDeliveryAnnouncementContentDO>lambdaUpdate().eq(DataCompanyDeliveryAnnouncementContentDO::getId, id)
                .set(DataCompanyDeliveryAnnouncementContentDO::getLatestHandleAt, LocalDateTime.now()));
    }







}
