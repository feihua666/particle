package com.particle.tenant.client.userinvite.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户邀请 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Data
@ApiModel
public class TenantUserInviteVO extends AbstractBaseIdVO {

    @ApiModelProperty("邀请码")
    private String inviteCode;
    
    @ApiModelProperty("最大邀请人数")
    private Integer maxInviteCount;
    
    @ApiModelProperty("已邀请人数")
    private Integer invitedCount;
    
    @ApiModelProperty("是否过期")
    private Boolean isExpired;
    
    @ApiModelProperty("过期原因")
    private String expiredReason;
    
    @ApiModelProperty("到期时间")
    private LocalDateTime expireAt;
        
    @ApiModelProperty("邀请人用户id")
    private Long inviterUserId;
    


}
