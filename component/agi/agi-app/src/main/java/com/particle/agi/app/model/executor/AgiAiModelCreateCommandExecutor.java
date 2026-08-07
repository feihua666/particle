package com.particle.agi.app.model.executor;

import com.particle.agi.app.model.structmapping.AgiAiModelAppStructMapping;
import com.particle.agi.client.model.dto.command.AgiAiModelCreateCommand;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;
import com.particle.agi.domain.model.AgiAiModel;
import com.particle.agi.domain.model.gateway.AgiAiModelGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class AgiAiModelCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAiModelGateway agiAiModelGateway;

	/**
	 * 执行AI模型添加指令
	 * @param agiAiModelCreateCommand
	 * @return
	 */
	public SingleResponse<AgiAiModelVO> execute(@Valid AgiAiModelCreateCommand agiAiModelCreateCommand) {
		AgiAiModel agiAiModel = createByAgiAiModelCreateCommand(agiAiModelCreateCommand);
		agiAiModel.setAddControl(agiAiModelCreateCommand);
		boolean save = agiAiModelGateway.save(agiAiModel);
		if (save) {
			return SingleResponse.of(AgiAiModelAppStructMapping.instance.toAgiAiModelVO(agiAiModel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据AI模型创建指令创建AI模型模型
	 * @param agiAiModelCreateCommand
	 * @return
	 */
	private AgiAiModel createByAgiAiModelCreateCommand(AgiAiModelCreateCommand agiAiModelCreateCommand){
		AgiAiModel agiAiModel = AgiAiModel.create();
		AgiAiModelCreateCommandToAgiAiModelMapping.instance.fillAgiAiModelByAgiAiModelCreateCommand(agiAiModel, agiAiModelCreateCommand);
		return agiAiModel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiAiModelCreateCommandToAgiAiModelMapping{
		AgiAiModelCreateCommandToAgiAiModelMapping instance = Mappers.getMapper( AgiAiModelCreateCommandToAgiAiModelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAiModel
		 * @param agiAiModelCreateCommand
		 */
		void fillAgiAiModelByAgiAiModelCreateCommand(@MappingTarget AgiAiModel agiAiModel, AgiAiModelCreateCommand agiAiModelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiAiModelGateway
	 */
	@Autowired
	public void setAgiAiModelGateway(AgiAiModelGateway agiAiModelGateway) {
		this.agiAiModelGateway = agiAiModelGateway;
	}
}
