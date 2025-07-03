package com.particle.cms.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.cms.client.api.ICmsChannelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 栏目后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Tag(name = "栏目wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/cms_channel")
public class CmsChannelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICmsChannelApplicationService iCmsChannelApplicationService;


}