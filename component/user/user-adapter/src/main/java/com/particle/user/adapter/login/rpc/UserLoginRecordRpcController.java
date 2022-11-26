package com.particle.user.adapter.login.rpc;

import com.particle.user.client.login.api.IUserLoginRecordApplicationService;
import com.particle.user.adapter.feign.client.login.rpc.UserLoginRecordRpcFeignClient;
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
 * 用户登录记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Api(tags = "用户登录记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/user-login-record")
public class UserLoginRecordRpcController extends AbstractBaseRpcAdapter implements UserLoginRecordRpcFeignClient {

	@Autowired
	private IUserLoginRecordApplicationService iUserLoginRecordApplicationService;









}