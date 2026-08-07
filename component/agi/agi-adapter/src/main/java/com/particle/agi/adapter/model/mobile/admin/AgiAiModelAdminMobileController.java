package com.particle.agi.adapter.model.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.agi.client.model.api.IAgiAiModelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * AI模型后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Tag(name = "AI模型移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/agi_ai_model")
public class AgiAiModelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAgiAiModelApplicationService iAgiAiModelApplicationService;


}
