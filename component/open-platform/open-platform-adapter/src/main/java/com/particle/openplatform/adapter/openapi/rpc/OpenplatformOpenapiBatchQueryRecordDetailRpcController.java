package com.particle.openplatform.adapter.openapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordDetailApplicationService;
import com.particle.openplatform.adapter.feign.client.openapi.rpc.OpenplatformOpenapiBatchQueryRecordDetailRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口批量查询记录明细远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Tag(name = "开放接口批量查询记录明细远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi_batch_query_record_detail")
public class OpenplatformOpenapiBatchQueryRecordDetailRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiBatchQueryRecordDetailRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiBatchQueryRecordDetailApplicationService iOpenplatformOpenapiBatchQueryRecordDetailApplicationService;


}