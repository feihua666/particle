package com.particle.user.adapter.wap;

import com.particle.user.client.api.IUserApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台管理用户前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "后台管理用户wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/user")
public class UserFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserApplicationService iUserApplicationService;









}