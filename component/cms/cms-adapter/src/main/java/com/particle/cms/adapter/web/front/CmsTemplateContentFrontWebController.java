package com.particle.cms.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.cms.client.api.ICmsTemplateContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 模板内容前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Tag(name = "模板内容pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/cms_template_content")
public class CmsTemplateContentFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICmsTemplateContentApplicationService iCmsTemplateContentApplicationService;


}