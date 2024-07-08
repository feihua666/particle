package com.particle.user.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.adapter.feign.client.rpc.UserRpcFeignClient;
import com.particle.user.adapter.tool.PasswordTool;
import com.particle.user.client.api.IUserApplicationService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "用户远程调用相关接口")
@RestController
@RequestMapping("/rpc/user")
public class UserRpcController extends AbstractBaseRpcAdapter implements UserRpcFeignClient {

	@Autowired
	private IUserApplicationService iUserApplicationService;

	@Operation(summary = "添加用户")
	@PostMapping("/create")
	@Override
	public SingleResponse<UserVO> create(@RequestBody UserCreateCommand userCreateCommand, @RequestBody UserIdentifierPwdCommand userIdentifierPwdCommand){
		PasswordTool.encodePassword(userIdentifierPwdCommand);

		return iUserApplicationService.create(userCreateCommand, userIdentifierPwdCommand);
	}
}