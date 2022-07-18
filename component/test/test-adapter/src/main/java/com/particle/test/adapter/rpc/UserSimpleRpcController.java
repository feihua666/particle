package com.particle.test.adapter.rpc;

import com.particle.test.client.api.IUserSimpleApplicationService;
import com.particle.test.adapter.feign.client.rpc.UserSimpleRpcFeignClient;
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
 * 简单用户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "简单用户远程调用相关接口")
@RestController
@RequestMapping("/rpc/user-simple")
public class UserSimpleRpcController extends AbstractBaseRpcAdapter implements UserSimpleRpcFeignClient {

	@Autowired
	private IUserSimpleApplicationService iUserSimpleApplicationService;









}