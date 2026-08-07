package com.particle.agi.app.model.executor;

import com.particle.agi.domain.model.gateway.AgiModelProviderGateway;
import com.particle.agi.infrastructure.model.service.IAgiModelProviderService;
import com.particle.agi.infrastructure.model.dos.AgiModelProviderDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * AI模型提供商 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Component
@Validated
public class AgiModelProviderCommandExecutor  extends AbstractBaseExecutor {

	private AgiModelProviderGateway agiModelProviderGateway;
	private IAgiModelProviderService iAgiModelProviderService;
	/**
	 * 注入使用set方法
	 * @param agiModelProviderGateway
	 */
	@Autowired
	public void setAgiModelProviderGateway(AgiModelProviderGateway agiModelProviderGateway) {
		this.agiModelProviderGateway = agiModelProviderGateway;
	}
	@Autowired
	public void setIAgiModelProviderService(IAgiModelProviderService iAgiModelProviderService) {
		this.iAgiModelProviderService = iAgiModelProviderService;
	}
}
