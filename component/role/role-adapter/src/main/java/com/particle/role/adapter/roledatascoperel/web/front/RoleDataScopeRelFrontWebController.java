package com.particle.role.adapter.roledatascoperel.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.role.client.roledatascoperel.api.IRoleDataScopeRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色数据范围关系前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Tag(name = "角色数据范围关系pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/role_data_scope_rel")
public class RoleDataScopeRelFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleDataScopeRelApplicationService iRoleDataScopeRelApplicationService;


}