package com.particle.agi.adapter.model.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.agi.client.model.api.IAgiModelProviderApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * AI模型提供商前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Tag(name = "AI模型提供商移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/agi_model_provider")
public class AgiModelProviderFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAgiModelProviderApplicationService iAgiModelProviderApplicationService;


}
