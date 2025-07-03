package com.particle.cms.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.cms.client.api.ICmsChannelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 栏目后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Tag(name = "栏目移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/cms_channel")
public class CmsChannelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICmsChannelApplicationService iCmsChannelApplicationService;


}