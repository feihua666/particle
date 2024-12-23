package com.particle.user.adapter.login.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.user.client.login.api.IUserLoginRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录记录前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Tag(name = "用户登录记录wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/user-login-record")
public class UserLoginRecordFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserLoginRecordApplicationService iUserLoginRecordApplicationService;









}
