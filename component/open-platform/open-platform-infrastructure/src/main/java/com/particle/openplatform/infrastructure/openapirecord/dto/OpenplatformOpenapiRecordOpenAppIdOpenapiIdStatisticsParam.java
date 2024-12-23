package com.particle.openplatform.infrastructure.openapirecord.dto;

import com.particle.global.dto.basic.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 开放接口调用记录统计 参数对象
 * </p>
 *
 * @author yangwei
 * @since 2024-10-09 11:47:44
 */
@Data
public class OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam extends DTO {
    /**
     * 开放平台应用id
     */
    private Long openplatformAppId;

    /**
     * 开始处理时间,开始
     */
    @NotNull
    private LocalDateTime requestHandleAtStart;
    /**
     * 开始处理时间,结束
     */
    @NotNull
    private LocalDateTime requestHandleAtEnd;

    public static OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam create(Long openplatformAppId, LocalDateTime requestHandleAtStart, LocalDateTime requestHandleAtEnd) {
        OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam = new OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam();
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.openplatformAppId = openplatformAppId;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.requestHandleAtStart = requestHandleAtStart;
        openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.requestHandleAtEnd = requestHandleAtEnd;
        return openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
    }
}
