package com.particle.tenant.domain.userinvite;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户邀请记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Data
@Entity
public class TenantUserInviteUserRecord extends AggreateRoot {

    private TenantUserInviteUserRecordId id;

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



    /**
     * 创建租户用户邀请记录领域模型对象
     * @return 租户用户邀请记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static TenantUserInviteUserRecord create(){
        return DomainFactory.create(TenantUserInviteUserRecord.class);
    }
}
