package com.particle.role.adapter.web.front;

import com.particle.role.client.api.IRoleApplicationService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/role")
public class RoleFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleApplicationService iRoleApplicationService;









}