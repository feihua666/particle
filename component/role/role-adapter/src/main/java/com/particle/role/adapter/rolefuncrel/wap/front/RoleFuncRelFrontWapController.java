package com.particle.role.adapter.rolefuncrel.wap.front;

import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色菜单功能关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色菜单功能关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/role-func-rel")
public class RoleFuncRelFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IRoleFuncRelApplicationService iRoleFuncRelApplicationService;









}