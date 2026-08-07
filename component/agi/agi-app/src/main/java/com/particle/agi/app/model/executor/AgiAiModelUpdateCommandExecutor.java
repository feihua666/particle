package com.particle.agi.app.model.executor;

import com.particle.agi.app.model.structmapping.AgiAiModelAppStructMapping;
import com.particle.agi.client.model.dto.command.AgiAiModelUpdateCommand;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;
import com.particle.agi.domain.model.AgiAiModel;
import com.particle.agi.domain.model.AgiAiModelId;
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
 * AI模型 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiAiModelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAiModelGateway agiAiModelGateway;

	/**
	 * 执行 AI模型 更新指令
	 * @param agiAiModelUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAiModelVO> execute(@Valid AgiAiModelUpdateCommand agiAiModelUpdateCommand) {
		AgiAiModel agiAiModel = createByAgiAiModelUpdateCommand(agiAiModelUpdateCommand);
		agiAiModel.setUpdateControl(agiAiModelUpdateCommand);
		boolean save = agiAiModelGateway.save(agiAiModel);
		if (save) {
			return SingleResponse.of(AgiAiModelAppStructMapping.instance.toAgiAiModelVO(agiAiModel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据AI模型更新指令创建AI模型模型
	 * @param agiAiModelUpdateCommand
	 * @return
	 */
	private AgiAiModel createByAgiAiModelUpdateCommand(AgiAiModelUpdateCommand agiAiModelUpdateCommand){
		AgiAiModel agiAiModel = AgiAiModel.create();
		AgiAiModelUpdateCommandToAgiAiModelMapping.instance.fillAgiAiModelByAgiAiModelUpdateCommand(agiAiModel, agiAiModelUpdateCommand);
		return agiAiModel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiAiModelUpdateCommandToAgiAiModelMapping{
		AgiAiModelUpdateCommandToAgiAiModelMapping instance = Mappers.getMapper(AgiAiModelUpdateCommandToAgiAiModelMapping.class );

		default AgiAiModelId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiAiModelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAiModel
		 * @param agiAiModelUpdateCommand
		 */
		void fillAgiAiModelByAgiAiModelUpdateCommand(@MappingTarget AgiAiModel agiAiModel, AgiAiModelUpdateCommand agiAiModelUpdateCommand);
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
