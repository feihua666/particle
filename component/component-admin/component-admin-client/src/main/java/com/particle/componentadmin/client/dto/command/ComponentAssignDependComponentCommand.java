package com.particle.componentadmin.client.dto.command;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.form.Form;
import com.particle.global.validation.form.IFormValid;
import com.particle.global.validation.form.ValidContext;
import com.particle.global.validation.form.ValidResult;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 源组件分配依赖组件 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Form
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "源组件分配依赖组件表单对象")
public class ComponentAssignDependComponentCommand extends AbstractBaseCommand implements IFormValid {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "源组件id不能为空")
    @Schema(description = "源组件id")
    private Long componentId;

    @Schema(description = "选择的依赖组件id")
    private List<Long> checkedDependComponentIds;

    @PropValid.DependCondition(message = "未选择的依赖组件id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的依赖组件id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedDependComponentIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;

    @Schema(description = "是否必需")
    private Boolean isRequired;

    @Override
    public boolean valid(ValidResult result, ValidContext context) {
        if (CollectionUtil.isNotEmpty(checkedDependComponentIds) && checkedDependComponentIds.contains(componentId)) {
            result.setErrorMsg("自己不能依赖自己,选择的数据和自己不能相同");
            result.setReportOn("componentId");
            return false;
        }
        return true;
    }
}
