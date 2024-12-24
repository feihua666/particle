package com.particle.openplatform.client.openapi.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放平台开放接口限制规则 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Data
@Schema
public class OpenplatformOpenapiLimitRuleCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "限制名称 不能为空")
        @Schema(description = "限制名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "限制方式 不能为空")
        @Schema(description = "限制方式",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long limitTypeDictId;


    @NotNull(message = "限制条数 不能为空")
        @Schema(description = "限制条数",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer limitCount;


    @NotNull(message = "限制金额费用 不能为空")
        @Schema(description = "限制金额费用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer limitFee;


    @NotNull(message = "限制周期 不能为空")
        @Schema(description = "限制周期",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long limitPeriodDictId;


    @NotNull(message = "限制速率 不能为空")
        @Schema(description = "限制速率",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer limitRate;

	@Schema(description = "ip白名单，多个用英文逗号分隔")
	private String whiteIps;

	@Schema(description = "ip黑名单，多个用英文逗号分隔")
	private String blackIps;


    @Schema(description = "描述")
    private String remark;









}