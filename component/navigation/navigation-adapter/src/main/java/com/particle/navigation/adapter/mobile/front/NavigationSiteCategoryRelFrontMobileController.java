package com.particle.navigation.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.navigation.client.api.INavigationSiteCategoryRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航网站分类关系前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Tag(name = "导航网站分类关系移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/navigation_site_category_rel")
public class NavigationSiteCategoryRelFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private INavigationSiteCategoryRelApplicationService iNavigationSiteCategoryRelApplicationService;


}