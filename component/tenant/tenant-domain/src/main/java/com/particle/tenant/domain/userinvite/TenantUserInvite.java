package com.particle.tenant.domain.userinvite;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户邀请 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Data
@Entity
public class TenantUserInvite extends AggreateRoot {

    private TenantUserInviteId id;

    /**
    * 邀请码
    */
    private String inviteCode;

    /**
    * 最大邀请人数
    */
    private Integer maxInviteCount;

    /**
    * 已邀请人数
    */
    private Integer invitedCount;

    /**
    * 是否过期，过期后该密码不能登录
    */
    private Boolean isExpired;

    /**
    * 过期原因
    */
    private String expiredReason;

    /**
    * 到期时间，为空永不到期
    */
    private LocalDateTime expireAt;

    /**
    * 邀请人用户id
    */
    private Long inviterUserId;



    /**
     * 创建租户用户邀请领域模型对象
     * @return 租户用户邀请领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static TenantUserInvite create(){
        return DomainFactory.create(TenantUserInvite.class);
    }
}
