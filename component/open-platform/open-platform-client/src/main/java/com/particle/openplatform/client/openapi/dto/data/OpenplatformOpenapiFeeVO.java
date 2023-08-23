package com.particle.openplatform.client.openapi.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 开放平台开放接口费用 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Data
@Schema
public class OpenplatformOpenapiFeeVO extends AbstractBaseIdVO {

	@Schema(description = "费用名称，可以理解为类似一个套餐")
	private String name;

    @Schema(description = "单价")
    private Integer price;
    
    @Schema(description = "计费方式")
    private Long feeTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "feeTypeDictId",mapValueField = "name")
    @Schema(description = "计费方式对应字典名称")
    private String feeTypeDictName;
        
    @Schema(description = "按计费方式配置")
    private String feeTypeJson;
    
    @Schema(description = "去重方式")
    private Long deduplicateTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "deduplicateTypeDictId",mapValueField = "name")
    @Schema(description = "去重方式对应字典名称")
    private String deduplicateTypeDictName;
        
    @Schema(description = "去重条数")
    private Integer deduplicateCount;
    
    @Schema(description = "是否按请求参数去重")
    private Boolean isDeduplicateByParameter;
    
    @Schema(description = "是否检查是否返回值")
    private Boolean isCheckHasValue;
    
    @Schema(description = "是否检查处理时长")
    private Boolean isCheckHandleDuration;
    
    @Schema(description = "处理时长")
    private Integer handleDuration;
    
    @Schema(description = "描述")
    private String remark;
    


}