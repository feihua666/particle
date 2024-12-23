package com.particle.user.adapter.login.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.user.client.login.api.IUserLoginDeviceApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录设备后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Tag(name = "用户登录设备wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/user-login-device")
public class UserLoginDeviceAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserLoginDeviceApplicationService iUserLoginDeviceApplicationService;









}
