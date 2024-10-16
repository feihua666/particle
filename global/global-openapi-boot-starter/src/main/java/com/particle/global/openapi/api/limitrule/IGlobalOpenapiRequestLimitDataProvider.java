package com.particle.global.openapi.api.limitrule;

import com.particle.global.openapi.data.OpenapiAppQuotaLimitInfo;

import java.time.LocalDateTime;

/**
 * <p>
 * 用于统计接口调用量
 * </p>
 *
 * @author yangwei
 * @since 2024/10/14 18:29
 */
public interface IGlobalOpenapiRequestLimitDataProvider {

    /**
     * 统计clientId 对应的调用量
     * @param clientId
     * @param startAt
     * @param endAt
     * @return
     */
    GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic statistic(String clientId, LocalDateTime startAt, LocalDateTime endAt);

    /**
     * 统计clientId 对应的openapiCode的调用量
     * @param clientId
     * @param openapiCode
     * @param startAt
     * @param endAt
     * @return
     */
    GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic statistic(String clientId, String openapiCode,LocalDateTime startAt, LocalDateTime endAt);

    /**
     * 获取当前clientId 对应的接口调用量额度限制信息
     * @param clientId
     * @return
     */
    OpenapiAppQuotaLimitInfo getOpenapiAppQuotaLimitInfo(String clientId);
}
