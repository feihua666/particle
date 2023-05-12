package com.particle.tenant.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 租户 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Data
@ApiModel
public class TenantVO extends AbstractBaseIdVO {

    @ApiModelProperty("租户编码")
    private String code;
    
    @ApiModelProperty("租户名称")
    private String name;
    
    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;
    
    @ApiModelProperty("禁用原因")
    private String disabledReason;
    
    @ApiModelProperty("联系人姓名")
    private String contactUserName;
    
    @ApiModelProperty("联系人邮箱")
    private String contactUserEmail;
    
    @ApiModelProperty("联系人电话")
    private String contactUserPhone;
    
    @ApiModelProperty("租户域名")
    private String tenantDomain;
    
    @ApiModelProperty("租户主题")
    private String tenantTheme;
    
    @ApiModelProperty("租户默认的页面路由")
    private String tenantDefaultRoute;

    @ApiModelProperty("是否正式，1=正式，0=试用")
    private Boolean isFormal;

    @ApiModelProperty("用户数量限制")
    private Integer userLimitCount;

    @ApiModelProperty("生效日期，从什么时候开始生效")
    private LocalDateTime effectiveAt;

    @ApiModelProperty("失效日期，从什么时候失效")
    private LocalDateTime invalidAt;

    @ApiModelProperty("主用户，一般该用户为租户的超级管理员")
    private Long masterUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "masterUserId",mapValueField = "nickname")
    @ApiModelProperty("主用户昵称")
    private String masterUserNickname;

    @ApiModelProperty("租户logo地址")
    private String tenantLogoUrl;

    @ApiModelProperty("额外配置json")
    private String configJson;
    
    @ApiModelProperty("描述")
    private String remark;
    


}