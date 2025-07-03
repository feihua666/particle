package com.particle.cms.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.cms.client.api.ICmsChannelViewRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 栏目访问记录后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Tag(name = "栏目访问记录wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/cms_channel_view_record")
public class CmsChannelViewRecordAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICmsChannelViewRecordApplicationService iCmsChannelViewRecordApplicationService;


}