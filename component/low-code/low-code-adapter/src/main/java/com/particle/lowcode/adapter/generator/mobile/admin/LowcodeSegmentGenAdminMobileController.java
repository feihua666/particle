package com.particle.lowcode.adapter.generator.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.lowcode.client.generator.api.ILowcodeSegmentGenApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "低代码生成移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/lowcode-segment-gen")
public class LowcodeSegmentGenAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ILowcodeSegmentGenApplicationService iLowcodeSegmentGenApplicationService;








}
