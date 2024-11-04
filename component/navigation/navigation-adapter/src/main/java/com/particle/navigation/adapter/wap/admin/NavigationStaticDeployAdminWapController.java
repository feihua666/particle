package com.particle.navigation.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.navigation.client.api.INavigationStaticDeployApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航网站静态部署后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Tag(name = "导航网站静态部署wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/navigation_static_deploy")
public class NavigationStaticDeployAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private INavigationStaticDeployApplicationService iNavigationStaticDeployApplicationService;


}