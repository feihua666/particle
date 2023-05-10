package com.particle.tracking.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.tracking.client.api.ITrackingPageRecordApplicationService;
import com.particle.tracking.adapter.feign.client.rpc.TrackingPageRecordRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 页面埋点记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Api(tags = "页面埋点记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/tracking_page_record")
public class TrackingPageRecordRpcController extends AbstractBaseRpcAdapter implements TrackingPageRecordRpcFeignClient  {

	@Autowired
	private ITrackingPageRecordApplicationService iTrackingPageRecordApplicationService;


}