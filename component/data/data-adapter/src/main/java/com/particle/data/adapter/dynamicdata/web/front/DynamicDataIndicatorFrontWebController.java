package com.particle.data.adapter.dynamicdata.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据指标前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Tag(name = "动态数据指标pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dynamic_data_indicator")
public class DynamicDataIndicatorFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDynamicDataIndicatorApplicationService iDynamicDataIndicatorApplicationService;


}