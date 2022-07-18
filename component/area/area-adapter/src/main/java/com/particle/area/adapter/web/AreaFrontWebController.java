package com.particle.area.adapter.web;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Api(tags = "区域pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/area")
public class AreaFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAreaApplicationService areaApplicationService;

}