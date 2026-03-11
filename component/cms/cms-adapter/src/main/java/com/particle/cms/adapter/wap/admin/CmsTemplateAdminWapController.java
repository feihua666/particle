package com.particle.cms.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.cms.client.api.ICmsTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 模板后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Tag(name = "模板wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/cms_template")
public class CmsTemplateAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICmsTemplateApplicationService iCmsTemplateApplicationService;


}