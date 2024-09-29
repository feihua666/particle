package com.particle.openplatform.client.bill.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * <p>
 * 开放平台供应商接口日汇总 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Data
@Schema
public class OpenplatformProviderRecordPrdApiDaySummaryCreateCommand extends AbstractBaseCommand {



    @Schema(description = "供应商id")
    private Long openplatformProviderId;


    @Schema(description = "供应商接口id")
    private Long openplatformProviderApiId;


    @NotNull(message = "日期 不能为空")
        @Schema(description = "日期",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate dayAt;
    

    @NotNull(message = "调用总量 不能为空")
        @Schema(description = "调用总量",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalCall;


    @NotNull(message = "调用计费总量 不能为空")
        @Schema(description = "调用计费总量",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalFeeCall;


    @NotNull(message = "平均单价金额 不能为空")
        @Schema(description = "平均单价金额",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer averageUnitPriceAmount;


    @NotNull(message = "总消费金额 不能为空")
        @Schema(description = "总消费金额",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalFeeAmount;


    @Schema(description = "描述")
    private String remark;









}
