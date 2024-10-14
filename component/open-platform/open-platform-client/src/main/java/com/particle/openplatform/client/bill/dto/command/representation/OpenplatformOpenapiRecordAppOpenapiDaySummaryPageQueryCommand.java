package com.particle.openplatform.client.bill.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * <p>
 * 开放平台应用开放接口日汇总 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand extends AbstractBasePageQueryCommand {



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
