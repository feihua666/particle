package com.particle.agi.app.model.executor;

import com.particle.agi.app.model.structmapping.AgiModelProviderAppStructMapping;
import com.particle.agi.client.model.dto.command.AgiModelProviderUpdateCommand;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;
import com.particle.agi.domain.model.AgiModelProvider;
import com.particle.agi.domain.model.AgiModelProviderId;
import com.particle.agi.domain.model.gateway.AgiModelProviderGateway;
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
 * AI模型提供商 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiModelProviderUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiModelProviderGateway agiModelProviderGateway;

	/**
	 * 执行 AI模型提供商 更新指令
	 * @param agiModelProviderUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiModelProviderVO> execute(@Valid AgiModelProviderUpdateCommand agiModelProviderUpdateCommand) {
		AgiModelProvider agiModelProvider = createByAgiModelProviderUpdateCommand(agiModelProviderUpdateCommand);
		agiModelProvider.setUpdateControl(agiModelProviderUpdateCommand);
		boolean save = agiModelProviderGateway.save(agiModelProvider);
		if (save) {
			return SingleResponse.of(AgiModelProviderAppStructMapping.instance.toAgiModelProviderVO(agiModelProvider));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据AI模型提供商更新指令创建AI模型提供商模型
	 * @param agiModelProviderUpdateCommand
	 * @return
	 */
	private AgiModelProvider createByAgiModelProviderUpdateCommand(AgiModelProviderUpdateCommand agiModelProviderUpdateCommand){
		AgiModelProvider agiModelProvider = AgiModelProvider.create();
		AgiModelProviderUpdateCommandToAgiModelProviderMapping.instance.fillAgiModelProviderByAgiModelProviderUpdateCommand(agiModelProvider, agiModelProviderUpdateCommand);
		return agiModelProvider;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiModelProviderUpdateCommandToAgiModelProviderMapping{
		AgiModelProviderUpdateCommandToAgiModelProviderMapping instance = Mappers.getMapper(AgiModelProviderUpdateCommandToAgiModelProviderMapping.class );

		default AgiModelProviderId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiModelProviderId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiModelProvider
		 * @param agiModelProviderUpdateCommand
		 */
		void fillAgiModelProviderByAgiModelProviderUpdateCommand(@MappingTarget AgiModelProvider agiModelProvider, AgiModelProviderUpdateCommand agiModelProviderUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiModelProviderGateway
	 */
	@Autowired
	public void setAgiModelProviderGateway(AgiModelProviderGateway agiModelProviderGateway) {
		this.agiModelProviderGateway = agiModelProviderGateway;
	}
}
