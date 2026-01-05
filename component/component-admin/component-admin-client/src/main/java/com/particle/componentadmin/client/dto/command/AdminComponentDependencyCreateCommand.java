package com.particle.componentadmin.client.dto.command;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;

import com.particle.global.validation.form.Form;
import com.particle.global.validation.form.IFormValid;
import com.particle.global.validation.form.ValidContext;
import com.particle.global.validation.form.ValidResult;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 组件依赖关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Form
@Data
@Schema
public class AdminComponentDependencyCreateCommand extends AbstractBaseCommand implements IFormValid {

    @NotNull(message = "源组件id 不能为空")
    @Schema(description = "源组件id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long componentId;


    @NotNull(message = "依赖组件id 不能为空")
    @Schema(description = "依赖组件id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dependComponentId;


    @NotNull(message = "是否必需 不能为空")
    @Schema(description = "是否必需",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isRequired;


    @Schema(description = "备注")
    private String remark;


    @Override
    public boolean valid(ValidResult result, ValidContext context) {
        if (dependComponentId.equals(componentId)) {
            result.setErrorMsg("自己不能依赖自己");
            result.setReportOn("componentId");
            return false;
        }
        return true;
    }
}
