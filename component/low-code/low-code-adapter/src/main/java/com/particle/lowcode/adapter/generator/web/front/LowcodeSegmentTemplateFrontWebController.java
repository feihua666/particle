package com.particle.lowcode.adapter.generator.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.lowcode.client.generator.api.ILowcodeSegmentTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码片段模板前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Tag(name = "低代码片段模板pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/lowcode-segment-template")
public class LowcodeSegmentTemplateFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeSegmentTemplateApplicationService iLowcodeSegmentTemplateApplicationService;









}
