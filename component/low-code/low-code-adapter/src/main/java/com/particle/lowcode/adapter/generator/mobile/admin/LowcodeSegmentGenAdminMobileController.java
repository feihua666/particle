package com.particle.lowcode.adapter.generator.mobile.admin;

import com.particle.lowcode.client.generator.api.ILowcodeSegmentGenApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码生成后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Api(tags = "低代码生成移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/lowcode-segment-gen")
public class LowcodeSegmentGenAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ILowcodeSegmentGenApplicationService iLowcodeSegmentGenApplicationService;








}