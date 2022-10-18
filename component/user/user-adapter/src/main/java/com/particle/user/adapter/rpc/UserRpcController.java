package com.particle.user.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.user.adapter.feign.client.rpc.UserRpcFeignClient;
import com.particle.user.client.api.IUserApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台管理用户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "后台管理用户远程调用相关接口")
@RestController
@RequestMapping("/rpc/user")
public class UserRpcController extends AbstractBaseRpcAdapter implements UserRpcFeignClient {

	@Autowired
	private IUserApplicationService iUserApplicationService;









}