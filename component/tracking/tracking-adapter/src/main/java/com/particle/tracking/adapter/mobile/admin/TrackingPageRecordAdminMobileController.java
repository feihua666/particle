package com.particle.tracking.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.tracking.client.api.ITrackingPageRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 页面埋点记录后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Tag(name = "页面埋点记录移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/tracking_page_record")
public class TrackingPageRecordAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITrackingPageRecordApplicationService iTrackingPageRecordApplicationService;


}