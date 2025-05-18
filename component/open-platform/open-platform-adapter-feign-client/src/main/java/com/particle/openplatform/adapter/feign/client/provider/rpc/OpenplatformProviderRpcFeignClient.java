package com.particle.openplatform.adapter.feign.client.provider.rpc;

import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 开放平台开放接口供应商远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_provider")
public interface OpenplatformProviderRpcFeignClient {
    /**
     * 根据id查询
     * @param openplatformProviderId
     * @return
     */
    @GetMapping("/getById")
    public SingleResponse<OpenplatformProviderVO> getById(Long openplatformProviderId);

    /**
     * 根据数据查询供应商id查询
     * @param dataQueryProviderId
     * @return
     */
    @GetMapping("/getByDataQueryProviderId")
    public SingleResponse<OpenplatformProviderVO> getByDataQueryProviderId(Long dataQueryProviderId);
}
