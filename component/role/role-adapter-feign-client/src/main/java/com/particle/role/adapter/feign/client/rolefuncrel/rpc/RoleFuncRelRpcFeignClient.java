package com.particle.role.adapter.feign.client.rolefuncrel.rpc;

import com.particle.global.dto.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * <p>
 * 角色菜单功能关系远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.name.roleFuncRel:roleFuncRel}",path = "/rpc/role-func-rel")
public interface RoleFuncRelRpcFeignClient {


	/**
	 * 删除功能id范围外的角色功能关系数据
	 * 该功能接口主要用于在租户应用分配功能后，可能功能会减少，将减少的功能联动角色一并减少
	 * @param scopedFuncIds
	 * @return
	 */
	@PostMapping("/deleteOutOfScopeByScopedFuncIds")
	Response deleteOutOfScopeByScopedFuncIds(List<Long> scopedFuncIds,Long tenantId);

}
