package com.particle.agi.app.model.executor;

import com.particle.agi.domain.model.gateway.AgiAiModelGateway;
import com.particle.agi.infrastructure.model.service.IAgiAiModelService;
import com.particle.agi.infrastructure.model.dos.AgiAiModelDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * AI模型 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Component
@Validated
public class AgiAiModelCommandExecutor  extends AbstractBaseExecutor {

	private AgiAiModelGateway agiAiModelGateway;
	private IAgiAiModelService iAgiAiModelService;
	/**
	 * 注入使用set方法
	 * @param agiAiModelGateway
	 */
	@Autowired
	public void setAgiAiModelGateway(AgiAiModelGateway agiAiModelGateway) {
		this.agiAiModelGateway = agiAiModelGateway;
	}
	@Autowired
	public void setIAgiAiModelService(IAgiAiModelService iAgiAiModelService) {
		this.iAgiAiModelService = iAgiAiModelService;
	}
}
