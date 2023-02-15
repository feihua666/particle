package com.particle.lowcode.app.generator.executor;

import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentGenAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.domain.generator.LowcodeSegmentGen;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentGenGateway;
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
 * 低代码生成 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Component
@Validated
public class LowcodeSegmentGenCreateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeSegmentGenGateway lowcodeSegmentGenGateway;

	private LowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor;


	/**
	 * 执行低代码生成添加指令
	 * @param lowcodeSegmentGenCreateCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentGenVO> execute(@Valid LowcodeSegmentGenCreateCommand lowcodeSegmentGenCreateCommand) {
		LowcodeSegmentGen lowcodeSegmentGen = createByLowcodeSegmentGenCreateCommand(lowcodeSegmentGenCreateCommand);

		if (lowcodeSegmentGen.canInitLowcodeModelJson()) {
			lowcodeSegmentGen.changeLowcodeModelJson(lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor.loadLowcodeModelJson(LowcodeModelId.of(lowcodeSegmentGen.getLowcodeModelId())));
		}

		lowcodeSegmentGen.setAddControl(lowcodeSegmentGenCreateCommand);
		lowcodeSegmentGen.changeToNotGenerated();
		boolean save = lowcodeSegmentGenGateway.save(lowcodeSegmentGen);
		if (save) {
			return SingleResponse.of(LowcodeSegmentGenAppStructMapping.instance.toLowcodeSegmentGenVO(lowcodeSegmentGen));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据低代码生成创建指令创建低代码生成模型
	 * @param lowcodeSegmentGenCreateCommand
	 * @return
	 */
	private LowcodeSegmentGen createByLowcodeSegmentGenCreateCommand(LowcodeSegmentGenCreateCommand lowcodeSegmentGenCreateCommand){
		LowcodeSegmentGen lowcodeSegmentGen = LowcodeSegmentGen.create();
		LowcodeSegmentGenCreateCommandToLowcodeSegmentGenMapping.instance.fillLowcodeSegmentGenByLowcodeSegmentGenCreateCommand(lowcodeSegmentGen, lowcodeSegmentGenCreateCommand);
		return lowcodeSegmentGen;
	}

	@Mapper
	interface  LowcodeSegmentGenCreateCommandToLowcodeSegmentGenMapping{
		LowcodeSegmentGenCreateCommandToLowcodeSegmentGenMapping instance = Mappers.getMapper( LowcodeSegmentGenCreateCommandToLowcodeSegmentGenMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeSegmentGen
		 * @param lowcodeSegmentGenCreateCommand
		 */
		void fillLowcodeSegmentGenByLowcodeSegmentGenCreateCommand(@MappingTarget LowcodeSegmentGen lowcodeSegmentGen, LowcodeSegmentGenCreateCommand lowcodeSegmentGenCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeSegmentGenGateway
	 */
	@Autowired
	public void setLowcodeSegmentGenGateway(LowcodeSegmentGenGateway lowcodeSegmentGenGateway) {
		this.lowcodeSegmentGenGateway = lowcodeSegmentGenGateway;
	}
	@Autowired
	public void setLowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor(LowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor) {
		this.lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor = lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor;
	}
}
