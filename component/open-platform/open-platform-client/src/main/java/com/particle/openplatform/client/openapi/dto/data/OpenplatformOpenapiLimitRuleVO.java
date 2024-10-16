package com.particle.openplatform.client.openapi.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 开放平台开放接口限制规则 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Data
@Schema
public class OpenplatformOpenapiLimitRuleVO extends AbstractBaseIdVO {

    @Schema(description = "限制名称")
    private String name;
    
    @Schema(description = "限制方式")
    private Long limitTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "limitTypeDictId",mapValueField = "name")
    @Schema(description = "限制方式对应字典名称")
    private String limitTypeDictName;
        
    @Schema(description = "限制条数")
    private Integer limitCount;
    
    @Schema(description = "限制金额费用")
    private Integer limitFee;
    
    @Schema(description = "限制周期")
    private Long limitPeriodDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "limitPeriodDictId",mapValueField = "name")
    @Schema(description = "限制周期对应字典名称")
    private String limitPeriodDictName;
        
    @Schema(description = "限制速率")
    private Integer limitRate;
    
    @Schema(description = "描述")
    private String remark;
    


}
