package com.particle.role.adapter.roleuserrel.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.role.client.roleuserrel.api.IRoleUserRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色用户关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色用户关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/role-user-rel")
public class RoleUserRelFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IRoleUserRelApplicationService iRoleUserRelApplicationService;









}
