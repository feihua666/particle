package com.particle.tenant.client.createapply.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class TenantCreateApplyVO extends AbstractBaseIdVO {

    @ApiModelProperty("租户名称")
    private String name;
    
    @ApiModelProperty("姓名")
    private String userName;
    
    @ApiModelProperty("邮箱")
    private String email;
    
    @ApiModelProperty("手机号")
    private String mobile;
    
    @ApiModelProperty("租户类型字典id")
    private Long tenantTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "tenantTypeDictId",mapValueField = "name")
    @ApiModelProperty("租户类型字典id对应字典名称")
    private String tenantTypeDictName;
        
    @ApiModelProperty("申请用户")
    private Long applyUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "applyUserId",mapValueField = "nickname")
    @ApiModelProperty("申请用户昵称")
    private String applyUserNickname;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "applyUserId",mapValueField = "avatar")
    @ApiModelProperty("申请用户头像")
    private String applyUserAvatar;

    @ApiModelProperty("审核状态")
    private Long auditStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "auditStatusDictId",mapValueField = "name")
    @ApiModelProperty("审核状态对应字典名称")
    private String auditStatusDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "auditStatusDictId",mapValueField = "value")
    @ApiModelProperty("审核状态对应字典值")
    private String auditStatusDictValue;
        
    @ApiModelProperty("审核意见")
    private String auditStatusComment;
    
    @ApiModelProperty("审核用户id")
    private Long auditUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "auditUserId",mapValueField = "nickname")
    @ApiModelProperty("审核用户昵称")
    private String auditUserNickname;

    @ApiModelProperty("审核通过后创建的租户id")
    private Long appliedTenantId;

	@ApiModelProperty("是否正式，1=正式，0=试用")
	private Boolean isFormal;

	@ApiModelProperty("用户数量限制")
	private Integer userLimitCount;

	@ApiModelProperty("生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@ApiModelProperty("申请天数")
	private Integer effectiveDays;

	@ApiModelProperty("过期时间，从什么时候失效")
	private LocalDateTime expireAt;

	@ApiModelProperty("额外申请项json，如：应用和功能")
	private String extJson;
    
    @ApiModelProperty("描述")
    private String remark;
    


}