package com.particle.componentadmin.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.componentadmin.client.api.IAdminComponentDependencyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 组件依赖关系后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Tag(name = "组件依赖关系移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/admin_component_dependency")
public class AdminComponentDependencyAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAdminComponentDependencyApplicationService iAdminComponentDependencyApplicationService;


}