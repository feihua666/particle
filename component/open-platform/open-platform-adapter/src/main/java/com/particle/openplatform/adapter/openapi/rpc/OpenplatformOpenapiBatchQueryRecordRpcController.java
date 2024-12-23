package com.particle.openplatform.adapter.openapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.adapter.feign.client.openapi.rpc.OpenplatformOpenapiBatchQueryRecordRpcFeignClient;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口批量查询记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Tag(name = "开放接口批量查询记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi_batch_query_record")
public class OpenplatformOpenapiBatchQueryRecordRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiBatchQueryRecordRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiBatchQueryRecordApplicationService iOpenplatformOpenapiBatchQueryRecordApplicationService;


}
