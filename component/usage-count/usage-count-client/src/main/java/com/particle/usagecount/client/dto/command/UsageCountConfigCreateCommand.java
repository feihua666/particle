package com.particle.usagecount.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 使用次数配置 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Data
@Schema
public class UsageCountConfigCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "名称 不能为空")
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "使用次数定义id 不能为空")
    @Schema(description = "使用次数定义id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long usageCountDefineId;


    @Schema(description = "限制的最大使用次数")
    private Integer limitCount;


    @NotNull(message = "限制规则类型字典id 不能为空")
    @Schema(description = "限制规则类型字典id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long limitRuleTypeDictId;


    @NotNull(message = "限制周期字典id 不能为空")
    @Schema(description = "限制周期字典id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long limitPeriodDictId;

    @Schema(description = "限制租户id，如果为空代表是全局的设置")
    private Long limitTenantId;

    @Schema(description = "超出提示信息")
    private String exceedTip;


    @Schema(description = "备注")
    private String remark;









}
