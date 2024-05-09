package com.particle.crm.infrastructure.tag.service;

import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 客户标签 服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
public interface ICrmCustomerTagService extends IBaseService<CrmCustomerTagDO> {

    /**
     * 根据标签编码查询
     * @param code
     * @return
     */
    default CrmCustomerTagDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<CrmCustomerTagDO>lambdaQuery().eq(CrmCustomerTagDO::getCode, code));
    }



    /**
     * 根据标签编码查询多个
     * @param codes
     * @return
     */
    default List<CrmCustomerTagDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<CrmCustomerTagDO>lambdaQuery().in(CrmCustomerTagDO::getCode, codes));
    }
            

    /**
     * 根据标签名称查询
     * @param name
     * @return
     */
    default CrmCustomerTagDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<CrmCustomerTagDO>lambdaQuery().eq(CrmCustomerTagDO::getName, name));
    }



    /**
     * 根据标签名称查询多个
     * @param names
     * @return
     */
    default List<CrmCustomerTagDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<CrmCustomerTagDO>lambdaQuery().in(CrmCustomerTagDO::getName, names));
    }
            









}
