package com.particle.role.client.rolefuncrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 角色分配功能
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "角色分配功能表单对象")
public class RoleAssignFuncCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "角色id不能为空")
    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "选择的功能id")
    private List<Long> checkedFuncIds;

    @PropValid.DependCondition(message = "未选择的功能id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的功能id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedFuncIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
