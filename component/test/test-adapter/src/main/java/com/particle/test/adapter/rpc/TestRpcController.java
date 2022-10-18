package com.particle.test.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.test.adapter.feign.client.rpc.TestRpcFeignClient;
import com.particle.test.client.api.ITestApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "测试远程调用相关接口")
@RestController
@RequestMapping("/rpc/test")
public class TestRpcController extends AbstractBaseRpcAdapter implements TestRpcFeignClient {

	@Autowired
	private ITestApplicationService iTestApplicationService;









}