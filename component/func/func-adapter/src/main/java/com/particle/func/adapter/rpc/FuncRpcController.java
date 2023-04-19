package com.particle.func.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.func.adapter.feign.client.rpc.FuncRpcFeignClient;
import com.particle.func.adapter.feign.client.rpc.FuncTransRpcFeignClient;
import com.particle.func.client.api.IFuncApplicationService;
import com.particle.func.client.dto.data.FuncTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单功能远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "菜单功能远程调用相关接口")
@RestController
@RequestMapping("/rpc/func")
public class FuncRpcController extends AbstractBaseRpcAdapter implements FuncRpcFeignClient, FuncTransRpcFeignClient {

	@Autowired
	private IFuncApplicationService iFuncApplicationService;

	@Override
	public boolean supportBatch(String type) {
		return FuncTransRpcFeignClient.super.supportBatch(type);
	}

	@Override
	public List<TransResult<FuncTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return FuncTransRpcFeignClient.super.transBatch(type, keys);
	}

}