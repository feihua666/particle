package com.particle.cms.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.cms.client.api.ICmsContentMultimediaApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容多媒体前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Tag(name = "内容多媒体pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/cms_content_multimedia")
public class CmsContentMultimediaFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICmsContentMultimediaApplicationService iCmsContentMultimediaApplicationService;


}