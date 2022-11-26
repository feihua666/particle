package com.particle.role.adapter.rolefuncrel.web.front;

import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色菜单功能关系前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色菜单功能关系pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/role-func-rel")
public class RoleFuncRelFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleFuncRelApplicationService iRoleFuncRelApplicationService;









}