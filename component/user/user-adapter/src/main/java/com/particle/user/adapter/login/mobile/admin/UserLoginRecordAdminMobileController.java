package com.particle.user.adapter.login.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.user.client.login.api.IUserLoginRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录记录后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Tag(name = "用户登录记录移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/user-login-record")
public class UserLoginRecordAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUserLoginRecordApplicationService iUserLoginRecordApplicationService;








}
