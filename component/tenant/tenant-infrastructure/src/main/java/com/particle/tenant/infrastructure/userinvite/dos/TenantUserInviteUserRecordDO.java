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
 * 租户用户邀请记录表
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Data
@TableName("component_tenant_user_invite_user_record")
public class TenantUserInviteUserRecordDO extends BaseDO {

    /**
    * 租户用户邀请id
    */
    private Integer tenantUserInviteId;

    /**
    * 被邀请人用户id
    */
    private Long invitedUserId;

    /**
    * 用户加入时间
    */
    private LocalDateTime joinAt;
    
    /**
    * 描述
    */
    private String remark;


}
