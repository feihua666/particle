package com.particle.openplatform.client.bill.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开放平台应用月账单统计 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-11 17:39:02
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppMonthBillStatisticCommand extends AbstractBaseCommand {

    @Schema(description = "是否统计月汇总")
    private Boolean isIncludeMonthSummary = false;

    /**
     * 该值仅在 isIncludeMonthSummary 为true 时有效
     */
    @Schema(description = "是否统计日汇总，该值仅在 isIncludeMonthSummary 为true 时有效")
    private Boolean isIncludeDaySummary = false;
}
