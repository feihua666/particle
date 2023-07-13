package com.particle.tenant.infrastructure.service;

import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.util.Collection;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.tenant.infrastructure.dos.TenantUserDO;

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

    /**
     * 获取租户，不考虑租户的mybatis plus 插件限制
     * @param tenantIds
     * @return
     */
    default List<TenantDO> getByIdsIgnoreTenantLimit(Collection<Long> tenantIds) {
        Assert.notEmpty(tenantIds,"tenantIds 不能为空");
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).dataPermission(true).build());
            return listByIds(tenantIds);
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }

    /**
     * 获取租户，不考虑租户的mybatis plus 插件限制
     * @return
     */
    default List<TenantDO> getAllIgnoreTenantLimit() {
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).dataPermission(true).build());
            return list();
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }
    /**
     * 获取租户，不考虑租户的mybatis plus 插件限制
     * @return
     */
    default List<TenantDO> getAllSimpleIgnoreTenantLimit() {
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).dataPermission(true).build());
            return list(Wrappers.<TenantDO>lambdaQuery().select(TenantDO::getId,TenantDO::getCode,TenantDO::getName,TenantDO::getTenantDomain));
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }
    /**
     * 获取租户，不考虑租户的mybatis plus 插件限制
     * 不查询总数
     * @return
     */
    default Page<TenantDO> pageAllIgnoreTenantLimit(Long current, Long size) {
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).dataPermission(true).build());

            return page(new Page<TenantDO>(current,size,false));
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }
}
