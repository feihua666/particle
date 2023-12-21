package com.particle.tenant.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class TenantUserVO extends AbstractBaseIdVO {

    @Schema(description = "用户id")
    private Long userId;

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "name")
    @Schema(description = "真实姓名")
    private String name;
    
    @Schema(description = "是否过期")
    private Boolean isExpired;
    
    @Schema(description = "过期原因")
    private String expiredReason;
    
    @Schema(description = "到期时间")
    private LocalDateTime expireAt;

	@Schema(description = "生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@Schema(description = "生效日期，触发方式，一般为首次登录触发")
	private Long effectiveAtTriggerDictId;

	@Schema(description = "有效天数,0或空为不限制")
	private Integer effectiveDays;


    @Schema(description = "是否离职或退出")
    private Boolean isLeave;

    @Schema(description = "离职或退出原因")
    private String leaveReason;

    @Schema(description = "离职或退出时间")
    private LocalDateTime leaveAt;

    @Schema(description = "用户加入时间")
    private LocalDateTime joinAt;

	@Schema(description = "是否正式，1=正式，0=试用")
	private Boolean isFormal;

	@Schema(description = "备注")
	private String remark;


    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "nickname")
    @Schema(description = "昵称，模糊查询")
    private String nickname;

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "avatar")
    @Schema(description = "头像，建议图片相对路径")
    private String avatar;

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "email")
    @Schema(description = "邮箱")
    private String email;

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "mobile")
    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "租户id")
    private Long tenantId;

    @TransBy(tableName = TransTableNameConstants.component_tenant, byFieldName = "tenantId", mapValueField = "name")
    @Schema(description = "租户名称")
    private String tenantName;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_USER_ID,byFieldName = "userId",mapValueField = "id")
    @Schema(description = "部门id")
    private Long deptId;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_USER_ID,byFieldName = "userId",mapValueField = "name")
    @Schema(description = "部门名称")
    private String deptName;

    @TransBy(type = TransConstants.TRANS_ROLE_BY_USER_ID,byFieldName = "userId",mapValueField = "name")
    @Schema(description = "角色名称")
    private String roleName;
}