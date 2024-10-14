package com.particle.openplatform.infrastructure.bill.dto;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放平台应用开放接口月汇总统计 客户参数对象
 * </p>
 *
 * @author yangwei
 * @since 2024-10-11 16:41:32
 */
@Data
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam extends DTO {
    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 开始处理年份,开始
     */
    @NotNull
    private Integer year;
    /**
     * 开始处理年份,结束
     */
    @NotNull
    private Integer month;

    public static OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam create(Long customerId, Integer year, Integer month) {
        OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam = new OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam();
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.customerId = customerId;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.year = year;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.month = month;
        return openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
    }
}
