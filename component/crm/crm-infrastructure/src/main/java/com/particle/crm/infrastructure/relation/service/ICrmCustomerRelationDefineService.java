package com.particle.crm.infrastructure.relation.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDefineDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 客户关系定义 服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
public interface ICrmCustomerRelationDefineService extends IBaseService<CrmCustomerRelationDefineDO> {

    /**
     * 根据关系定义编码查询
     * @param code
     * @return
     */
    default CrmCustomerRelationDefineDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<CrmCustomerRelationDefineDO>lambdaQuery().eq(CrmCustomerRelationDefineDO::getCode, code));
    }



    /**
     * 根据关系定义编码查询多个
     * @param codes
     * @return
     */
    default List<CrmCustomerRelationDefineDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<CrmCustomerRelationDefineDO>lambdaQuery().in(CrmCustomerRelationDefineDO::getCode, codes));
    }


    /**
     * 根据关系定义名称查询
     * @param name
     * @return
     */
    default CrmCustomerRelationDefineDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<CrmCustomerRelationDefineDO>lambdaQuery().eq(CrmCustomerRelationDefineDO::getName, name));
    }



    /**
     * 根据关系定义名称查询多个
     * @param names
     * @return
     */
    default List<CrmCustomerRelationDefineDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<CrmCustomerRelationDefineDO>lambdaQuery().in(CrmCustomerRelationDefineDO::getName, names));
    }










}
