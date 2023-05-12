package com.particle.tenant.client.tenantfunc.dto.command;

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
 * 租户分配功能菜单
 * </p>
 *
 * @author yangwei
 * @since 2023-04-19 11:09:14
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="租户分配功能菜单表单对象")
public class TenantAssignFuncCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "租户id不能为空")
    @ApiModelProperty(value = "租户id",required = true)
    private Long tenantId;

    /**
     * 支持按应用分配
     */
    @NotNull(message = "功能应用id 不能为空")
    @ApiModelProperty(value = "功能应用id",required = true)
    private Long funcApplicationId;

    @ApiModelProperty(value = "选择的功能菜单id")
    private List<Long> checkedFuncIds;

    @PropValid.DependCondition(message = "未选择的功能菜单id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @ApiModelProperty(value = "未选择的功能菜单id",example = "如果为懒加载请传该值")
    private List<Long> uncheckedFuncIds;

    @ApiModelProperty(value = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
