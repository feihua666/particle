package com.particle.openplatform.client.doc.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档示例代码 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Data
@Schema
public class OpenplatformDocApiDocExampleCodeVO extends AbstractBaseIdVO {

    @Schema(description = "开发语言")
    private Long langDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "langDictId",mapValueField = "name")
    @Schema(description = "开发语言对应字典名称")
    private String langDictName;

    @Schema(description = "示例代码片段")
    private String exampleCode;

    @Schema(description = "示例代码下载地址")
    private String exampleDownloadUrl;

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


    @Schema(description = "描述")
    private String remark;


    public static OpenplatformDocApiDocExampleCodeVO create(OpenplatformDocApiDocTemplateExampleCodeVO openplatformDocApiDocTemplateExampleCodeVO) {
        OpenplatformDocApiDocExampleCodeVO openplatformDocApiDocExampleCodeVO = new OpenplatformDocApiDocExampleCodeVO();
        openplatformDocApiDocExampleCodeVO.langDictId = openplatformDocApiDocTemplateExampleCodeVO.getLangDictId();
        openplatformDocApiDocExampleCodeVO.exampleCode = openplatformDocApiDocTemplateExampleCodeVO.getExampleCode();
        openplatformDocApiDocExampleCodeVO.exampleDownloadUrl = openplatformDocApiDocTemplateExampleCodeVO.getExampleDownloadUrl();

        openplatformDocApiDocExampleCodeVO.remark = openplatformDocApiDocTemplateExampleCodeVO.getRemark();
        return openplatformDocApiDocExampleCodeVO;
    }

}
