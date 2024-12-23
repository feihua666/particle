package com.particle.role.client.roledatascoperel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 数据范围分配角色 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "数据范围分配角色表单对象")
public class DataScopeAssignRoleCommand extends AbstractBaseCommand {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "数据范围id不能为空")
    @Schema(description = "数据范围id")
    private Long dataScopeId;

    @NotNull(message = "数据对象id不能为空")
    @Schema(description = "数据对象id")
    private Long dataObjectId;

    @Schema(description = "选择的角色id")
    private List<Long> checkedRoleIds;

    @PropValid.DependCondition(message = "未选择的角色id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的角色id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedRoleIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;

}
