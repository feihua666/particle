package com.particle.cms.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.cms.client.api.ICmsChannelViewRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 栏目访问记录后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Tag(name = "栏目访问记录移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/cms_channel_view_record")
public class CmsChannelViewRecordAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICmsChannelViewRecordApplicationService iCmsChannelViewRecordApplicationService;


}