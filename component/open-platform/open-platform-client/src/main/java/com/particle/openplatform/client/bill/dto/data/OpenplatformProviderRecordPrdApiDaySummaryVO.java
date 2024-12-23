package com.particle.openplatform.client.bill.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * <p>
 * 开放平台供应商接口日汇总 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Data
@Schema
public class OpenplatformProviderRecordPrdApiDaySummaryVO extends AbstractBaseIdVO {

    @Schema(description = "供应商id")
    private Long openplatformProviderId;

    @Schema(description = "供应商接口id")
    private Long openplatformProviderApiId;

    @Schema(description = "日期")
    private LocalDate dayAt;

    @Schema(description = "调用总量")
    private Integer totalCall;

    @Schema(description = "调用计费总量")
    private Integer totalFeeCall;

    @Schema(description = "平均单价金额")
    private BigDecimal averageUnitPriceAmount;

    @Schema(description = "总消费金额")
    private Integer totalFeeAmount;

    @Schema(description = "描述")
    private String remark;



}
