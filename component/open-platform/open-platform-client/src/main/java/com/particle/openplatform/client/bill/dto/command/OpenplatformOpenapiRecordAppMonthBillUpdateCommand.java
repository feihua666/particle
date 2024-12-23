package com.particle.openplatform.client.bill.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放平台应用月账单 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppMonthBillUpdateCommand extends AbstractBaseUpdateCommand {


    @NotNull(message = "账单状态 不能为空")
        @Schema(description = "账单状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long statusDictId;


    @Schema(description = "描述")
    private String remark;









}
