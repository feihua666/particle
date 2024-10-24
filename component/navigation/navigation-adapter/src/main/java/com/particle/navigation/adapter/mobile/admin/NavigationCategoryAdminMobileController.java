package com.particle.navigation.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.navigation.client.api.INavigationCategoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航分类后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Tag(name = "导航分类移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/navigation_category")
public class NavigationCategoryAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private INavigationCategoryApplicationService iNavigationCategoryApplicationService;


}