package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentGenAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.domain.generator.LowcodeSegmentGen;
import com.particle.lowcode.domain.generator.LowcodeSegmentGenId;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentGenGateway;
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
 * 低代码生成 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Component
@Validated
public class LowcodeSegmentGenUpdateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeSegmentGenGateway lowcodeSegmentGenGateway;

	private LowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor;

	/**
	 * 执行 低代码生成 更新指令
	 * @param lowcodeSegmentGenUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentGenVO> execute(@Valid LowcodeSegmentGenUpdateCommand lowcodeSegmentGenUpdateCommand) {
		LowcodeSegmentGen lowcodeSegmentGen = createByLowcodeSegmentGenUpdateCommand(lowcodeSegmentGenUpdateCommand);

		lowcodeSegmentGen.clearLowcodeModelJsonIfNecessary();

		if (lowcodeSegmentGen.canInitLowcodeModelJson()) {
			lowcodeSegmentGen.changeLowcodeModelJson(lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor.loadLowcodeModelJson(LowcodeModelId.of(lowcodeSegmentGen.getLowcodeModelId())));
		}

		lowcodeSegmentGen.setUpdateControl(lowcodeSegmentGenUpdateCommand);
		boolean save = lowcodeSegmentGenGateway.save(lowcodeSegmentGen);
		if (save) {
			return SingleResponse.of(LowcodeSegmentGenAppStructMapping.instance.toLowcodeSegmentGenVO(lowcodeSegmentGen));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据低代码生成更新指令创建低代码生成模型
	 * @param lowcodeSegmentGenUpdateCommand
	 * @return
	 */
	private LowcodeSegmentGen createByLowcodeSegmentGenUpdateCommand(LowcodeSegmentGenUpdateCommand lowcodeSegmentGenUpdateCommand){
		LowcodeSegmentGen lowcodeSegmentGen = LowcodeSegmentGen.create();
		LowcodeSegmentGenUpdateCommandToLowcodeSegmentGenMapping.instance.fillLowcodeSegmentGenByLowcodeSegmentGenUpdateCommand(lowcodeSegmentGen, lowcodeSegmentGenUpdateCommand);
		return lowcodeSegmentGen;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface LowcodeSegmentGenUpdateCommandToLowcodeSegmentGenMapping{
		LowcodeSegmentGenUpdateCommandToLowcodeSegmentGenMapping instance = Mappers.getMapper(LowcodeSegmentGenUpdateCommandToLowcodeSegmentGenMapping.class );

		default LowcodeSegmentGenId map(Long id){
			if (id == null) {
				return null;
			}
			return LowcodeSegmentGenId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeSegmentGen
		 * @param lowcodeSegmentGenUpdateCommand
		 */
		void fillLowcodeSegmentGenByLowcodeSegmentGenUpdateCommand(@MappingTarget LowcodeSegmentGen lowcodeSegmentGen, LowcodeSegmentGenUpdateCommand lowcodeSegmentGenUpdateCommand);
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
