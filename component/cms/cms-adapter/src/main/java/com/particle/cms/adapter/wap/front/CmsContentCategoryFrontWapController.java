package com.particle.cms.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.cms.client.api.ICmsContentCategoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容分类前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Tag(name = "内容分类wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/cms_content_category")
public class CmsContentCategoryFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICmsContentCategoryApplicationService iCmsContentCategoryApplicationService;


}