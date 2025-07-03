package com.particle.cms.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.cms.client.api.ICmsTemplateContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 模板内容后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Tag(name = "模板内容wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/cms_template_content")
public class CmsTemplateContentAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICmsTemplateContentApplicationService iCmsTemplateContentApplicationService;


}