package com.particle.data.client.dynamictable.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 动态数据表格字段 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Data
@Schema
public class DynamicTableFieldPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;

    @Like(left = true,right = true)
    @Schema(description = "字段名称")
    private String name;

    @Like(left = true,right = true)
    @Schema(description = "字段注释")
    private String comment;


    @Schema(description = "字段类型")
    private String type;


    @Schema(description = "是否必填")
    private Boolean isRequired;

	@Schema(description = "字段默认值")
	private String defaultValue;

    /**
     * 如果{@link DynamicTableFieldPageQueryCommand#dynamicTableId} 为空，则尝试使用该值对应的动态数据表格id
     */
    @Schema(description = "动态数据指标分类id")
    private Long dynamicDataIndicatorCategoryId;


}
