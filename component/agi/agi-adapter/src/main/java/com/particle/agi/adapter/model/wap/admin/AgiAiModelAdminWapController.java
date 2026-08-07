package com.particle.agi.adapter.model.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.agi.client.model.api.IAgiAiModelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * AI模型后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Tag(name = "AI模型wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/agi_ai_model")
public class AgiAiModelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAgiAiModelApplicationService iAgiAiModelApplicationService;


}
