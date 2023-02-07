package com.particle.lowcode.adapter.generator.mobile.admin;

import com.particle.lowcode.client.generator.api.ILowcodeSegmentTemplateApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码片段模板后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Api(tags = "低代码片段模板移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/lowcode-segment-template")
public class LowcodeSegmentTemplateAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ILowcodeSegmentTemplateApplicationService iLowcodeSegmentTemplateApplicationService;








}