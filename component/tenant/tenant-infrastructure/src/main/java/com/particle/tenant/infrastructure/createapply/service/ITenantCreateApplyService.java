package com.particle.tenant.infrastructure.createapply.service;

import com.particle.tenant.infrastructure.createapply.dos.TenantCreateApplyDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 租户创建申请 服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
public interface ITenantCreateApplyService extends IBaseService<TenantCreateApplyDO> {






    /**
     * 根据申请用户查询
     * @param applyUserId
     * @return
     */
    default List<TenantCreateApplyDO> getByApplyUserId(Long applyUserId) {
        Assert.notNull(applyUserId,"applyUserId 不能为空");
        return list(Wrappers.<TenantCreateApplyDO>lambdaQuery().eq(TenantCreateApplyDO::getApplyUserId, applyUserId));
    }



    /**
     * 根据申请用户查询多个
     * @param applyUserIds
     * @return
     */
    default List<TenantCreateApplyDO> getByApplyUserIds(List<Long> applyUserIds) {
        Assert.notEmpty(applyUserIds,"applyUserIds 不能为空");
        return list(Wrappers.<TenantCreateApplyDO>lambdaQuery().in(TenantCreateApplyDO::getApplyUserId, applyUserIds));
    }
            













}
