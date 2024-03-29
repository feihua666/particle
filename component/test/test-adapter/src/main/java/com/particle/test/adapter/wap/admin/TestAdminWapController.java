package com.particle.test.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.test.client.api.ITestApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Tag(name = "测试wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/test")
public class TestAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITestApplicationService iTestApplicationService;









}