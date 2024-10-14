package com.particle.openplatform.client.bill.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台应用月账单 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppMonthBillQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;

	@Schema(description = "appId")
	private String appId;

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