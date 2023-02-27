package com.particle.lowcode.adapter.generator.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.lowcode.adapter.feign.client.generator.rpc.LowcodeDatasourceRpcFeignClient;
import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码数据源远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Api(tags = "低代码数据源远程调用相关接口")
@RestController
@RequestMapping("/rpc/lowcode-datasource")
public class LowcodeDatasourceRpcController extends AbstractBaseRpcAdapter implements LowcodeDatasourceRpcFeignClient {

	@Autowired
	private ILowcodeDatasourceApplicationService iLowcodeDatasourceApplicationService;









}