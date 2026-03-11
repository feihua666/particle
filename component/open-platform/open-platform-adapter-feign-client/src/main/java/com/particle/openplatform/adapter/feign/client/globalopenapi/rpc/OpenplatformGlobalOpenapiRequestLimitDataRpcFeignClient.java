package com.particle.openplatform.adapter.feign.client.globalopenapi.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.limitrule.GlobalOpenapiRequestLimitService;
import com.particle.global.openapi.data.OpenapiAppQuotaLimitInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/10 16:03
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformGlobalOpenapiRequestLimitDataRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_global_openapi_request_limit")
public interface OpenplatformGlobalOpenapiRequestLimitDataRpcFeignClient {


    /**
     * 统计clientId 对应的调用量
     * @param clientId
     * @param startAt
     * @param endAt
     * @return
     */
    @GetMapping("/clientIdStatistic")
    SingleResponse<GlobalOpenapiRequestLimitService.RequestLimitClientIdStatistic> clientIdStatistic(@RequestParam String clientId,
                                                                             @RequestParam LocalDateTime startAt,
                                                                             @RequestParam LocalDateTime endAt);

    /**
     * 统计clientId 对应的openapiCode的调用量
     * @param clientId
     * @param openapiCode
     * @param startAt
     * @param endAt
     * @return
     */
    @GetMapping("/clientIdAndOpenapiStatistic")
    SingleResponse<GlobalOpenapiRequestLimitService.RequestLimitClientIdAndOpenapiStatistic> clientIdAndOpenapiStatistic(@RequestParam String clientId,
                                                                                       @RequestParam String openapiCode,
                                                                                       @RequestParam LocalDateTime startAt,
                                                                                       @RequestParam LocalDateTime endAt);

    /**
     * 获取当前clientId 对应的接口调用量额度限制信息
     * @param clientId
     * @return
     */
    @GetMapping("/getOpenapiAppQuotaLimitInfo")
    SingleResponse<OpenapiAppQuotaLimitInfo> getOpenapiAppQuotaLimitInfo(@RequestParam String clientId);
}
