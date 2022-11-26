package com.particle.user.adapter.login.mobile.front;

import com.particle.user.client.login.api.IUserLoginRecordApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Api(tags = "用户登录记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/user-login-record")
public class UserLoginRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUserLoginRecordApplicationService iUserLoginRecordApplicationService;









}