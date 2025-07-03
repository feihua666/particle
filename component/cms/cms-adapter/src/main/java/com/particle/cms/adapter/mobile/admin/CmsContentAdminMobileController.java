package com.particle.cms.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.cms.client.api.ICmsContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Tag(name = "内容移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/cms_content")
public class CmsContentAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICmsContentApplicationService iCmsContentApplicationService;


}