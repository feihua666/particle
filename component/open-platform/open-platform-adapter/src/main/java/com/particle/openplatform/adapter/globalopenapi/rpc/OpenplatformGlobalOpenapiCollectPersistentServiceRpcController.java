package com.particle.openplatform.adapter.globalopenapi.rpc;

import com.particle.global.dto.response.Response;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient;
import com.particle.openplatform.adapter.globalopenapi.OpenplatformGlobalOpenapiCollectPersistentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/10 16:20
 */

@Tag(name = "调用记录持久化远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_global_openapi_collect_persistent")
public class OpenplatformGlobalOpenapiCollectPersistentServiceRpcController implements OpenplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient {

    @Autowired
    private OpenplatformGlobalOpenapiCollectPersistentServiceImpl openplatformGlobalOpenapiCollectPersistentServiceImpl;

    @Override
    public Response save(OpenapiContext openapiContext) {
        openplatformGlobalOpenapiCollectPersistentServiceImpl.save(openapiContext);
        return Response.buildSuccess();
    }

    @Override
    public Response saveProvider(OpenapiContext openapiContext) {
        openplatformGlobalOpenapiCollectPersistentServiceImpl.saveProvider(openapiContext);
        return Response.buildSuccess();
    }
}
