package com.particle.role.client.roleuserrel.dto.command;


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
 * 用户分配角色
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "用户分配角色表单对象")
public class UserAssignRoleCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户id不能为空")
    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "选择的角色id")
    private List<Long> checkedRoleIds;

    @PropValid.DependCondition(message = "未选择的角色id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(description = "未选择的角色id",example = "如果为懒加载请传该值")
    private List<Long> uncheckedRoleIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
