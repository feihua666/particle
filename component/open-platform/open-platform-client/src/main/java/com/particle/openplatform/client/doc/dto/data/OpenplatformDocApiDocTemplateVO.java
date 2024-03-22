package com.particle.openplatform.client.doc.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
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
    private String responseMaxDurationDesc;
    
    @Schema(description = "认证方式")
    private String authenticationType;
    


}
