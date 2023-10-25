package com.particle.usagecount.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 使用次数配置 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Data
@Schema
public class UsageCountConfigVO extends AbstractBaseIdVO {

    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "使用次数定义id")
    private Long usageCountDefineId;

    @TransBy(tableName = TransTableNameConstants.component_usage_count_define, byFieldName = "usageCountDefineId", mapValueField = "name")
    @Schema(description = "使用次数定义")
    private String usageCountDefineName;
    
    @Schema(description = "限制的最大使用次数")
    private Integer limitCount;
    
    @Schema(description = "限制规则类型字典id")
    private Long limitRuleTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "limitRuleTypeDictId",mapValueField = "name")
    @Schema(description = "限制规则类型字典id对应字典名称")
    private String limitRuleTypeDictName;
        
    @Schema(description = "限制周期字典id")
    private Long limitPeriodDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "limitPeriodDictId",mapValueField = "name")
    @Schema(description = "限制周期字典id对应字典名称")
    private String limitPeriodDictName;

    @Schema(description = "限制租户id，如果为空代表是全局的设置")
    private Long limitTenantId;

    @TransBy(type = TransConstants.TRANS_TENANT_BY_ID,byFieldName = "limitTenantId",mapValueField = "name")
    @Schema(description = "限制租户名称")
    private String limitTenantName;

    @Schema(description = "备注")
    private String remark;
    


}