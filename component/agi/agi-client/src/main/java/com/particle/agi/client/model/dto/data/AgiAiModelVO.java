package com.particle.agi.client.model.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.math.BigDecimal;
/**
 * <p>
 * AI模型 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Data
@Schema
public class AgiAiModelVO extends AbstractBaseIdVO {

    @Schema(description = "模型编码")
    private String code;
    
    @Schema(description = "模型显示名称")
    private String name;
    
    @Schema(description = "模型类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "模型类型对应字典名称")
    private String typeDictName;
        
    @Schema(description = "最大 Token 数")
    private Integer maxTokens;
    
    @Schema(description = "默认温度值（0.00-2.00）")
    private BigDecimal defaultTemperature;
        
    @Schema(description = "默认 Top P 值")
    private BigDecimal defaultTopP;
        
    @Schema(description = "是否禁用")
    private Boolean isDisabled;
    
    @Schema(description = "是否为默认模型")
    private Boolean isDefault;
    
    @Schema(description = "扩展配置")
    private String extraConfigJson;
    
    @Schema(description = "所属提供商ID")
    private Long agiModelProviderId;
    
    @Schema(description = "排序")
    private Integer seq;
    
    @Schema(description = "描述")
    private String remark;
    


}
