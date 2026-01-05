package com.particle.componentadmin.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.componentadmin.client.api.IAdminComponentDependencyApplicationService;
import com.particle.componentadmin.adapter.feign.client.rpc.AdminComponentDependencyRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 组件依赖关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Tag(name = "组件依赖关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/admin_component_dependency")
public class AdminComponentDependencyRpcController extends AbstractBaseRpcAdapter implements AdminComponentDependencyRpcFeignClient  {

	@Autowired
	private IAdminComponentDependencyApplicationService iAdminComponentDependencyApplicationService;


}