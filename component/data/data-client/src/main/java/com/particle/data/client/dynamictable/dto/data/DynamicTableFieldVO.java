package com.particle.data.client.dynamictable.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 动态数据表格字段 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Data
@Schema
public class DynamicTableFieldVO extends AbstractBaseIdVO {

    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;

    @Schema(description = "动态数据表格名称")
    @TransBy(tableName = TransTableNameConstants.component_data_dynamic_table, byFieldName = "dynamicTableId", mapValueField = "name")
    private String dynamicTableName;

    @Schema(description = "动态数据表格注释")
    @TransBy(tableName = TransTableNameConstants.component_data_dynamic_table, byFieldName = "dynamicTableId", mapValueField = "comment")
    private String dynamicTableComment;

    @Schema(description = "字段名称")
    private String name;

    @Schema(description = "字段注释")
    private String comment;

    @Schema(description = "字段类型")
    private String type;

    @Schema(description = "是否必填")
    private Boolean isRequired;

	@Schema(description = "字段默认值")
	private String defaultValue;



}