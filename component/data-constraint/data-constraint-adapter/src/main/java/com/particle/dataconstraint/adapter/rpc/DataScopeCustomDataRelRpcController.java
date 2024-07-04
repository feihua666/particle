package com.particle.dataconstraint.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataconstraint.client.api.IDataScopeCustomDataRelApplicationService;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeCustomDataRelRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据范围自定义数据关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Tag(name = "数据范围自定义数据关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_scope_custom_data_rel")
public class DataScopeCustomDataRelRpcController extends AbstractBaseRpcAdapter implements DataScopeCustomDataRelRpcFeignClient  {

	@Autowired
	private IDataScopeCustomDataRelApplicationService iDataScopeCustomDataRelApplicationService;


}