package com.particle.dataconstraint.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 数据对象 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Data
@Schema
public class DataObjectVO extends AbstractBaseIdVO {

    @Schema(description = "数据对象编码")
    private String code;
    
    @Schema(description = "数据对象名称")
    private String name;
    
    @Schema(description = "数据范围自定义时用来绑定自定义数据的url")
    private String customDataUrl;
    
    @Schema(description = "自定义数据是否懒加载")
    private Boolean isCustomDataLazy;
    
    @Schema(description = "自定义数据交互方式")
    private Long customDataTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "customDataTypeDictId",mapValueField = "value")
    @Schema(description = "自定义数据交互方式对应字典名称")
    private String customDataTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "customDataTypeDictId",mapValueField = "name")
    @Schema(description = "自定义数据交互方式对应字典名称")
    private String customDataTypeDictName;
        
    @Schema(description = "数据交互方式内容")
    private String customDataConfigJson;

	@Schema(description = "是否禁用")
	private Boolean isDisabled;

	@Schema(description = "禁用原因")
	private String disabledReason;
    
    @Schema(description = "描述")
    private String remark;
    


}