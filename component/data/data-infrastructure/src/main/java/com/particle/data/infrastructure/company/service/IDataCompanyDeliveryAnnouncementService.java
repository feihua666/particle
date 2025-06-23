package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyDeliveryAnnouncementListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业送达公告 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
public interface IDataCompanyDeliveryAnnouncementService extends IBaseService<DataCompanyDeliveryAnnouncementDO> {

    /**
     * 分页查询
     * @param param
     * @param pageQueryForm
     * @return
     */
    public Page<DataCompanyDeliveryAnnouncementDO> listPage(DataCompanyDeliveryAnnouncementListPageByCompanyIdParam param, PageQueryCommand pageQueryForm);
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param caseNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyDeliveryAnnouncementDO> listPageByCompanyId(Long companyId, String caseNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        return listPage(DataCompanyDeliveryAnnouncementListPageByCompanyIdParam.create(companyId, caseNo), pageQueryForm);
    }

    /**
     * 根据 dataMd5 查询
     * @param dataMd5
     * @return
     */
    default public DataCompanyDeliveryAnnouncementDO getByDataMd5(String dataMd5) {
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyDeliveryAnnouncementDO>lambdaQuery()
                .eq(DataCompanyDeliveryAnnouncementDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyDeliveryAnnouncementDO>lambdaUpdate().eq(DataCompanyDeliveryAnnouncementDO::getId, id)
                .set(DataCompanyDeliveryAnnouncementDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
