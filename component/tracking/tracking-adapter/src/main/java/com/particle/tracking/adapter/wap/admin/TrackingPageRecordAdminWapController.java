package com.particle.tracking.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tracking.client.api.ITrackingPageRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 页面埋点记录后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Tag(name = "页面埋点记录wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/tracking_page_record")
public class TrackingPageRecordAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITrackingPageRecordApplicationService iTrackingPageRecordApplicationService;


}