package com.particle.openplatform.infrastructure.bill.dto;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam extends DTO {
    /**
     * 开放平台应用id
     */
    private Long openplatformAppId;
    /**
     * 开放接口id，这里只存储接口，不存储分组
     */
    private Long openplatformOpenapiId;
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

    public static OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam create(Long openplatformAppId,Long openplatformOpenapiId,
                                                                                                          LocalDate dayAtStart, LocalDate dayAtEnd) {
        OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam = new OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdOpenapiIdStatisticsParam();
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.openplatformAppId = openplatformAppId;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.openplatformOpenapiId = openplatformOpenapiId;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.dayAtStart = dayAtStart;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.dayAtEnd = dayAtEnd;
        return openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
    }
}
