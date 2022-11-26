package com.particle.role.adapter.rolefuncrel.mobile.front;

import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色菜单功能关系前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色菜单功能关系移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/role-func-rel")
public class RoleFuncRelFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IRoleFuncRelApplicationService iRoleFuncRelApplicationService;









}