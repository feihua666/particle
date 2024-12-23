package com.particle.openplatform.client.doc.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档模板 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Data
@Schema
public class OpenplatformDocApiDocTemplateVO extends AbstractBaseIdVO {

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "请求地址前缀")
    private String requestUrlPrefix;

	@Schema(description = "内网请求地址前缀，可全局配置")
	private String requestUrlIntranetPrefix;

    @Schema(description = "请求类型")
    private Long requestTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "requestTypeDictId",mapValueField = "name")
    @Schema(description = "请求类型对应字典名称")
    private String requestTypeDictName;

    @Schema(description = "请求体类型")
    private Long requestBodyTypeDictId;

	@Schema(description = "请求参数类型，字典id，如：string、array")
	private Long requestParamTypeDictId;

	@Schema(description = "请求参数嵌套字段类型，字典id，一般用于字段类型为array时里面的类型，如：string、object")
	private Long requestParamNestTypeDictId;

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
    private String responseMaxDurationDesc;

    @Schema(description = "认证方式")
    private String authenticationType;



}
