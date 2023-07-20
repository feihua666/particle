package com.particle.lowcode.adapter.generator.mobile.front;

import com.particle.lowcode.client.generator.api.ILowcodeModelItemApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码模型项目前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Tag(name = "低代码模型项目移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/lowcode-model-item")
public class LowcodeModelItemFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ILowcodeModelItemApplicationService iLowcodeModelItemApplicationService;









}