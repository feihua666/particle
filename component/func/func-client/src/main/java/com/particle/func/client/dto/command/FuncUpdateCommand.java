package com.particle.func.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单功能 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@PropValid
@Data
@Schema
public class FuncUpdateCommand extends AbstractBaseUpdateCommand {


    @NotEmpty(message = "编码不能为空")
    @Schema(description = "编码",required = true)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @Schema(description = "名称",required = true)
    private String name;

    @NotNull(message = "功能分组id不能为空")
    @Schema(description = "功能分组id",required = true)
    private Long funcGroupId;

    @Schema(description = "图标")
    private String icon;

    @NotNull(message = "是否禁用不能为空")
    @Schema(description = "是否禁用",required = true)
    private Boolean isDisabled;

    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @SetNullWhenNull
    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "地址")
    private String url;

    @Schema(description = "shiro权限串，多个以逗号分隔")
    private String permissions;

    @NotNull(message = "类型,字典id不能为空")
    @Schema(description = "类型,字典id",required = true)
    private Long typeDictId;

    @NotNull(message = "是否展示不能为空")
    @Schema(description = "是否展示")
    private Boolean isShow;

    @NotEmpty(message = "归属组件不能为空")
    @Schema(description = "归属组件",required = true)
    private String componentOf;

    @Schema(description = "描述")
    private String remark;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq;

    @Schema(description = "父级id")
    private Long parentId;
}
