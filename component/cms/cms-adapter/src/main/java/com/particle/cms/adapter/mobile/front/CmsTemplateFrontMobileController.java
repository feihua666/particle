package com.particle.cms.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.cms.client.api.ICmsTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 模板前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Tag(name = "模板移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/cms_template")
public class CmsTemplateFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICmsTemplateApplicationService iCmsTemplateApplicationService;


}