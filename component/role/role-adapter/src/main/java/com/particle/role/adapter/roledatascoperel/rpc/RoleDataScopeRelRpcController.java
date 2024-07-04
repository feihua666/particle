package com.particle.role.adapter.roledatascoperel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.role.client.roledatascoperel.api.IRoleDataScopeRelApplicationService;
import com.particle.role.adapter.feign.client.roledatascoperel.rpc.RoleDataScopeRelRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色数据范围关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Tag(name = "角色数据范围关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/role_data_scope_rel")
public class RoleDataScopeRelRpcController extends AbstractBaseRpcAdapter implements RoleDataScopeRelRpcFeignClient  {

	@Autowired
	private IRoleDataScopeRelApplicationService iRoleDataScopeRelApplicationService;


}