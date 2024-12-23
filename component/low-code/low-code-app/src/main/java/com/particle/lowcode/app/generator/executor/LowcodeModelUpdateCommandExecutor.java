package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.lowcode.domain.generator.LowcodeModel;
import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelGateway;
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
 * 低代码模型 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
@Validated
public class LowcodeModelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeModelGateway lowcodeModelGateway;

	/**
	 * 执行 低代码模型 更新指令
	 * @param lowcodeModelUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelVO> execute(@Valid LowcodeModelUpdateCommand lowcodeModelUpdateCommand) {
		LowcodeModel lowcodeModel = createByLowcodeModelUpdateCommand(lowcodeModelUpdateCommand);
		lowcodeModel.setUpdateControl(lowcodeModelUpdateCommand);
		boolean save = lowcodeModelGateway.save(lowcodeModel);
		if (save) {
			return SingleResponse.of(LowcodeModelAppStructMapping.instance.toLowcodeModelVO(lowcodeModel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据低代码模型更新指令创建低代码模型模型
	 * @param lowcodeModelUpdateCommand
	 * @return
	 */
	private LowcodeModel createByLowcodeModelUpdateCommand(LowcodeModelUpdateCommand lowcodeModelUpdateCommand){
		LowcodeModel lowcodeModel = LowcodeModel.create();
		LowcodeModelUpdateCommandToLowcodeModelMapping.instance.fillLowcodeModelByLowcodeModelUpdateCommand(lowcodeModel, lowcodeModelUpdateCommand);
		return lowcodeModel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface LowcodeModelUpdateCommandToLowcodeModelMapping{
		LowcodeModelUpdateCommandToLowcodeModelMapping instance = Mappers.getMapper(LowcodeModelUpdateCommandToLowcodeModelMapping.class );

		default LowcodeModelId map(Long id){
			if (id == null) {
				return null;
			}
			return LowcodeModelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeModel
		 * @param lowcodeModelUpdateCommand
		 */
		void fillLowcodeModelByLowcodeModelUpdateCommand(@MappingTarget LowcodeModel lowcodeModel, LowcodeModelUpdateCommand lowcodeModelUpdateCommand);
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
