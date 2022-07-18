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
 * 简单用户后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "简单用户移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/user-simple")
public class UserSimpleAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUserSimpleApplicationService iUserSimpleApplicationService;








}