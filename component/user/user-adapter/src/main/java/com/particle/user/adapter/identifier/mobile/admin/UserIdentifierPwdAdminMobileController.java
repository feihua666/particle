package com.particle.user.adapter.identifier.mobile.admin;

import com.particle.user.client.identifier.api.IUserIdentifierPwdApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户密码后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "用户密码移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/user-identifier-pwd")
public class UserIdentifierPwdAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUserIdentifierPwdApplicationService iUserIdentifierPwdApplicationService;








}