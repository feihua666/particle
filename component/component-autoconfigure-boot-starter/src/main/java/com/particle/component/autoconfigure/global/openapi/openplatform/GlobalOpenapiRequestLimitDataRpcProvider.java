package com.particle.component.autoconfigure.global.openapi.openplatform;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.limitrule.GlobalOpenapiRequestLimitService;
import com.particle.global.openapi.api.limitrule.IGlobalOpenapiRequestLimitDataProvider;
import com.particle.global.openapi.data.OpenapiAppQuotaLimitInfo;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiRequestLimitDataRpcFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/10 15:58
 */
public class GlobalOpenapiRequestLimitDataRpcProvider implements IGlobalOpenapiRequestLimitDataProvider {

    @Autowired
    private OpenplatformGlobalOpenapiRequestLimitDataRpcFeignClient openplatformGlobalOpenapiRequestLimitDataRpcFeignClient;

    @Override
    public GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic statistic(String clientId, LocalDateTime startAt, LocalDateTime endAt) {
        SingleResponse<GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic>
                requestLimitClientIdStatisticSingleResponse
                = openplatformGlobalOpenapiRequestLimitDataRpcFeignClient.clientIdStatistic(clientId, startAt, endAt);
        return requestLimitClientIdStatisticSingleResponse.getData();
    }

    @Override
    public GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic statistic(String clientId, String openapiCode, LocalDateTime startAt, LocalDateTime endAt) {
        SingleResponse<GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic>
                requestLimitClientIdAndOpenapiStatisticSingleResponse
                = openplatformGlobalOpenapiRequestLimitDataRpcFeignClient.clientIdAndOpenapiStatistic(clientId, openapiCode, startAt, endAt);
        return requestLimitClientIdAndOpenapiStatisticSingleResponse.getData();
    }

    @Override
    public OpenapiAppQuotaLimitInfo getOpenapiAppQuotaLimitInfo(String clientId) {
        SingleResponse<OpenapiAppQuotaLimitInfo>
                openapiAppQuotaLimitInfo
                = openplatformGlobalOpenapiRequestLimitDataRpcFeignClient.getOpenapiAppQuotaLimitInfo(clientId);
        return openapiAppQuotaLimitInfo.getData();
    }
}
