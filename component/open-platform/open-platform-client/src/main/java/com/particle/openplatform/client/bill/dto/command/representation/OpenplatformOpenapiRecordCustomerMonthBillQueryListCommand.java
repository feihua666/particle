package com.particle.openplatform.client.bill.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台客户月账单 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Data
@Schema
public class OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "客户id")
    private Long customerId;


    @Schema(description = "年")
    private Integer year;


    @Schema(description = "月")
    private Integer month;


    @Schema(description = "调用总量")
    private Integer totalCall;


    @Schema(description = "调用计费总量")
    private Integer totalFeeCall;


    @Schema(description = "总消费金额")
    private Integer totalFeeAmount;


    @Schema(description = "账单状态")
    private Long statusDictId;










}
