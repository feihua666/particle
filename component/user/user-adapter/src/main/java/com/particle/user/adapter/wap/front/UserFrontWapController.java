package com.particle.user.adapter.wap.front;

import com.particle.user.client.api.IUserApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "用户wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/user")
public class UserFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserApplicationService iUserApplicationService;









}