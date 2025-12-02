package com.particle.data.client.dynamicdata.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 动态数据指标分类 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "动态数据分类id")
    private Long dynamicDataCategoryId;

    @Like
    @Schema(description = "动态数据指标分类名称")
    private String name;


    @Schema(description = "是否禁用")
    private Boolean isDisabled;


    @Schema(description = "动态数据指标分类类型")
    private Long typeDictId;

	@Schema(description = "指标数量")
	private Integer dynamicDataIndicatorNum;

	@Schema(description = "数据数量")
	private Integer dynamicDataIndicatorCategoryDataNum;










}