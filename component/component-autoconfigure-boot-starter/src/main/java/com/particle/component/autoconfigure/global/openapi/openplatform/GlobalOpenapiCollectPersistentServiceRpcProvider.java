package com.particle.component.autoconfigure.global.openapi.openplatform;

import com.particle.global.openapi.api.GlobalOpenapiCollectPersistentService;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/10 18:57
 */
public class GlobalOpenapiCollectPersistentServiceRpcProvider implements GlobalOpenapiCollectPersistentService {


    @Autowired
    private OpenplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient openplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient;

    @Override
    public void save(OpenapiContext openapiContext) {
        openplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient.save(openapiContext);
    }

    @Override
    public void saveProvider(OpenapiContext openapiContext) {
        openplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient.saveProvider(openapiContext);

    }
}
