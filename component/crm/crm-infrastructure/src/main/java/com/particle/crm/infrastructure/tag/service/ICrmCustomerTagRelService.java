package com.particle.crm.infrastructure.tag.service;

import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagRelDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 客户标签关系 服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
public interface ICrmCustomerTagRelService extends IBaseService<CrmCustomerTagRelDO> {

    /**
     * 根据客户id查询
     * @param crmCustomerId
     * @return
     */
    default List<CrmCustomerTagRelDO> getByCrmCustomerId(Long crmCustomerId) {
        Assert.notNull(crmCustomerId,"crmCustomerId 不能为空");
        return list(Wrappers.<CrmCustomerTagRelDO>lambdaQuery().eq(CrmCustomerTagRelDO::getCrmCustomerId, crmCustomerId));
    }



    /**
     * 根据客户id查询多个
     * @param crmCustomerIds
     * @return
     */
    default List<CrmCustomerTagRelDO> getByCrmCustomerIds(List<Long> crmCustomerIds) {
        Assert.notEmpty(crmCustomerIds,"crmCustomerIds 不能为空");
        return list(Wrappers.<CrmCustomerTagRelDO>lambdaQuery().in(CrmCustomerTagRelDO::getCrmCustomerId, crmCustomerIds));
    }
            

    /**
     * 根据标签id查询
     * @param crmCustomerTagId
     * @return
     */
    default List<CrmCustomerTagRelDO> getByCrmCustomerTagId(Long crmCustomerTagId) {
        Assert.notNull(crmCustomerTagId,"crmCustomerTagId 不能为空");
        return list(Wrappers.<CrmCustomerTagRelDO>lambdaQuery().eq(CrmCustomerTagRelDO::getCrmCustomerTagId, crmCustomerTagId));
    }



    /**
     * 根据标签id查询多个
     * @param crmCustomerTagIds
     * @return
     */
    default List<CrmCustomerTagRelDO> getByCrmCustomerTagIds(List<Long> crmCustomerTagIds) {
        Assert.notEmpty(crmCustomerTagIds,"crmCustomerTagIds 不能为空");
        return list(Wrappers.<CrmCustomerTagRelDO>lambdaQuery().in(CrmCustomerTagRelDO::getCrmCustomerTagId, crmCustomerTagIds));
    }
            








}
