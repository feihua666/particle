package com.particle.openplatform.client.app.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 开放平台应用 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Data
@Schema
public class OpenplatformAppVO extends AbstractBaseIdVO {

    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "appId")
    private String appId;
    
    @Schema(description = "归属用户id")
    private Long ownerUserId;

	@TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "ownerUserId",mapValueField = "nickname")
	@Schema(description = "归属用户昵称")
	private String ownerUserNickname;
    
    @Schema(description = "归属客户id")
    private Long ownerCustomerId;

	@TransBy(type = TransConstants.TRANS_CRM_CUSTOMER_BY_ID,byFieldName = "ownerCustomerId",mapValueField = "name")
	@Schema(description = "归属客户名称")
	private String ownerCustomerName;
    
    @Schema(description = "请求算法与密钥等相关配置")
    private String requestAlgorithmSecretJson;
    
    @Schema(description = "响应算法与密钥等相关配置")
    private String responseAlgorithmSecretJson;
    
    @Schema(description = "访问范围配置")
    private String scopes;
    
    @Schema(description = "计费规则id")
    private Long openplatformOpenapiFeeId;

	@TransBy(tableName = TransTableNameConstants.component_openplatform_openapi_fee, byFieldName = "openplatformOpenapiFeeId", mapValueField = "name")
	@Schema(description = "计费规则名称")
	private String openplatformOpenapiFeeName;

	@Schema(description = "限制规则id，不配置不限制，应用级限制")
	private Long openplatformOpenapiLimitRuleId;

	@TransBy(tableName = TransTableNameConstants.component_openplatform_openapi_limit_rule, byFieldName = "openplatformOpenapiLimitRuleId", mapValueField = "name")
	@Schema(description = "限制规则名称")
	private String openplatformOpenapiLimitRuleName;

	@Schema(description = "是否禁用")
	private Boolean isDisabled;

	@Schema(description = "禁用原因")
	private String disabledReason;

	@Schema(description = "是否检查签名,主要用于在oauth2 token时可以不检查")
	private Boolean isCheckSignature;

}