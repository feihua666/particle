package com.particle.openplatform.client.doc.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 开放接口文档响应码 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Data
@Schema
public class OpenplatformDocApiDocResponseCodeVO extends AbstractBaseIdVO {

    @Schema(description = "编码")
    private String code;

	@Schema(description = "业务状态码，码值")
	private String codeStatus;

	@Schema(description = "http响应码,如：200、500")
	private Integer httpCode;
    
    @Schema(description = "是否计费")
    private Boolean isCharge;
    
    @Schema(description = "字段说明")
    private String explanation;
    
    @Schema(description = "开放接口文档接口id")
    private Long openplatformDocApiId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api, byFieldName = "openplatformDocApiId", mapValueField = "code")
    @Schema(description = "开放接口文档接口编码")
    private String openplatformDocApiCode;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api, byFieldName = "openplatformDocApiId", mapValueField = "name")
    @Schema(description = "开放接口文档接口名称")
    private String openplatformDocApiName;

    @Schema(description = "开放接口文档id")
    private Long openplatformDocApiDocId;

	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;


    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api_doc, byFieldName = "openplatformDocApiDocId", mapValueField = "requestUrl")
    @Schema(description = "开放接口文档请求地址")
    private String openplatformDocApiDocRequestUrl;

    @Schema(description = "是否全局通用码")
    private Boolean isGlobal;
    
    @Schema(description = "描述")
    private String remark;


    public static OpenplatformDocApiDocResponseCodeVO create(OpenplatformDocApiDocTemplateResponseCodeVO openplatformDocApiDocTemplateResponseCodeVO) {
        OpenplatformDocApiDocResponseCodeVO openplatformDocApiDocResponseCodeVO = new OpenplatformDocApiDocResponseCodeVO();
        openplatformDocApiDocResponseCodeVO.code = openplatformDocApiDocTemplateResponseCodeVO.getCode();
        openplatformDocApiDocResponseCodeVO.codeStatus = openplatformDocApiDocTemplateResponseCodeVO.getCodeStatus();
        openplatformDocApiDocResponseCodeVO.httpCode = openplatformDocApiDocTemplateResponseCodeVO.getHttpCode();
        openplatformDocApiDocResponseCodeVO.isCharge = openplatformDocApiDocTemplateResponseCodeVO.getIsCharge();
        openplatformDocApiDocResponseCodeVO.explanation = openplatformDocApiDocTemplateResponseCodeVO.getExplanation();

        openplatformDocApiDocResponseCodeVO.isGlobal = openplatformDocApiDocTemplateResponseCodeVO.getIsGlobal();
        openplatformDocApiDocResponseCodeVO.remark = openplatformDocApiDocTemplateResponseCodeVO.getRemark();
        return openplatformDocApiDocResponseCodeVO;
    }

}