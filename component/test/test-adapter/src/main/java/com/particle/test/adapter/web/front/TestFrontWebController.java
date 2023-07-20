package com.particle.test.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.test.client.api.ITestApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Tag(name = "测试pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/test")
public class TestFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITestApplicationService iTestApplicationService;









}