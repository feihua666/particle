package com.particle.test.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.test.client.api.ITestApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "测试移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/test")
public class TestAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITestApplicationService iTestApplicationService;








}