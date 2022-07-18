package com.particle.test.adapter.mobile;

import com.particle.test.client.api.IUserSimpleApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 简单用户前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "简单用户移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/user-simple")
public class UserSimpleFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUserSimpleApplicationService iUserSimpleApplicationService;









}