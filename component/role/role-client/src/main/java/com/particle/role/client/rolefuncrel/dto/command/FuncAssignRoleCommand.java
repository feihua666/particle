package com.particle.role.client.rolefuncrel.dto.command;


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
 * 功能分配角色
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "功能分配角色表单对象")
public class FuncAssignRoleCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "功能id不能为空")
    @Schema(description = "功能id")
    private Long funcId;

    @Schema(description = "选择的角色id")
    private List<Long> checkedRoleIds;

    @PropValid.DependCondition(message = "未选择的角色id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的角色id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedRoleIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
