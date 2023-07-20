package com.particle.tenant.client.userinvite.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户邀请记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Data
@Schema
public class TenantUserInviteUserRecordVO extends AbstractBaseIdVO {

    @Schema(description = "租户用户邀请id")
    private Integer tenantUserInviteId;
    
    @Schema(description = "被邀请人用户id")
    private Long invitedUserId;
    
    @Schema(description = "用户加入时间")
    private LocalDateTime joinAt;
        
    @Schema(description = "描述")
    private String remark;
    


}
