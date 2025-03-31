package com.particle.agi.adapter.agent.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.agi.client.agent.api.IAgiAgentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Tag(name = "智能体移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/agi_agent")
public class AgiAgentFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAgiAgentApplicationService iAgiAgentApplicationService;


}