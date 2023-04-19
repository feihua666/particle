package com.particle.tenant.infrastructure.userinvite.service;

import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 租户用户邀请 服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
public interface ITenantUserInviteService extends IBaseService<TenantUserInviteDO> {

    /**
     * 根据邀请码查询
     * @param inviteCode
     * @return
     */
    default TenantUserInviteDO getByInviteCode(String inviteCode) {
        Assert.notNull(inviteCode,"inviteCode 不能为空");
        return getOne(Wrappers.<TenantUserInviteDO>lambdaQuery().eq(TenantUserInviteDO::getInviteCode, inviteCode));
    }



    /**
     * 根据邀请码查询多个
     * @param inviteCodes
     * @return
     */
    default List<TenantUserInviteDO> getByInviteCodes(List<String> inviteCodes) {
        Assert.notEmpty(inviteCodes,"inviteCodes 不能为空");
        return list(Wrappers.<TenantUserInviteDO>lambdaQuery().in(TenantUserInviteDO::getInviteCode, inviteCodes));
    }
            














}
