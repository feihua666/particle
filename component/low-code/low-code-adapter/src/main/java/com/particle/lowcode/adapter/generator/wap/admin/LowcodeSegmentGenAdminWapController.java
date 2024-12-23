package com.particle.lowcode.adapter.generator.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.lowcode.client.generator.api.ILowcodeSegmentGenApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码生成后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Tag(name = "低代码生成wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/lowcode-segment-gen")
public class LowcodeSegmentGenAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ILowcodeSegmentGenApplicationService iLowcodeSegmentGenApplicationService;









}
