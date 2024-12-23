package com.particle.crm.infrastructure.relation.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 客户与客户关系 服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
public interface ICrmCustomerRelationService extends IBaseService<CrmCustomerRelationDO> {

    /**
     * 根据客户id查询
     * @param crmCustomerId
     * @return
     */
    default List<CrmCustomerRelationDO> getByCrmCustomerId(Long crmCustomerId) {
        Assert.notNull(crmCustomerId,"crmCustomerId 不能为空");
        return list(Wrappers.<CrmCustomerRelationDO>lambdaQuery().eq(CrmCustomerRelationDO::getCrmCustomerId, crmCustomerId));
    }



    /**
     * 根据客户id查询多个
     * @param crmCustomerIds
     * @return
     */
    default List<CrmCustomerRelationDO> getByCrmCustomerIds(List<Long> crmCustomerIds) {
        Assert.notEmpty(crmCustomerIds,"crmCustomerIds 不能为空");
        return list(Wrappers.<CrmCustomerRelationDO>lambdaQuery().in(CrmCustomerRelationDO::getCrmCustomerId, crmCustomerIds));
    }


    /**
     * 根据另一个客户id查询
     * @param anotherCrmCustomerId
     * @return
     */
    default List<CrmCustomerRelationDO> getByAnotherCrmCustomerId(Long anotherCrmCustomerId) {
        Assert.notNull(anotherCrmCustomerId,"anotherCrmCustomerId 不能为空");
        return list(Wrappers.<CrmCustomerRelationDO>lambdaQuery().eq(CrmCustomerRelationDO::getAnotherCrmCustomerId, anotherCrmCustomerId));
    }



    /**
     * 根据另一个客户id查询多个
     * @param anotherCrmCustomerIds
     * @return
     */
    default List<CrmCustomerRelationDO> getByAnotherCrmCustomerIds(List<Long> anotherCrmCustomerIds) {
        Assert.notEmpty(anotherCrmCustomerIds,"anotherCrmCustomerIds 不能为空");
        return list(Wrappers.<CrmCustomerRelationDO>lambdaQuery().in(CrmCustomerRelationDO::getAnotherCrmCustomerId, anotherCrmCustomerIds));
    }


    /**
     * 根据关系id查询
     * @param crmCustomerRelationDefineId
     * @return
     */
    default List<CrmCustomerRelationDO> getByCrmCustomerRelationDefineId(Long crmCustomerRelationDefineId) {
        Assert.notNull(crmCustomerRelationDefineId,"crmCustomerRelationDefineId 不能为空");
        return list(Wrappers.<CrmCustomerRelationDO>lambdaQuery().eq(CrmCustomerRelationDO::getCrmCustomerRelationDefineId, crmCustomerRelationDefineId));
    }



    /**
     * 根据关系id查询多个
     * @param crmCustomerRelationDefineIds
     * @return
     */
    default List<CrmCustomerRelationDO> getBycrmCustomerRelationDefineIds(List<Long> crmCustomerRelationDefineIds) {
        Assert.notEmpty(crmCustomerRelationDefineIds,"crmCustomerRelationDefineIds 不能为空");
        return list(Wrappers.<CrmCustomerRelationDO>lambdaQuery().in(CrmCustomerRelationDO::getCrmCustomerRelationDefineId, crmCustomerRelationDefineIds));
    }










}
