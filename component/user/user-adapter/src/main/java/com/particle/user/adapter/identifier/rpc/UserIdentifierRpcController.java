package com.particle.user.adapter.identifier.rpc;

import com.particle.user.client.identifier.api.IUserIdentifierApplicationService;
import com.particle.user.adapter.feign.client.identifier.rpc.UserIdentifierRpcFeignClient;
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
 * 用户登录标识远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "用户登录标识远程调用相关接口")
@RestController
@RequestMapping("/rpc/user-identifier")
public class UserIdentifierRpcController extends AbstractBaseRpcAdapter implements UserIdentifierRpcFeignClient {

	@Autowired
	private IUserIdentifierApplicationService iUserIdentifierApplicationService;









}