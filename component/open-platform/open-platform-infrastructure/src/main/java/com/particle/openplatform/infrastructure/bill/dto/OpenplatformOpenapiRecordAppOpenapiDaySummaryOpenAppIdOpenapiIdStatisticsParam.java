package com.particle.openplatform.infrastructure.bill.dto;

import com.particle.global.dto.basic.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 开放平台应用开放接口日汇总统计 参数对象
 * </p>
 *
 * @author yangwei
 * @since 2024-10-09 15:16:57
 */
@Data
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam extends DTO {
    /**
     * 开放平台应用id
     */
    private Long openplatformAppId;

    /**
     * 开始处理时间,开始
     */
    @NotNull
    private LocalDate dayAtStart;
    /**
     * 开始处理时间,结束
     */
    @NotNull
    private LocalDate dayAtEnd;

    public static OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam create(Long openplatformAppId, LocalDate dayAtStart, LocalDate dayAtEnd) {
        OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam = new OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam();
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.openplatformAppId = openplatformAppId;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.dayAtStart = dayAtStart;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.dayAtEnd = dayAtEnd;
        return openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
    }
}
