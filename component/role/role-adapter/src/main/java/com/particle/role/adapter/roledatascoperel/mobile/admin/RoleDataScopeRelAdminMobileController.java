package com.particle.role.adapter.roledatascoperel.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.role.client.roledatascoperel.api.IRoleDataScopeRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色数据范围关系后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Tag(name = "角色数据范围关系移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/role_data_scope_rel")
public class RoleDataScopeRelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IRoleDataScopeRelApplicationService iRoleDataScopeRelApplicationService;


}