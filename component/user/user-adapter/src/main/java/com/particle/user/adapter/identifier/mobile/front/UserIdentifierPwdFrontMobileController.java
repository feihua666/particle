package com.particle.user.adapter.identifier.mobile.front;

import com.particle.user.client.identifier.api.IUserIdentifierPwdApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户密码前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "用户密码移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/user-identifier-pwd")
public class UserIdentifierPwdFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUserIdentifierPwdApplicationService iUserIdentifierPwdApplicationService;









}