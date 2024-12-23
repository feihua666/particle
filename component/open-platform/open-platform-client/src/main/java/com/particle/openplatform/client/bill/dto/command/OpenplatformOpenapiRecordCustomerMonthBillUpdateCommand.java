package com.particle.openplatform.client.bill.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放平台客户月账单 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Data
@Schema
public class OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand extends AbstractBaseUpdateCommand {


    @NotNull(message = "账单状态 不能为空")
        @Schema(description = "账单状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long statusDictId;


    @Schema(description = "描述")
    private String remark;









}
