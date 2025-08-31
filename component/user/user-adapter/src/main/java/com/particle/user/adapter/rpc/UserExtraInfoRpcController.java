package com.particle.user.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.user.client.api.IUserExtraInfoApplicationService;
import com.particle.user.adapter.feign.client.rpc.UserExtraInfoRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户扩展信息远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Tag(name = "用户扩展信息远程调用相关接口")
@RestController
@RequestMapping("/rpc/user_extra_info")
public class UserExtraInfoRpcController extends AbstractBaseRpcAdapter implements UserExtraInfoRpcFeignClient  {

	@Autowired
	private IUserExtraInfoApplicationService iUserExtraInfoApplicationService;


}