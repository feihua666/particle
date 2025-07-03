package com.particle.cms.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.cms.client.api.ICmsContentMultimediaApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容多媒体后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Tag(name = "内容多媒体wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/cms_content_multimedia")
public class CmsContentMultimediaAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICmsContentMultimediaApplicationService iCmsContentMultimediaApplicationService;


}