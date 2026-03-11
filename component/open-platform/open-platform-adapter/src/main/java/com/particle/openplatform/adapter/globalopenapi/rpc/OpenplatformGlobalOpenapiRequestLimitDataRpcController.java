package com.particle.openplatform.adapter.globalopenapi.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.limitrule.GlobalOpenapiRequestLimitService;
import com.particle.global.openapi.data.OpenapiAppQuotaLimitInfo;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiRequestLimitDataRpcFeignClient;
import com.particle.openplatform.adapter.globalopenapi.OpenplatformGlobalOpenapiRequestLimitDataProviderImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/10 16:02
 */

@Tag(name = "请求限制远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_global_openapi_request_limit")
public class OpenplatformGlobalOpenapiRequestLimitDataRpcController implements OpenplatformGlobalOpenapiRequestLimitDataRpcFeignClient {


    @Autowired
    private OpenplatformGlobalOpenapiRequestLimitDataProviderImpl openplatformGlobalOpenapiRequestLimitDataProviderImpl;

    @Operation(summary = "统计clientId 对应的调用量")
    @Override
    public SingleResponse<GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic> clientIdStatistic(String clientId, LocalDateTime startAt, LocalDateTime endAt) {
        GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic statistic = openplatformGlobalOpenapiRequestLimitDataProviderImpl.statistic(clientId, startAt, endAt);
        return SingleResponse.of(statistic);
    }
    @Operation(summary = "统计clientId 对应的openapiCode的调用量")
    @Override
    public SingleResponse<GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic> clientIdAndOpenapiStatistic(String clientId, String openapiCode, LocalDateTime startAt, LocalDateTime endAt) {
        GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic statistic = openplatformGlobalOpenapiRequestLimitDataProviderImpl.statistic(clientId, openapiCode, startAt, endAt);
        return SingleResponse.of(statistic);
    }
    @Operation(summary = "获取clientId 对应的接口调用量额度限制信息")
    @Override
    public SingleResponse<OpenapiAppQuotaLimitInfo> getOpenapiAppQuotaLimitInfo(String clientId) {
        OpenapiAppQuotaLimitInfo openapiAppQuotaLimitInfo = openplatformGlobalOpenapiRequestLimitDataProviderImpl.getOpenapiAppQuotaLimitInfo(clientId);
        return SingleResponse.of(openapiAppQuotaLimitInfo);
    }
}
