package com.particle.lowcode.adapter.generator.mobile.front;

import com.particle.lowcode.client.generator.api.ILowcodeSegmentGenApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码生成前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Api(tags = "低代码生成移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/lowcode-segment-gen")
public class LowcodeSegmentGenFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ILowcodeSegmentGenApplicationService iLowcodeSegmentGenApplicationService;









}