package com.particle.area.adapter.wap.front;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Tag(name = "区域wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/area")
public class AreaFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;









}