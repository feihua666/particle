package com.particle.cms.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.cms.client.api.ICmsTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 模板前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Tag(name = "模板pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/cms_template")
public class CmsTemplateFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICmsTemplateApplicationService iCmsTemplateApplicationService;


}