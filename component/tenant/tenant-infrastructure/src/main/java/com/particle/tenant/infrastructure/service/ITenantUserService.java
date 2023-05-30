package com.particle.tenant.infrastructure.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 租户用户 服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
public interface ITenantUserService extends IBaseService<TenantUserDO> {

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default List<TenantUserDO> getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return list(Wrappers.<TenantUserDO>lambdaQuery().eq(TenantUserDO::getUserId, userId));
    }

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default List<TenantUserDO> getByUserIdIgnoreTenantLimit(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
            return list(Wrappers.<TenantUserDO>lambdaQuery().eq(TenantUserDO::getUserId, userId));
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }

    /**
     * 统计租户下的用户数
     * @param tenantId
     * @return
     */
    default long countByTenantIdIgnoreTenantLimit(Long tenantId,Boolean isLeave,Boolean isExpired) {
        Assert.notNull(tenantId,"tenantId 不能为空");
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
            return count(Wrappers.<TenantUserDO>lambdaQuery()
                    .eq(TenantUserDO::getTenantId, tenantId)
                    .eq(isLeave != null,TenantUserDO::getIsLeave,isLeave)
                    .eq(isExpired != null,TenantUserDO::getIsExpired,isExpired)
            );
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }

    /**
     * 根据用户id查询多个
     * @param userIds
     * @return
     */
    default List<TenantUserDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<TenantUserDO>lambdaQuery().in(TenantUserDO::getUserId, userIds));
    }



}
