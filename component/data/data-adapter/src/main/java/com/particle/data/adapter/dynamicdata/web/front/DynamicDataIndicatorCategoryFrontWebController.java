package com.particle.data.adapter.dynamicdata.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据指标分类前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Tag(name = "动态数据指标分类pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dynamic_data_indicator_category")
public class DynamicDataIndicatorCategoryFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDynamicDataIndicatorCategoryApplicationService iDynamicDataIndicatorCategoryApplicationService;


}