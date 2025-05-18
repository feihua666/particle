package com.particle.dataquery.infrastructure.gateway.impl;

import com.particle.dataquery.domain.gateway.DataQueryOpenplatformGateway;
import com.particle.dataquery.domain.value.DataQueryOpenplatformProvider;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.adapter.feign.client.provider.rpc.OpenplatformProviderRpcFeignClient;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 依赖开放平台 开放接口供应商远程调用相关接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025/5/8 12:42
 */
@Component
public class DataQueryOpenplatformGatewayImpl implements DataQueryOpenplatformGateway {

    private OpenplatformProviderRpcFeignClient openplatformProviderRpcFeignClient;

    @Override
    public DataQueryOpenplatformProvider getOpenplatformProviderByDataQueryProviderId(Long dataQueryProviderId) {
        SingleResponse<OpenplatformProviderVO> byDataQueryProviderId = openplatformProviderRpcFeignClient.getByDataQueryProviderId(dataQueryProviderId);
        if (byDataQueryProviderId.isSuccess()) {
            OpenplatformProviderVO openplatformProviderVO = byDataQueryProviderId.getData();
            if (openplatformProviderVO != null) {
                return DataQueryOpenplatformProvider.create(openplatformProviderVO.getId(),openplatformProviderVO.getCode(),openplatformProviderVO.getName());
            }
        }
        return null;
    }

    @Autowired
    public void setOpenplatformProviderRpcFeignClient(OpenplatformProviderRpcFeignClient openplatformProviderRpcFeignClient) {
        this.openplatformProviderRpcFeignClient = openplatformProviderRpcFeignClient;
    }
}
