package com.particle.user.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.user.client.api.IUserApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台管理用户后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "后台管理用户wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/user")
public class UserAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserApplicationService iUserApplicationService;









}