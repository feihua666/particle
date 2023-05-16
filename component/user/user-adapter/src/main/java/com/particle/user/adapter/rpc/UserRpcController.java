package com.particle.user.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.trans.result.TransResult;
import com.particle.user.adapter.feign.client.rpc.UserRpcFeignClient;
import com.particle.user.adapter.feign.client.rpc.UserTransRpcFeignClient;
import com.particle.user.adapter.tool.PasswordTool;
import com.particle.user.client.api.IUserApplicationService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.data.UserTransVO;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "用户远程调用相关接口")
@RestController
@RequestMapping("/rpc/user")
public class UserRpcController extends AbstractBaseRpcAdapter implements UserRpcFeignClient , UserTransRpcFeignClient {


	@Autowired
	private UserTransServiceImpl userTransService;
	@Autowired
	private IUserApplicationService iUserApplicationService;

	@Override
	public boolean supportBatch(String type) {
		return userTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<UserTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return userTransService.transBatch(type, keys);
	}



	@ApiOperation("添加用户")
	@PostMapping("/create")
	@Override
	public SingleResponse<UserVO> create(@RequestBody UserCreateCommand userCreateCommand, @RequestBody UserIdentifierPwdCommand userIdentifierPwdCommand){
		PasswordTool.encodePassword(userIdentifierPwdCommand);

		return iUserApplicationService.create(userCreateCommand, userIdentifierPwdCommand);
	}
}