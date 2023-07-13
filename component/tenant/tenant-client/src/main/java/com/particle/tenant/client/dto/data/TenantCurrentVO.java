package com.particle.tenant.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 当前租户 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-07-13 15:36:07
 */
@Data
@ApiModel
public class TenantCurrentVO extends AbstractBaseIdVO {

    @ApiModelProperty("租户编码")
    private String code;
    
    @ApiModelProperty("租户名称")
    private String name;
    
    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;
    
    @ApiModelProperty("禁用原因")
    private String disabledReason;
    
    @ApiModelProperty("姓名")
    private String userName;
    
    @ApiModelProperty("邮箱")
    private String email;
    
    @ApiModelProperty("手机号")
    private String mobile;
    
    @ApiModelProperty("租户域名")
    private String tenantDomain;
    
    @ApiModelProperty("租户主题")
    private String tenantThemeJson;
    
    @ApiModelProperty("租户默认的页面路由")
    private String tenantDefaultRouteJson;

    @ApiModelProperty("是否正式，1=正式，0=试用")
    private Boolean isFormal;

    @ApiModelProperty("用户数量限制，为空或0代表不限制")
    private Integer userLimitCount;

    @ApiModelProperty("生效日期，从什么时候开始生效")
    private LocalDateTime effectiveAt;

    @ApiModelProperty("过期时间，从什么时候失效")
    private LocalDateTime expireAt;

    @ApiModelProperty("主用户，一般该用户为租户的超级管理员")
    private Long masterUserId;

    @ApiModelProperty("租户logo地址")
    private String tenantLogoJson;

    @ApiModelProperty("额外配置json")
    private String configJson;
    
    @ApiModelProperty("描述")
    private String remark;

}