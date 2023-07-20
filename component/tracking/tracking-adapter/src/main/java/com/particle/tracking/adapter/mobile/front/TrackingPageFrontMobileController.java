package com.particle.tracking.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.tracking.client.api.ITrackingPageApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 埋点页面前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Tag(name = "埋点页面移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/tracking_page")
public class TrackingPageFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITrackingPageApplicationService iTrackingPageApplicationService;


}