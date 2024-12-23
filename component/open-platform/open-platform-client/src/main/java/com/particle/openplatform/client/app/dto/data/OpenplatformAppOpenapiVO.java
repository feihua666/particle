package com.particle.openplatform.client.app.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台应用与开放接口配置 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Data
@Schema
public class OpenplatformAppOpenapiVO extends AbstractBaseIdVO {

    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_app, byFieldName = "openplatformAppId", mapValueField = "name")
    @Schema(description = "开放平台应用名称")
    private String openplatformAppName;

    @Schema(description = "开放接口id")
    private Long openplatformOpenapiId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_openapi, byFieldName = "openplatformOpenapiId", mapValueField = "name")
    @Schema(description = "开放接口名称")
    private String openplatformOpenapiName;

    @Schema(description = "计费规则id")
    private Long openplatformOpenapiFeeId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_openapi_fee, byFieldName = "openplatformOpenapiFeeId", mapValueField = "name")
    @Schema(description = "计费规则名称")
    private String openplatformOpenapiFeeName;

	@Schema(description = "限制规则id，不配置不限制，应用和接口级限制")
	private Long openplatformOpenapiLimitRuleId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_openapi_limit_rule, byFieldName = "openplatformOpenapiLimitRuleId", mapValueField = "name")
    @Schema(description = "限制规则名称")
    private String openplatformOpenapiLimitRuleName;

	@Schema(description = "指定供应商，如果不指定将按默认编码调用")
	private Long openplatformProviderId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_provider, byFieldName = "openplatformOpenapiLimitRuleId", mapValueField = "name")
    @Schema(description = "指定供应商名称")
    private String openplatformProviderName;

	@Schema(description = "描述")
	private String remark;


}
