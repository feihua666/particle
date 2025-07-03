package com.particle.cms.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.cms.client.api.ICmsContentViewRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容访问记录前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Tag(name = "内容访问记录pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/cms_content_view_record")
public class CmsContentViewRecordFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICmsContentViewRecordApplicationService iCmsContentViewRecordApplicationService;


}