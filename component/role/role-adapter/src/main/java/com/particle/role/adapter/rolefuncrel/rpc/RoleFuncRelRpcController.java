package com.particle.role.adapter.rolefuncrel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.Response;
import com.particle.role.adapter.feign.client.rolefuncrel.rpc.RoleFuncRelRpcFeignClient;
import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色菜单功能关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色菜单功能关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/role-func-rel")
public class RoleFuncRelRpcController extends AbstractBaseRpcAdapter implements RoleFuncRelRpcFeignClient {

	@Autowired
	private IRoleFuncRelApplicationService iRoleFuncRelApplicationService;

	@Operation(summary = "删除功能id范围外的角色功能关系数据")
	@PostMapping("/deleteOutOfScopeByScopedFuncIds")
	@Override
	public Response deleteOutOfScopeByScopedFuncIds(List<Long> scopedFuncIds,Long tenantId) {
		return iRoleFuncRelApplicationService.deleteOutOfScopeByScopedFuncIds(scopedFuncIds,tenantId);
	}
}
