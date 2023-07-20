package com.particle.tracking.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tracking.client.api.ITrackingPageApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 埋点页面后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Tag(name = "埋点页面wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/tracking_page")
public class TrackingPageAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITrackingPageApplicationService iTrackingPageApplicationService;


}