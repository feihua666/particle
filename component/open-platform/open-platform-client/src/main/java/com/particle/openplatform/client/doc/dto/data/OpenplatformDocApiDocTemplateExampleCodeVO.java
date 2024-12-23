package com.particle.openplatform.client.doc.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档模板示例代码 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Data
@Schema
public class OpenplatformDocApiDocTemplateExampleCodeVO extends AbstractBaseIdVO {

    @Schema(description = "开发语言")
    private Long langDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "langDictId",mapValueField = "name")
    @Schema(description = "开发语言对应字典名称")
    private String langDictName;

    @Schema(description = "示例代码片段")
    private String exampleCode;

    @Schema(description = "示例代码下载地址")
    private String exampleDownloadUrl;

    @Schema(description = "开放接口文档模板id")
    private Long openplatformDocApiDocTemplateId;

	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api_doc_template, byFieldName = "openplatformDocApiDocTemplateId", mapValueField = "name")
    @Schema(description = "开放接口文档模板名称")
    private String openplatformDocApiDocTemplateName;

    @Schema(description = "描述")
    private String remark;



}
