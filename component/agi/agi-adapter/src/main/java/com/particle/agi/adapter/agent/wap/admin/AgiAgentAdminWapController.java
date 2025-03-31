package com.particle.agi.adapter.agent.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.agi.client.agent.api.IAgiAgentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Tag(name = "智能体wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/agi_agent")
public class AgiAgentAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAgiAgentApplicationService iAgiAgentApplicationService;


}