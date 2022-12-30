package com.particle.role.client.rolefuncrel.dto.command;


import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
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
@ApiModel(value="功能分配角色表单对象")
public class FuncAssignRoleCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "功能id不能为空")
    @ApiModelProperty(value = "功能id")
    private Long funcId;

    @ApiModelProperty(value = "选择的角色id")
    private List<Long> checkedRoleIds;

    @PropValid.DependCondition(message = "未选择的角色id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @ApiModelProperty(value = "未选择的角色id",notes = "如果为懒加载请传该值")
    private List<Long> uncheckedRoleIds;

    @ApiModelProperty(value = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
