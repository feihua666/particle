package com.particle.dataconstraint.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 数据范围 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Data
@Schema
public class DataScopeVO extends AbstractBaseIdVO {

    @Schema(description = "数据范围编码")
    private String code;
    
    @Schema(description = "数据范围名称")
    private String name;
    
    @Schema(description = "数据对象id")
    private Long dataObjectId;

	@Schema(description = "约束条件内容类型，字典id")
	private Long constraintContentTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "constraintContentTypeDictId",mapValueField = "value")
    @Schema(description = "约束条件内容类型，字典值")
    private String constraintContentTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "constraintContentTypeDictId",mapValueField = "name")
    @Schema(description = "约束条件内容类型，字典名称")
    private String constraintContentTypeDictName;

    @TransBy(tableName = TransTableNameConstants.component_data_object, byFieldName = "dataObjectId", mapValueField = "name")
    @Schema(description = "数据对象名称")
    private String dataObjectName;
    
    @Schema(description = "约束条件内容")
    private String constraintContent;
    
    @Schema(description = "是否自定义")
    private Boolean isCustom;
    
    @Schema(description = "是否用于删除")
    private Boolean isForDelete;
    
    @Schema(description = "是否用于修改")
    private Boolean isForUpdate;
    
    @Schema(description = "是否用于查询")
    private Boolean isForQuery;
    
    @Schema(description = "是否用于其它")
    private Boolean isForOther;
    
    @Schema(description = "描述")
    private String remark;
    


}