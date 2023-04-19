package com.particle.tenant.infrastructure.userinvite.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户邀请表
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Data
@TableName("component_tenant_user_invite")
public class TenantUserInviteDO extends BaseDO {

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


}
