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
 * 租户用户邀请记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Data
@ApiModel
public class TenantUserInviteUserRecordVO extends AbstractBaseIdVO {

    @ApiModelProperty("租户用户邀请id")
    private Integer tenantUserInviteId;
    
    @ApiModelProperty("被邀请人用户id")
    private Long invitedUserId;
    
    @ApiModelProperty("用户加入时间")
    private LocalDateTime joinAt;
        
    @ApiModelProperty("描述")
    private String remark;
    


}
