package com.particle.test.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.test.client.api.ITestApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "测试wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/test")
public class TestFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITestApplicationService iTestApplicationService;









}