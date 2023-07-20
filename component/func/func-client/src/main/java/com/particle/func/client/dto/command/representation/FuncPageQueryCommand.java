package com.particle.func.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 菜单功能 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@OrderBy("seq")
@Data
@Schema
public class FuncPageQueryCommand extends AbstractBaseTreePageQueryCommand {


    @Like
    @Schema(description = "编码，左前缀匹配")
    private String code;

    @Like
    @Schema(description = "名称，左模糊查询")
    private String name;

    @Schema(description = "功能分组id")
    private Long funcGroupId;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Schema(description = "类型,字典id")
    private Long typeDictId;

    @Schema(description = "归属组件")
    private String componentOf;

    @Schema(description = "父级id")
    private Long parentId;

    @Schema(description = "功能应用id")
    private Long funcApplicationId;
}
