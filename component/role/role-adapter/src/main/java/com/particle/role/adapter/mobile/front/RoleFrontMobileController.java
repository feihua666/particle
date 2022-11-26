package com.particle.role.adapter.mobile.front;

import com.particle.role.client.api.IRoleApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/role")
public class RoleFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IRoleApplicationService iRoleApplicationService;









}