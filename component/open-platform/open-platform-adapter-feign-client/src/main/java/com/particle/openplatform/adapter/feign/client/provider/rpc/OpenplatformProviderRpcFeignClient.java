package com.particle.openplatform.adapter.feign.client.provider.rpc;

import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 开放平台开放接口供应商远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformProviderRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_provider")
public interface OpenplatformProviderRpcFeignClient {
    /**
     * 根据id查询
     * @param openplatformProviderId
     * @return
     */
    @GetMapping("/getById")
    public SingleResponse<OpenplatformProviderVO> getById(@RequestParam Long openplatformProviderId);

    /**
     * 根据数据查询供应商id查询
     * @param dataQueryProviderId
     * @return
     */
    @GetMapping("/getByDataQueryProviderId")
    public SingleResponse<OpenplatformProviderVO> getByDataQueryProviderId(@RequestParam Long dataQueryProviderId);
}
