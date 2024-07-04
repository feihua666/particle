package com.particle.role.adapter.roledatascoperel.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.role.client.roledatascoperel.api.IRoleDataScopeRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色数据范围关系后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Tag(name = "角色数据范围关系wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/role_data_scope_rel")
public class RoleDataScopeRelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IRoleDataScopeRelApplicationService iRoleDataScopeRelApplicationService;


}