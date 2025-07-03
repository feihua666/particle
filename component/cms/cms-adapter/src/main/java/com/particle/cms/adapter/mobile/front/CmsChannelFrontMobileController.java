package com.particle.cms.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.cms.client.api.ICmsChannelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 栏目前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Tag(name = "栏目移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/cms_channel")
public class CmsChannelFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICmsChannelApplicationService iCmsChannelApplicationService;


}