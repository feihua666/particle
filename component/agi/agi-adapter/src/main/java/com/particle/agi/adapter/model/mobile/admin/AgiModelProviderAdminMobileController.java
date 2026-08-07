package com.particle.agi.adapter.model.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.agi.client.model.api.IAgiModelProviderApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * AI模型提供商后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Tag(name = "AI模型提供商移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/agi_model_provider")
public class AgiModelProviderAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAgiModelProviderApplicationService iAgiModelProviderApplicationService;


}
