package com.particle.area.adapter.rpc;

import com.particle.area.adapter.feign.client.rpc.AreaRpcFeignClient;
import com.particle.area.adapter.feign.client.rpc.AreaTransRpcFeignClient;
import com.particle.area.client.api.IAreaApplicationService;
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
 * 区域远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Tag(name = "区域远程调用相关接口")
@RestController
@RequestMapping("/rpc/area")
public class AreaRpcController extends AbstractBaseRpcAdapter implements AreaRpcFeignClient, AreaTransRpcFeignClient {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;

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