package com.particle.openplatform.client.app.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放平台应用额度 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Data
@Schema
public class OpenplatformAppQuotaUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "开放平台应用id 不能为空")
        @Schema(description = "开放平台应用id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformAppId;


    @NotNull(message = "限制方式 不能为空")
        @Schema(description = "限制方式",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long limitTypeDictId;


    @NotNull(message = "限制条数 不能为空")
        @Schema(description = "限制条数",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer limitCount;


    @NotNull(message = "限制金额费用 不能为空")
        @Schema(description = "限制金额费用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer limitFee;


    @Schema(description = "描述")
    private String remark;









}
