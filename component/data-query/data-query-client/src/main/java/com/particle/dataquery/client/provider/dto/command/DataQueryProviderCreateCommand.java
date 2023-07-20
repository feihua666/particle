package com.particle.dataquery.client.provider.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 数据查询供应商 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@PropValid
@Data
@Schema
public class DataQueryProviderCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "供应商名称 不能为空")
    @Schema(description = "供应商名称",required = true)
    private String name;

    @NotNull(message = "是否禁用 不能为空")
    @Schema(description = "是否禁用",required = true)
    private Boolean isDisabled;

    /**
     * 禁用时，禁用原因必填
     */
    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "姓名")
    private String userName;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "描述")
    private String remark;

}
