package com.particle.componentadmin.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.componentadmin.client.api.IAdminComponentDependencyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 组件依赖关系后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Tag(name = "组件依赖关系wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/admin_component_dependency")
public class AdminComponentDependencyAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAdminComponentDependencyApplicationService iAdminComponentDependencyApplicationService;


}