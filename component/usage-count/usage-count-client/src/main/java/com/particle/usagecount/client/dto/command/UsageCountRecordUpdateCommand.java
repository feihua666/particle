package com.particle.usagecount.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 使用次数记录 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Data
@Schema
public class UsageCountRecordUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "使用次数key 不能为空")
        @Schema(description = "使用次数key",requiredMode = Schema.RequiredMode.REQUIRED)
    private String usageCountKey;


    @NotNull(message = "使用次数定义id 不能为空")
        @Schema(description = "使用次数定义id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long usageCountDefineId;


    @NotNull(message = "使用次数配置id 不能为空")
        @Schema(description = "使用次数配置id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long usageCountConfigId;


    @Schema(description = "已使用次数")
    private Integer usageCount;


    @Schema(description = "使用用户id")
    private Long usageUserId;


    @Schema(description = "使用租户id")
    private Long usageTenantId;









}
