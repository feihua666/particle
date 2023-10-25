package com.particle.usagecount.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 使用次数记录 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@PropValid
@Data
@Schema
public class UsageCountRecordMarkCommand extends AbstractBaseCommand {


    @PropValid.DependCondition(message = "使用次数定义编码 和 匹配的url地址 至少填写一个",dependProp = "urlPattern",ifEqual = "empty")
    @Schema(description = "使用次数定义编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String usageCountDefineCode;

    @PropValid.DependCondition(message = "使用次数定义编码 和 匹配的url地址 至少填写一个",dependProp = "usageCountDefineCode",ifEqual = "empty")
    @Schema(description = "匹配的url地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String urlPattern;

    /**
     * 由后端逻辑填充，前端填充无效
     */
    @NotNull(message = "当前用户id不能为空")
    @Schema(description = "当前用户id",requiredMode = Schema.RequiredMode.REQUIRED,hidden = true)
    private Long currentUserId;

    /**
     * 由后端逻辑填充，前端填充无效
     * 在多租户系统是应该是有值的，否则可能会没有值，这时将使用全局配置
     */
    @Schema(description = "当前租户id",hidden = true)
    private Long currentTenantId;

}
