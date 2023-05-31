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

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "name")
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

	@ApiModelProperty("是否正式，1=正式，0=试用")
	private Boolean isFormal;


    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "nickname")
    @ApiModelProperty("昵称，模糊查询")
    private String nickname;

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "avatar")
    @ApiModelProperty("头像，图片绝对路径")
    private String avatar;

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "email")
    @ApiModelProperty("邮箱")
    private String email;

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "mobile")
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @TransBy(tableName = TransTableNameConstants.component_tenant, byFieldName = "tenantId", mapValueField = "name")
    @ApiModelProperty("租户名称")
    private String tenantName;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_USER_ID,byFieldName = "userId",mapValueField = "id")
    @ApiModelProperty("部门id")
    private Long deptId;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_USER_ID,byFieldName = "userId",mapValueField = "name")
    @ApiModelProperty("部门名称")
    private String deptName;

    @TransBy(type = TransConstants.TRANS_ROLE_BY_USER_ID,byFieldName = "id",mapValueField = "name")
    @ApiModelProperty("角色名称")
    private String roleName;
}