package com.particle.cms.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.cms.client.api.ICmsTemplateContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 模板内容前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Tag(name = "模板内容移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/cms_template_content")
public class CmsTemplateContentFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICmsTemplateContentApplicationService iCmsTemplateContentApplicationService;


}