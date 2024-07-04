package com.particle.dataconstraint.client.dto.command;

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
 * 数据范围分配自定义数据
 * </p>
 *
 * @author yangwei
 * @since 2024-06-28 16:03:27
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "数据范围分配自定义数据表单对象")
public class DataScopeAssignCustomDataCommand extends AbstractBaseCommand {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "数据范围id不能为空")
    @Schema(description = "数据范围id")
    private Long dataScopeId;

    @Schema(description = "选择的自定义数据id")
    private List<Long> checkedDataIds;

    @PropValid.DependCondition(message = "未选择的自定义数据id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的自定义数据id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedDataIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;
}
