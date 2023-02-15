package com.particle.lowcode.adapter.generator.wap.front;

import com.particle.lowcode.client.generator.api.ILowcodeSegmentGenApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码生成前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Api(tags = "低代码生成wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/lowcode-segment-gen")
public class LowcodeSegmentGenFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ILowcodeSegmentGenApplicationService iLowcodeSegmentGenApplicationService;









}