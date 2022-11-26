package com.particle.role.adapter.roleuserrel.wap.admin;

import com.particle.role.client.roleuserrel.api.IRoleUserRelApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色用户关系后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色用户关系wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/role-user-rel")
public class RoleUserRelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IRoleUserRelApplicationService iRoleUserRelApplicationService;









}