package com.particle.lowcode.adapter.generator.mobile.front;

import com.particle.lowcode.client.generator.api.ILowcodeModelApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码模型前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Api(tags = "低代码模型移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/lowcode-model")
public class LowcodeModelFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ILowcodeModelApplicationService iLowcodeModelApplicationService;









}