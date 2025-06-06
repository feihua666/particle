package com.particle.lowcode.adapter.generator.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.lowcode.client.generator.api.ILowcodeModelItemApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码模型项目前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Tag(name = "低代码模型项目pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/lowcode-model-item")
public class LowcodeModelItemFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeModelItemApplicationService iLowcodeModelItemApplicationService;









}
