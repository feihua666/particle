package com.particle.func.client.funcapplicationfuncrel.dto.command;


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
 * 功能应用分配功能
 * </p>
 *
 * @author yangwei
 * @since 2023-04-17 14:01:38
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="功能应用分配功能表单对象")
public class FuncApplicationAssignFuncCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "功能应用id不能为空")
    @ApiModelProperty(value = "功能应用id")
    private Long funcApplicationId;

    @ApiModelProperty(value = "选择的功能id")
    private List<Long> checkedFuncIds;

    @PropValid.DependCondition(message = "未选择的功能id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @ApiModelProperty(value = "未选择的功能id",example = "如果为懒加载请传该值")
    private List<Long> uncheckedFuncIds;

    @ApiModelProperty(value = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
