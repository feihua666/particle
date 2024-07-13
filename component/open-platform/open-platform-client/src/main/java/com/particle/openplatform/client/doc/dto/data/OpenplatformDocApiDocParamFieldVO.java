package com.particle.openplatform.client.doc.dto.data;

import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档参数字段 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Data
@Schema
public class OpenplatformDocApiDocParamFieldVO extends OpenplatformDocApiDocParamFieldBasicVO {

	@Schema(description = "最大长度")
	private String exampleValue;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api_doc_param_field, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}