package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业送达公告当事人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
public interface IDataCompanyDeliveryAnnouncementPartyService extends IBaseService<DataCompanyDeliveryAnnouncementPartyDO> {


    /**
     * 根据送达公告表id查询
     * @param companyDeliveryAnnouncementId
     * @return
     */
    default List<DataCompanyDeliveryAnnouncementPartyDO> getByCompanyDeliveryAnnouncementId(Long companyDeliveryAnnouncementId) {
        Assert.notNull(companyDeliveryAnnouncementId,"companyDeliveryAnnouncementId 不能为空");
        return list(Wrappers.<DataCompanyDeliveryAnnouncementPartyDO>lambdaQuery().eq(DataCompanyDeliveryAnnouncementPartyDO::getCompanyDeliveryAnnouncementId, companyDeliveryAnnouncementId));
    }



    /**
     * 根据送达公告表id查询多个
     * @param companyDeliveryAnnouncementIds
     * @return
     */
    default List<DataCompanyDeliveryAnnouncementPartyDO> getByCompanyDeliveryAnnouncementIds(List<Long> companyDeliveryAnnouncementIds) {
        Assert.notEmpty(companyDeliveryAnnouncementIds,"companyDeliveryAnnouncementIds 不能为空");
        return list(Wrappers.<DataCompanyDeliveryAnnouncementPartyDO>lambdaQuery().in(DataCompanyDeliveryAnnouncementPartyDO::getCompanyDeliveryAnnouncementId, companyDeliveryAnnouncementIds));
    }




    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyDeliveryAnnouncementPartyDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyDeliveryAnnouncementPartyDO>lambdaQuery()
                .eq(DataCompanyDeliveryAnnouncementPartyDO::getPartyCompanyId, companyId)
        );
    }

    /**
     * 根据 送达公告id 和 dataMd5 查询
     * @param companyDeliveryAnnouncementId
     * @param dataMd5
     * @return
     */
    default public DataCompanyDeliveryAnnouncementPartyDO getByCompanyDeliveryAnnouncementIdAndDataMd5(Long companyDeliveryAnnouncementId,String dataMd5) {
        Assert.notNull(companyDeliveryAnnouncementId,"companyDeliveryAnnouncementId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyDeliveryAnnouncementPartyDO>lambdaQuery()
                .eq(DataCompanyDeliveryAnnouncementPartyDO::getCompanyDeliveryAnnouncementId, companyDeliveryAnnouncementId)
                .eq(DataCompanyDeliveryAnnouncementPartyDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyDeliveryAnnouncementPartyDO>lambdaUpdate().eq(DataCompanyDeliveryAnnouncementPartyDO::getId, id)
                .set(DataCompanyDeliveryAnnouncementPartyDO::getLatestHandleAt, LocalDateTime.now()));
    }




}
