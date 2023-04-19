package com.particle.tenant.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@ApiModel
public class TenantUserVO extends AbstractBaseIdVO {

    @ApiModelProperty("用户id")
    private Long userId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "userId",mapValueField = "name")
    @ApiModelProperty("真实姓名")
    private String name;
    
    @ApiModelProperty("是否过期")
    private Boolean isExpired;
    
    @ApiModelProperty("过期原因")
    private String expiredReason;
    
    @ApiModelProperty("到期时间")
    private LocalDateTime expireAt;


    @ApiModelProperty("是否离职或退出")
    private Boolean isLeave;

    @ApiModelProperty("离职或退出原因")
    private String leaveReason;

    @ApiModelProperty("离职或退出时间")
    private LocalDateTime leaveAt;

    @ApiModelProperty("用户加入时间")
    private LocalDateTime joinAt;


    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "userId",mapValueField = "nickname")
    @ApiModelProperty("昵称，模糊查询")
    private String nickname;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "userId",mapValueField = "avatar")
    @ApiModelProperty("头像，图片绝对路径")
    private String avatar;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @TransBy(tableName = TransTableNameConstants.component_tenant, byFieldName = "tenantId", mapValueField = "name")
    @ApiModelProperty("租户名称")
    private String tenantName;
}
