package com.particle.agi.app.model.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.model.structmapping.AgiModelProviderAppStructMapping;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;
import com.particle.agi.domain.model.AgiModelProvider;
import com.particle.agi.domain.model.AgiModelProviderId;
import com.particle.agi.domain.model.gateway.AgiModelProviderGateway;
import com.particle.agi.infrastructure.model.service.IAgiModelProviderService;
import com.particle.agi.infrastructure.model.dos.AgiModelProviderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * AI模型提供商 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Component
@Validated
public class AgiModelProviderDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiModelProviderGateway agiModelProviderGateway;
	private IAgiModelProviderService iAgiModelProviderService;

	/**
	 * 执行 AI模型提供商 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiModelProviderVO> execute(@Valid CommonIdCommand deleteCommand) {
		AgiModelProviderId agiModelProviderId = AgiModelProviderId.of(deleteCommand.getId());
		AgiModelProvider byId = agiModelProviderGateway.getById(agiModelProviderId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiModelProviderGateway.delete(agiModelProviderId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AgiModelProviderAppStructMapping.instance.toAgiModelProviderVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
