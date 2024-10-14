package com.particle.openplatform.client.bill.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放平台应用月账单 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppMonthBillCreateCommand extends AbstractBaseCommand {



    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;

	@Schema(description = "appId")
	private String appId;

	@Schema(description = "客户id")
	private Long customerId;


    @NotNull(message = "年 不能为空")
        @Schema(description = "年",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer year;


    @NotNull(message = "月 不能为空")
        @Schema(description = "月",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer month;


    @NotNull(message = "调用总量 不能为空")
        @Schema(description = "调用总量",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalCall;


    @NotNull(message = "调用计费总量 不能为空")
        @Schema(description = "调用计费总量",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalFeeCall;


    @NotNull(message = "总消费金额 不能为空")
        @Schema(description = "总消费金额",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalFeeAmount;


    @NotNull(message = "账单状态 不能为空")
        @Schema(description = "账单状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long statusDictId;


    @Schema(description = "描述")
    private String remark;









}