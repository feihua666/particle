package com.particle.data.client.dynamicdata.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 动态数据分类 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Data
@Schema
public class DynamicDataCategoryVO extends AbstractBaseIdVO {

    @Schema(description = "数据类型名称")
    private String name;
    
    @Schema(description = "是否禁用")
    private Boolean isDisabled;
    
    @Schema(description = "动态数据分类类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "动态数据分类类型对应字典名称")
    private String typeDictName;
        
    @Schema(description = "备注信息")
    private String remark;
    


}
