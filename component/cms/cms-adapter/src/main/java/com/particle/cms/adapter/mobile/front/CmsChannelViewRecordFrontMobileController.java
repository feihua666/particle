package com.particle.cms.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.cms.client.api.ICmsChannelViewRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 栏目访问记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Tag(name = "栏目访问记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/cms_channel_view_record")
public class CmsChannelViewRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICmsChannelViewRecordApplicationService iCmsChannelViewRecordApplicationService;


}