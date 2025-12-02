package com.particle.data.adapter.dynamicdata.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据指标分类前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Tag(name = "动态数据指标分类wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/dynamic_data_indicator_category")
public class DynamicDataIndicatorCategoryFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDynamicDataIndicatorCategoryApplicationService iDynamicDataIndicatorCategoryApplicationService;


}