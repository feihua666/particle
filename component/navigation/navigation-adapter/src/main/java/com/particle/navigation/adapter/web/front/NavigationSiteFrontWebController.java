package com.particle.navigation.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.navigation.client.api.INavigationSiteApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航网站前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Tag(name = "导航网站pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/navigation_site")
public class NavigationSiteFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private INavigationSiteApplicationService iNavigationSiteApplicationService;


}