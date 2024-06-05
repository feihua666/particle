package com.particle.tenant.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class TenantVO extends AbstractBaseIdVO {

    @Schema(description = "租户编码")
    private String code;
    
    @Schema(description = "租户名称")
    private String name;
    
    @Schema(description = "是否禁用")
    private Boolean isDisabled;
    
    @Schema(description = "禁用原因")
    private String disabledReason;
    
    @Schema(description = "姓名")
    private String userName;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "手机号")
    private String mobile;
    
    @Schema(description = "租户域名")
    private String tenantDomain;
    
    @Schema(description = "租户主题")
    private String tenantThemeJson;
    
    @Schema(description = "租户默认的页面路由")
    private String tenantDefaultRouteJson;

    @Schema(description = "是否正式，1=正式，0=试用")
    private Boolean isFormal;

    @Schema(description = "用户数量限制，为空或0代表不限制")
    private Integer userLimitCount;

    @Schema(description = "生效日期，从什么时候开始生效")
    private LocalDateTime effectiveAt;

    @Schema(description = "过期时间，从什么时候失效")
    private LocalDateTime expireAt;

    @Schema(description = "主用户，一般该用户为租户的超级管理员")
    private Long masterUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "masterUserId",mapValueField = "nickname")
    @Schema(description = "主用户昵称")
    private String masterUserNickname;

    @Schema(description = "租户logo地址")
    private String tenantLogoJson;

	@Schema(description = "租户logo文本")
	private String tenantLogoTextJson;

	@Schema(description = "租户web页面标题文本，添加主要是后台管理的页面标题")
	private String tenantWebTitleJson;

	@Schema(description = "图标,支持base64编码,支持http://开头的图片地址,支持本地文件路径地址，支持classpath文件路径")
	private String tenantFaviconJson;

    @Schema(description = "额外配置json")
    private String configJson;
    
    @Schema(description = "描述")
    private String remark;
    


}