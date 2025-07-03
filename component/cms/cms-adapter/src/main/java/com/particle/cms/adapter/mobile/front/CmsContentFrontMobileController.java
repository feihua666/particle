package com.particle.cms.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.cms.client.api.ICmsContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Tag(name = "内容移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/cms_content")
public class CmsContentFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICmsContentApplicationService iCmsContentApplicationService;


}