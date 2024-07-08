package com.particle.func.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.func.adapter.feign.client.rpc.FuncTransRpcFeignClient;
import com.particle.func.client.dto.data.FuncTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单翻译功能远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 14:05:43
 */
@Tag(name = "菜单翻译功能远程调用相关接口")
@RestController
@RequestMapping("/rpc/func")
public class FuncTransRpcController extends AbstractBaseRpcAdapter implements FuncTransRpcFeignClient {

	@Autowired
	private FuncTransServiceImpl funcTransService;

	@Override
	public boolean supportBatch(String type) {
		return funcTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<FuncTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return funcTransService.transBatch(type, keys);
	}

}