package com.particle.usagecount.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 使用次数记录明细 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Data
@Schema
public class UsageCountRecordDetailUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "使用次数记录id 不能为空")
        @Schema(description = "使用次数记录id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long usageCountRecordId;


    @NotNull(message = "使用次数定义id 不能为空")
        @Schema(description = "使用次数定义id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long usageCountDefineId;


    @Schema(description = "使用用户id")
    private Long usageUserId;


    @Schema(description = "使用租户id")
    private Long usageTenantId;









}
