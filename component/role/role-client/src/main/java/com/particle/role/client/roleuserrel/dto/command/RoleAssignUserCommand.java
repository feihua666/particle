package com.particle.role.client.roleuserrel.dto.command;

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
 * 角色分配用户
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="角色分配用户表单对象")
public class RoleAssignUserCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "选择的用户id")
    private List<Long> checkedUserIds;

    @PropValid.DependCondition(message = "未选择的用户id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @ApiModelProperty(value = "未选择的用户id",example = "如果为懒加载请传该值")
    private List<Long> uncheckedUserIds;

    @ApiModelProperty(value = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
