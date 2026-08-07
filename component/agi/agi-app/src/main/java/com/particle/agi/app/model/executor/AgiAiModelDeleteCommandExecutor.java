package com.particle.agi.app.model.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.model.structmapping.AgiAiModelAppStructMapping;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;
import com.particle.agi.domain.model.AgiAiModel;
import com.particle.agi.domain.model.AgiAiModelId;
import com.particle.agi.domain.model.gateway.AgiAiModelGateway;
import com.particle.agi.infrastructure.model.service.IAgiAiModelService;
import com.particle.agi.infrastructure.model.dos.AgiAiModelDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * AI模型 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Component
@Validated
public class AgiAiModelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiAiModelGateway agiAiModelGateway;
	private IAgiAiModelService iAgiAiModelService;

	/**
	 * 执行 AI模型 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiAiModelVO> execute(@Valid CommonIdCommand deleteCommand) {
		AgiAiModelId agiAiModelId = AgiAiModelId.of(deleteCommand.getId());
		AgiAiModel byId = agiAiModelGateway.getById(agiAiModelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiAiModelGateway.delete(agiAiModelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AgiAiModelAppStructMapping.instance.toAgiAiModelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
