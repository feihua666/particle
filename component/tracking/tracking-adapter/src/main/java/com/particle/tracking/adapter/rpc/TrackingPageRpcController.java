package com.particle.tracking.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.tracking.adapter.feign.client.rpc.TrackingPageRpcFeignClient;
import com.particle.tracking.client.api.ITrackingPageApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 埋点页面远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Tag(name = "埋点页面远程调用相关接口")
@RestController
@RequestMapping("/rpc/tracking_page")
public class TrackingPageRpcController extends AbstractBaseRpcAdapter implements TrackingPageRpcFeignClient  {

	@Autowired
	private ITrackingPageApplicationService iTrackingPageApplicationService;


}
