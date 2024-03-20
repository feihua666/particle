package com.particle.openplatform.client.doc.dto.data;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Data
@Schema
public class OpenplatformDocApiDocVO extends AbstractBaseIdVO {

	@Schema(description = "开放接口文档接口id")
	private Long openplatformDocApiId;


    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api, byFieldName = "openplatformDocApiId", mapValueField = "code")
    @Schema(description = "开放接口文档接口编码")
    private String openplatformDocApiCode;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api, byFieldName = "openplatformDocApiId", mapValueField = "name")
    @Schema(description = "开放接口文档接口名称")
    private String openplatformDocApiName;

    @Schema(description = "请求地址前缀")
    private String requestUrlPrefix;
    
    @Schema(description = "请求地址")
    private String requestUrl;
    
    @Schema(description = "请求类型")
    private Long requestTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "requestTypeDictId",mapValueField = "name")
    @Schema(description = "请求类型对应字典名称")
    private String requestTypeDictName;
        
    @Schema(description = "请求体类型")
    private Long requestBodyTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "requestBodyTypeDictId",mapValueField = "name")
    @Schema(description = "请求体类型对应字典名称")
    private String requestBodyTypeDictName;
        
    @Schema(description = "响应体类型")
    private Long responseBodyTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseBodyTypeDictId",mapValueField = "name")
    @Schema(description = "响应体类型对应字典名称")
    private String responseBodyTypeDictName;
        
    @Schema(description = "最大响应时长")
    private Integer responseMaxDuration;
    
    @Schema(description = "响应时长文本")
    private Integer responseMaxDurationDesc;
    
    @Schema(description = "认证方式")
    private String authenticationType;
    
    @Schema(description = "详细描述")
    private String descriptionDetail;
    
    @Schema(description = "请求参数示例")
    private String requestParamExample;
    
    @Schema(description = "响应参数示例")
    private String responseParamExample;

	@Schema(description = "开放接口文档模板id")
	private Long openplatformDocApiDocTemplateId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api_doc_template, byFieldName = "openplatformDocApiDocTemplateId", mapValueField = "name")
    @Schema(description = "开放接口文档模板名称")
    private String openplatformDocApiDocTemplateName;


    /**
     * 根据模板填充必要的值
     * @param openplatformDocApiDocTemplateVO
     */
    public void fillByOpenplatformDocApiDocTemplateVO(OpenplatformDocApiDocTemplateVO openplatformDocApiDocTemplateVO) {
        if (openplatformDocApiDocTemplateVO == null) {
            return;
        }

        if (StrUtil.isEmpty(requestUrlPrefix)) {
            this.requestUrlPrefix = openplatformDocApiDocTemplateVO.getRequestUrlPrefix();
        }
        if (requestTypeDictId == null) {
            this.requestTypeDictId = openplatformDocApiDocTemplateVO.getRequestTypeDictId();
        }
        if (requestBodyTypeDictId == null) {
            this.requestBodyTypeDictId = openplatformDocApiDocTemplateVO.getRequestBodyTypeDictId();
        }
        if (responseBodyTypeDictId == null) {
            this.responseBodyTypeDictId = openplatformDocApiDocTemplateVO.getResponseBodyTypeDictId();
        }
        if (responseMaxDuration == null) {
            this.responseMaxDuration = openplatformDocApiDocTemplateVO.getResponseMaxDuration();
        }
        if (responseMaxDurationDesc == null) {
            this.responseMaxDurationDesc = openplatformDocApiDocTemplateVO.getResponseMaxDurationDesc();
        }
        if (StrUtil.isEmpty(authenticationType)) {
            this.authenticationType = openplatformDocApiDocTemplateVO.getAuthenticationType();
        }
    }

}