package com.particle.navigation.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.navigation.client.api.INavigationSiteTagApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航网站标签前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Tag(name = "导航网站标签wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/navigation_site_tag")
public class NavigationSiteTagFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private INavigationSiteTagApplicationService iNavigationSiteTagApplicationService;


}