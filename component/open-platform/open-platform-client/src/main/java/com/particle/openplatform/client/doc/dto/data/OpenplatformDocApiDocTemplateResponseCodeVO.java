package com.particle.openplatform.client.doc.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 开放接口文档模板响应码 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Data
@Schema
public class OpenplatformDocApiDocTemplateResponseCodeVO extends AbstractBaseIdVO {

    @Schema(description = "编码")
    private String code;
    
    @Schema(description = "http响应码")
    private Integer httpCode;
    
    @Schema(description = "是否计费")
    private Boolean isCharge;
    
    @Schema(description = "字段说明")
    private String explanation;
    
    @Schema(description = "开放接口文档模板id")
    private Long openplatformDocApiDocTemplateId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api_doc_template, byFieldName = "openplatformDocApiDocTemplateId", mapValueField = "name")
    @Schema(description = "开放接口文档模板名称")
    private String openplatformDocApiDocTemplateName;

    @Schema(description = "是否全局通用码")
    private Boolean isGlobal;

	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;
    
    @Schema(description = "描述")
    private String remark;
    


}