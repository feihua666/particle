package com.particle.crm.infrastructure.customer.service;

import com.particle.crm.infrastructure.customer.dos.CrmCustomerDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 客户 服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
public interface ICrmCustomerService extends IBaseService<CrmCustomerDO> {

    /**
     * 根据客户编码查询
     * @param code
     * @return
     */
    default CrmCustomerDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<CrmCustomerDO>lambdaQuery().eq(CrmCustomerDO::getCode, code));
    }



    /**
     * 根据客户编码查询多个
     * @param codes
     * @return
     */
    default List<CrmCustomerDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().in(CrmCustomerDO::getCode, codes));
    }
            












    /**
     * 根据归属用户id查询
     * @param belongUserId
     * @return
     */
    default List<CrmCustomerDO> getByBelongUserId(Long belongUserId) {
        Assert.notNull(belongUserId,"belongUserId 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().eq(CrmCustomerDO::getBelongUserId, belongUserId));
    }



    /**
     * 根据归属用户id查询多个
     * @param belongUserIds
     * @return
     */
    default List<CrmCustomerDO> getByBelongUserIds(List<Long> belongUserIds) {
        Assert.notEmpty(belongUserIds,"belongUserIds 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().in(CrmCustomerDO::getBelongUserId, belongUserIds));
    }
            

    /**
     * 根据归属用户的公司id查询
     * @param belongCompId
     * @return
     */
    default List<CrmCustomerDO> getByBelongCompId(Long belongCompId) {
        Assert.notNull(belongCompId,"belongCompId 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().eq(CrmCustomerDO::getBelongCompId, belongCompId));
    }



    /**
     * 根据归属用户的公司id查询多个
     * @param belongCompIds
     * @return
     */
    default List<CrmCustomerDO> getByBelongCompIds(List<Long> belongCompIds) {
        Assert.notEmpty(belongCompIds,"belongCompIds 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().in(CrmCustomerDO::getBelongCompId, belongCompIds));
    }
            

    /**
     * 根据归属用户的部门id查询
     * @param belongDeptId
     * @return
     */
    default List<CrmCustomerDO> getByBelongDeptId(Long belongDeptId) {
        Assert.notNull(belongDeptId,"belongDeptId 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().eq(CrmCustomerDO::getBelongDeptId, belongDeptId));
    }



    /**
     * 根据归属用户的部门id查询多个
     * @param belongDeptIds
     * @return
     */
    default List<CrmCustomerDO> getByBelongDeptIds(List<Long> belongDeptIds) {
        Assert.notEmpty(belongDeptIds,"belongDeptIds 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().in(CrmCustomerDO::getBelongDeptId, belongDeptIds));
    }
            

    /**
     * 根据唯一id查询
     * @param unionId
     * @return
     */
    default List<CrmCustomerDO> getByUnionId(Long unionId) {
        Assert.notNull(unionId,"unionId 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().eq(CrmCustomerDO::getUnionId, unionId));
    }



    /**
     * 根据唯一id查询多个
     * @param unionIds
     * @return
     */
    default List<CrmCustomerDO> getByUnionIds(List<Long> unionIds) {
        Assert.notEmpty(unionIds,"unionIds 不能为空");
        return list(Wrappers.<CrmCustomerDO>lambdaQuery().in(CrmCustomerDO::getUnionId, unionIds));
    }
            









}
