package com.particle.navigation.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.navigation.client.api.INavigationSiteTagRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航网站标签关系后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Tag(name = "导航网站标签关系wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/navigation_site_tag_rel")
public class NavigationSiteTagRelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private INavigationSiteTagRelApplicationService iNavigationSiteTagRelApplicationService;


}