package com.particle.openplatform.client.bill.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台供应商月账单 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Data
@Schema
public class OpenplatformProviderRecordPrdMonthBillPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "供应商id")
    private Long openplatformProviderId;


    @Schema(description = "年")
    private Integer year;


    @Schema(description = "月")
    private Integer month;


    @Schema(description = "调用总量")
    private Integer totalCall;


    @Schema(description = "调用计费总量")
    private Integer totalFeeCall;


    @Schema(description = "平均单价金额")
    private Integer averageUnitPriceAmount;


    @Schema(description = "总消费金额")
    private Integer totalFeeAmount;










}
