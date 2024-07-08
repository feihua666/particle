package com.particle.area.adapter.rpc;

import com.particle.area.adapter.feign.client.rpc.AreaRpcFeignClient;
import com.particle.area.client.api.IAreaApplicationService;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AreaRpcController extends AbstractBaseRpcAdapter implements AreaRpcFeignClient {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;

}