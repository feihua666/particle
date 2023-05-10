package com.particle.tracking.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tracking.client.api.ITrackingPageApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 埋点页面前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Api(tags = "埋点页面wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/tracking_page")
public class TrackingPageFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITrackingPageApplicationService iTrackingPageApplicationService;


}