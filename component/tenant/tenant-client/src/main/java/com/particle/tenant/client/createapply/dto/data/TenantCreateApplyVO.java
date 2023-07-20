package com.particle.tenant.client.createapply.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 租户创建申请 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Data
@Schema
public class TenantCreateApplyVO extends AbstractBaseIdVO {

    @Schema(description = "租户名称")
    private String name;
    
    @Schema(description = "姓名")
    private String userName;

	@Schema(description = "账号，没有指定 applyUserId 时，用户创建用户登录账号")
	private String account;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "手机号")
    private String mobile;

	@Schema(description = "密码，没有指定 applyUserId 时，用户创建用户登录密码")
	private String password;

	@Schema(description = "是否发送邮件通知，1=发送，0=不发送，仅存在邮箱时发送")
	private Boolean isSendEmailNotice;

	@Schema(description = "密码，没有指定 applyUserId 时，用户创建用户登录密码")
	private Boolean isSendMobileNotice;
    
    @Schema(description = "租户类型字典id")
    private Long tenantTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "tenantTypeDictId",mapValueField = "name")
    @Schema(description = "租户类型字典id对应字典名称")
    private String tenantTypeDictName;
        
    @Schema(description = "申请用户")
    private Long applyUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "applyUserId",mapValueField = "nickname")
    @Schema(description = "申请用户昵称")
    private String applyUserNickname;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "applyUserId",mapValueField = "avatar")
    @Schema(description = "申请用户头像")
    private String applyUserAvatar;

    @Schema(description = "审核状态")
    private Long auditStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "auditStatusDictId",mapValueField = "name")
    @Schema(description = "审核状态对应字典名称")
    private String auditStatusDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "auditStatusDictId",mapValueField = "value")
    @Schema(description = "审核状态对应字典值")
    private String auditStatusDictValue;
        
    @Schema(description = "审核意见")
    private String auditStatusComment;
    
    @Schema(description = "审核用户id")
    private Long auditUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "auditUserId",mapValueField = "nickname")
    @Schema(description = "审核用户昵称")
    private String auditUserNickname;

    @Schema(description = "审核通过后创建的租户id")
    private Long appliedTenantId;

	@Schema(description = "是否正式，1=正式，0=试用")
	private Boolean isFormal;

	@Schema(description = "用户数量限制")
	private Integer userLimitCount;

	@Schema(description = "生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@Schema(description = "申请天数")
	private Integer effectiveDays;

	@Schema(description = "过期时间，从什么时候失效")
	private LocalDateTime expireAt;

	@Schema(description = "额外申请项json，如：应用和功能")
	private String extJson;
    
    @Schema(description = "描述")
    private String remark;
    


}