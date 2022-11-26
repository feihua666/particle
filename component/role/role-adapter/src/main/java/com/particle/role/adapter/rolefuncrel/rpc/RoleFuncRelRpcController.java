package com.particle.role.adapter.rolefuncrel.rpc;

import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import com.particle.role.adapter.feign.client.rolefuncrel.rpc.RoleFuncRelRpcFeignClient;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色菜单功能关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色菜单功能关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/role-func-rel")
public class RoleFuncRelRpcController extends AbstractBaseRpcAdapter implements RoleFuncRelRpcFeignClient {

	@Autowired
	private IRoleFuncRelApplicationService iRoleFuncRelApplicationService;









}