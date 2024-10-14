package com.particle.openplatform.client.bill.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开放平台应用开放接口月汇总统计 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-09 16:24:48
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryStatisticCommand extends AbstractBaseCommand {

    @Schema(description = "是否统计日汇总")
    private Boolean isIncludeDaySummary = false;
}
