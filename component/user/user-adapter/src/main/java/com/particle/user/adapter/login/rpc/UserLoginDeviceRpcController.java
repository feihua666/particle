package com.particle.user.adapter.login.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.user.adapter.feign.client.login.rpc.UserLoginDeviceRpcFeignClient;
import com.particle.user.client.login.api.IUserLoginDeviceApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录设备远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Tag(name = "用户登录设备远程调用相关接口")
@RestController
@RequestMapping("/rpc/user-login-device")
public class UserLoginDeviceRpcController extends AbstractBaseRpcAdapter implements UserLoginDeviceRpcFeignClient {

	@Autowired
	private IUserLoginDeviceApplicationService iUserLoginDeviceApplicationService;









}
