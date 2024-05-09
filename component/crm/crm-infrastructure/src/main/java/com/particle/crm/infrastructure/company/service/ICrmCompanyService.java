package com.particle.crm.infrastructure.company.service;

import com.particle.crm.infrastructure.company.dos.CrmCompanyDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 客户公司 服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
public interface ICrmCompanyService extends IBaseService<CrmCompanyDO> {

    /**
     * 根据公司编码查询
     * @param code
     * @return
     */
    default CrmCompanyDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<CrmCompanyDO>lambdaQuery().eq(CrmCompanyDO::getCode, code));
    }



    /**
     * 根据公司编码查询多个
     * @param codes
     * @return
     */
    default List<CrmCompanyDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<CrmCompanyDO>lambdaQuery().in(CrmCompanyDO::getCode, codes));
    }
            
























}
