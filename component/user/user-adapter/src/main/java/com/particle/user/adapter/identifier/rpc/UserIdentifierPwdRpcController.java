package com.particle.user.adapter.identifier.rpc;

import com.particle.user.client.identifier.api.IUserIdentifierPwdApplicationService;
import com.particle.user.adapter.feign.client.identifier.rpc.UserIdentifierPwdRpcFeignClient;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户密码远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "用户密码远程调用相关接口")
@RestController
@RequestMapping("/rpc/user-identifier-pwd")
public class UserIdentifierPwdRpcController extends AbstractBaseRpcAdapter implements UserIdentifierPwdRpcFeignClient {

	@Autowired
	private IUserIdentifierPwdApplicationService iUserIdentifierPwdApplicationService;









}