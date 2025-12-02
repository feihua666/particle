package com.particle.data.client.dynamicdata.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 动态数据分类 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Data
@Schema
public class DynamicDataCategoryQueryListCommand extends AbstractBaseQueryCommand {


    @Like
    @Schema(description = "数据类型名称")
    private String name;


    @Schema(description = "是否禁用")
    private Boolean isDisabled;


    @Schema(description = "动态数据分类类型")
    private Long typeDictId;










}
