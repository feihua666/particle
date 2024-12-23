package com.particle.crm.infrastructure.customer.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerContactDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 客户联系方式 服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
public interface ICrmCustomerContactService extends IBaseService<CrmCustomerContactDO> {

    /**
     * 根据客户id查询
     * @param crmCustomerId
     * @return
     */
    default List<CrmCustomerContactDO> getByCrmCustomerId(Long crmCustomerId) {
        Assert.notNull(crmCustomerId,"crmCustomerId 不能为空");
        return list(Wrappers.<CrmCustomerContactDO>lambdaQuery().eq(CrmCustomerContactDO::getCrmCustomerId, crmCustomerId));
    }



    /**
     * 根据客户id查询多个
     * @param crmCustomerIds
     * @return
     */
    default List<CrmCustomerContactDO> getByCrmCustomerIds(List<Long> crmCustomerIds) {
        Assert.notEmpty(crmCustomerIds,"crmCustomerIds 不能为空");
        return list(Wrappers.<CrmCustomerContactDO>lambdaQuery().in(CrmCustomerContactDO::getCrmCustomerId, crmCustomerIds));
    }












}
