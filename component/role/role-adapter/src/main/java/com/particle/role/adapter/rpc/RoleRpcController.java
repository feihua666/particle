package com.particle.role.adapter.rpc;

import com.particle.role.client.api.IRoleApplicationService;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
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
 * 角色远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色远程调用相关接口")
@RestController
@RequestMapping("/rpc/role")
public class RoleRpcController extends AbstractBaseRpcAdapter implements RoleRpcFeignClient {

	@Autowired
	private IRoleApplicationService iRoleApplicationService;









}