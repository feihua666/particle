package com.particle.tenant.infrastructure.service;

import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 租户 服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
public interface ITenantService extends IBaseService<TenantDO> {

    /**
     * 根据租户编码查询
     * @param code
     * @return
     */
    default TenantDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<TenantDO>lambdaQuery().eq(TenantDO::getCode, code));
    }



    /**
     * 根据租户编码查询多个
     * @param codes
     * @return
     */
    default List<TenantDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<TenantDO>lambdaQuery().in(TenantDO::getCode, codes));
    }


    /**
     * 根据租户域名查询
     * @param tenantDomain
     * @return
     */
    default TenantDO getByTenantDomain(String tenantDomain) {
        Assert.notNull(tenantDomain,"tenantDomain 不能为空");
        return getOne(Wrappers.<TenantDO>lambdaQuery().eq(TenantDO::getTenantDomain, tenantDomain));
    }
}
