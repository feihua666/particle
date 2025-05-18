package com.particle.openplatform.client.openapi.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Data
@Schema
public class OpenplatformOpenapiVO extends AbstractBaseIdTreeVO {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "是否为组")
    private Boolean isGroup;

    @Schema(description = "接口权限码")
    private String permissions;

    @Schema(description = "接口地址")
    private String url;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "默认计费规则id")
    private Long defaultOpenplatformOpenapiFeeId;

	@Schema(description = "供应商配置json")
	private String providerConfigJson;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_openapi_fee, byFieldName = "defaultOpenplatformOpenapiFeeId", mapValueField = "name")
    @Schema(description = "计费规则id")
    private String defaultOpenplatformOpenapiFeeName;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_openplatform_openapi, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
