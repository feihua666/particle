package com.particle.openplatform.client.bill.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * <p>
 * 开放平台应用开放接口日实时汇总 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;


    @Schema(description = "appId")
    private String appId;


    @Schema(description = "开放平台接口id")
    private Long openplatformOpenapiId;


    @Schema(description = "日期")
    private LocalDate dayAt;


    @Schema(description = "客户id")
    private Long customerId;


    @Schema(description = "调用总量")
    private Integer totalCall;


    @Schema(description = "调用计费总量")
    private Integer totalFeeCall;


    @Schema(description = "平均单价金额")
    private BigDecimal averageUnitPriceAmount;


    @Schema(description = "总消费金额")
    private Integer totalFeeAmount;










}
