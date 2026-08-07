package com.particle.agi.client.model.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * AI模型提供商 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Data
@Schema
public class AgiModelProviderVO extends AbstractBaseIdVO {

    @Schema(description = "提供商编码")
    private String code;
    
    @Schema(description = "提供商显示名称")
    private String name;
    
    @Schema(description = "提供商类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "提供商类型对应字典名称")
    private String typeDictName;
        
    @Schema(description = "API 基础地址")
    private String baseUrl;
    
    @Schema(description = "加密的 API Key")
    private String apiKeyEncrypted;
    
    @Schema(description = "是否禁用")
    private Boolean isDisabled;
    
    @Schema(description = "扩展配置")
    private String extraConfigJson;
    
    @Schema(description = "排序")
    private Integer seq;
    
    @Schema(description = "描述")
    private String remark;
    


}
