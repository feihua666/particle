package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelItemAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelItemGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 低代码模型项目 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
@Validated
public class LowcodeModelItemCreateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeModelItemGateway lowcodeModelItemGateway;

	/**
	 * 执行低代码模型项目添加指令
	 * @param lowcodeModelItemCreateCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelItemVO> execute(@Valid LowcodeModelItemCreateCommand lowcodeModelItemCreateCommand) {
		LowcodeModelItem lowcodeModelItem = createByLowcodeModelItemCreateCommand(lowcodeModelItemCreateCommand);
		lowcodeModelItem.setAddControl(lowcodeModelItemCreateCommand);
		boolean save = lowcodeModelItemGateway.save(lowcodeModelItem);
		if (save) {
			return SingleResponse.of(LowcodeModelItemAppStructMapping.instance.toLowcodeModelItemVO(lowcodeModelItem));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据低代码模型项目创建指令创建低代码模型项目模型
	 * @param lowcodeModelItemCreateCommand
	 * @return
	 */
	private LowcodeModelItem createByLowcodeModelItemCreateCommand(LowcodeModelItemCreateCommand lowcodeModelItemCreateCommand){
		LowcodeModelItem lowcodeModelItem = LowcodeModelItem.create();
		LowcodeModelItemCreateCommandToLowcodeModelItemMapping.instance.fillLowcodeModelItemByLowcodeModelItemCreateCommand(lowcodeModelItem, lowcodeModelItemCreateCommand);
		return lowcodeModelItem;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  LowcodeModelItemCreateCommandToLowcodeModelItemMapping{
		LowcodeModelItemCreateCommandToLowcodeModelItemMapping instance = Mappers.getMapper( LowcodeModelItemCreateCommandToLowcodeModelItemMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeModelItem
		 * @param lowcodeModelItemCreateCommand
		 */
		void fillLowcodeModelItemByLowcodeModelItemCreateCommand(@MappingTarget LowcodeModelItem lowcodeModelItem, LowcodeModelItemCreateCommand lowcodeModelItemCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeModelItemGateway
	 */
	@Autowired
	public void setLowcodeModelItemGateway(LowcodeModelItemGateway lowcodeModelItemGateway) {
		this.lowcodeModelItemGateway = lowcodeModelItemGateway;
	}
}
