package com.particle.area.adapter.mobile;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Api(tags = "区域移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/area")
public class AreaFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;









}