package com.particle.openplatform.infrastructure.bill.dto;

import com.particle.global.dto.basic.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放平台应用开放接口月汇总统计 应用参数对象
 * </p>
 *
 * @author yangwei
 * @since 2024-10-11 16:41:32
 */
@Data
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam extends DTO {
    /**
     * 开放平台应用id
     */
    private Long openplatformAppId;

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

    public static OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam create(Long openplatformAppId, Integer year, Integer month) {
        OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam = new OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam();
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.openplatformAppId = openplatformAppId;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.year = year;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.month = month;
        return openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
    }
}
