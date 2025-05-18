package com.particle.user.adapter.feign.client.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 用户远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.name.user:user}",path = "/rpc/user")
public interface UserRpcFeignClient {
	/**
	 * 添加用户
	 * @param userCreateCommand
	 * @param userIdentifierPwdCommand
	 * @return
	 */
	public SingleResponse<UserVO> create(@RequestBody UserCreateCommand userCreateCommand, @RequestBody UserIdentifierPwdCommand userIdentifierPwdCommand);

}
