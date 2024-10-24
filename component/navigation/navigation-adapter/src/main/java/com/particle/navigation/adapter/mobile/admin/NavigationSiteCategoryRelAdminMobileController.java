package com.particle.navigation.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.navigation.client.api.INavigationSiteCategoryRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航网站分类关系后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Tag(name = "导航网站分类关系移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/navigation_site_category_rel")
public class NavigationSiteCategoryRelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private INavigationSiteCategoryRelApplicationService iNavigationSiteCategoryRelApplicationService;


}