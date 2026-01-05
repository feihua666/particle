package com.particle.componentadmin.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.componentadmin.client.api.IAdminComponentDependencyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 组件依赖关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Tag(name = "组件依赖关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/admin_component_dependency")
public class AdminComponentDependencyFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAdminComponentDependencyApplicationService iAdminComponentDependencyApplicationService;


}