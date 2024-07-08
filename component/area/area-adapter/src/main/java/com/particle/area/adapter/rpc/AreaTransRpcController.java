package com.particle.area.adapter.rpc;

import com.particle.area.adapter.feign.client.rpc.AreaTransRpcFeignClient;
import com.particle.area.client.dto.data.AreaTransVO;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 区域翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 11:43:20
 */
@Tag(name = "区域翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/area")
public class AreaTransRpcController extends AbstractBaseRpcAdapter implements AreaTransRpcFeignClient {
	@Autowired
	private AreaTransServiceImpl areaTransService;

	@Override
	public boolean supportBatch(String type) {
		return areaTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<AreaTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return areaTransService.transBatch(type, keys);
	}
}