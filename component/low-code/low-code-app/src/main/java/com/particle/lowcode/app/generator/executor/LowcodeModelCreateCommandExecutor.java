package com.particle.lowcode.app.generator.executor;

import com.particle.lowcode.app.generator.structmapping.LowcodeModelAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.lowcode.domain.generator.LowcodeModel;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 低代码模型 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
@Validated
public class LowcodeModelCreateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeModelGateway lowcodeModelGateway;

	/**
	 * 执行低代码模型添加指令
	 * @param lowcodeModelCreateCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelVO> execute(@Valid LowcodeModelCreateCommand lowcodeModelCreateCommand) {
		LowcodeModel lowcodeModel = createByLowcodeModelCreateCommand(lowcodeModelCreateCommand);
		lowcodeModel.setAddControl(lowcodeModelCreateCommand);
		boolean save = lowcodeModelGateway.save(lowcodeModel);
		if (save) {
			return SingleResponse.of(LowcodeModelAppStructMapping.instance.toLowcodeModelVO(lowcodeModel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据低代码模型创建指令创建低代码模型模型
	 * @param lowcodeModelCreateCommand
	 * @return
	 */
	private LowcodeModel createByLowcodeModelCreateCommand(LowcodeModelCreateCommand lowcodeModelCreateCommand){
		LowcodeModel lowcodeModel = LowcodeModel.create();
		LowcodeModelCreateCommandToLowcodeModelMapping.instance.fillLowcodeModelByLowcodeModelCreateCommand(lowcodeModel, lowcodeModelCreateCommand);
		return lowcodeModel;
	}

	@Mapper
	interface  LowcodeModelCreateCommandToLowcodeModelMapping{
		LowcodeModelCreateCommandToLowcodeModelMapping instance = Mappers.getMapper( LowcodeModelCreateCommandToLowcodeModelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeModel
		 * @param lowcodeModelCreateCommand
		 */
		void fillLowcodeModelByLowcodeModelCreateCommand(@MappingTarget LowcodeModel lowcodeModel, LowcodeModelCreateCommand lowcodeModelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeModelGateway
	 */
	@Autowired
	public void setLowcodeModelGateway(LowcodeModelGateway lowcodeModelGateway) {
		this.lowcodeModelGateway = lowcodeModelGateway;
	}
}
