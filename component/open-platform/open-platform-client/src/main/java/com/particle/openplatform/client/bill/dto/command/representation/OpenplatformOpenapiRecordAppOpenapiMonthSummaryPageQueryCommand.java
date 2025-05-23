package com.particle.openplatform.client.bill.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 开放平台应用开放接口月汇总 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;


    @Schema(description = "appId")
    private String appId;


    @Schema(description = "开放平台接口id")
    private Long openplatformOpenapiId;


    @Schema(description = "年")
    private Integer year;


    @Schema(description = "月")
    private Integer month;


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
