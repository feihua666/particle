package com.particle.role.adapter.wap.admin;

import com.particle.role.client.api.IRoleApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/role")
public class RoleAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IRoleApplicationService iRoleApplicationService;









}