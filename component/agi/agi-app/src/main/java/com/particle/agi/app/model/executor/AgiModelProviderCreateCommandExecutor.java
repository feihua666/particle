package com.particle.agi.app.model.executor;

import com.particle.agi.app.model.structmapping.AgiModelProviderAppStructMapping;
import com.particle.agi.client.model.dto.command.AgiModelProviderCreateCommand;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;
import com.particle.agi.domain.model.AgiModelProvider;
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
 * AI模型提供商 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Component
@Validated
public class AgiModelProviderCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiModelProviderGateway agiModelProviderGateway;

	/**
	 * 执行AI模型提供商添加指令
	 * @param agiModelProviderCreateCommand
	 * @return
	 */
	public SingleResponse<AgiModelProviderVO> execute(@Valid AgiModelProviderCreateCommand agiModelProviderCreateCommand) {
		AgiModelProvider agiModelProvider = createByAgiModelProviderCreateCommand(agiModelProviderCreateCommand);
		agiModelProvider.setAddControl(agiModelProviderCreateCommand);
		boolean save = agiModelProviderGateway.save(agiModelProvider);
		if (save) {
			return SingleResponse.of(AgiModelProviderAppStructMapping.instance.toAgiModelProviderVO(agiModelProvider));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据AI模型提供商创建指令创建AI模型提供商模型
	 * @param agiModelProviderCreateCommand
	 * @return
	 */
	private AgiModelProvider createByAgiModelProviderCreateCommand(AgiModelProviderCreateCommand agiModelProviderCreateCommand){
		AgiModelProvider agiModelProvider = AgiModelProvider.create();
		AgiModelProviderCreateCommandToAgiModelProviderMapping.instance.fillAgiModelProviderByAgiModelProviderCreateCommand(agiModelProvider, agiModelProviderCreateCommand);
		return agiModelProvider;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiModelProviderCreateCommandToAgiModelProviderMapping{
		AgiModelProviderCreateCommandToAgiModelProviderMapping instance = Mappers.getMapper( AgiModelProviderCreateCommandToAgiModelProviderMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiModelProvider
		 * @param agiModelProviderCreateCommand
		 */
		void fillAgiModelProviderByAgiModelProviderCreateCommand(@MappingTarget AgiModelProvider agiModelProvider, AgiModelProviderCreateCommand agiModelProviderCreateCommand);
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
