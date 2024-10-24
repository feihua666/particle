package com.particle.navigation.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.navigation.client.api.INavigationCategoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航分类前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Tag(name = "导航分类wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/navigation_category")
public class NavigationCategoryFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private INavigationCategoryApplicationService iNavigationCategoryApplicationService;


}