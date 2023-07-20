package com.particle.tenant.client.tenantfuncapplication.dto.command;

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
 * 租户分配功能应用
 * </p>
 *
 * @author yangwei
 * @since 2023-04-19 11:09:14
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "租户分配功能应用表单对象")
public class TenantAssignFuncApplicationCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "租户id不能为空")
    @Schema(description = "租户id")
    private Long tenantId;

    @Schema(description = "选择的功能应用id")
    private List<Long> checkedFuncApplicationIds;

    @PropValid.DependCondition(message = "未选择的功能应用id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(description = "未选择的功能应用id",example = "如果为懒加载请传该值")
    private List<Long> uncheckedFuncApplicationIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
