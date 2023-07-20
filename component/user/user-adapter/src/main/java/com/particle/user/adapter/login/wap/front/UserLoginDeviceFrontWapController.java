package com.particle.user.adapter.login.wap.front;

import com.particle.user.client.login.api.IUserLoginDeviceApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录设备前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Tag(name = "用户登录设备wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/user-login-device")
public class UserLoginDeviceFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserLoginDeviceApplicationService iUserLoginDeviceApplicationService;









}