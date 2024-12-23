package com.particle.func.app.application.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.func.app.application.structmapping.FuncApplicationAppStructMapping;
import com.particle.func.client.application.dto.command.FuncApplicationUpdateCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.func.domain.application.FuncApplication;
import com.particle.func.domain.application.FuncApplicationId;
import com.particle.func.domain.application.gateway.FuncApplicationGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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
 * 功能应用 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class FuncApplicationUpdateCommandExecutor  extends AbstractBaseExecutor {

	private FuncApplicationGateway funcApplicationGateway;

	/**
	 * 执行 功能应用 更新指令
	 * @param funcApplicationUpdateCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationVO> execute(@Valid FuncApplicationUpdateCommand funcApplicationUpdateCommand) {
		FuncApplication funcApplication = createByFuncApplicationUpdateCommand(funcApplicationUpdateCommand);
		funcApplication.setUpdateControl(funcApplicationUpdateCommand);
		boolean save = funcApplicationGateway.save(funcApplication);
		if (save) {
			return SingleResponse.of(FuncApplicationAppStructMapping.instance.toFuncApplicationVO(funcApplication));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据功能应用更新指令创建功能应用模型
	 * @param funcApplicationUpdateCommand
	 * @return
	 */
	private FuncApplication createByFuncApplicationUpdateCommand(FuncApplicationUpdateCommand funcApplicationUpdateCommand){
		FuncApplication funcApplication = FuncApplication.create();
		FuncApplicationUpdateCommandToFuncApplicationMapping.instance.fillFuncApplicationByFuncApplicationUpdateCommand(funcApplication, funcApplicationUpdateCommand);
		return funcApplication;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface FuncApplicationUpdateCommandToFuncApplicationMapping{
		FuncApplicationUpdateCommandToFuncApplicationMapping instance = Mappers.getMapper(FuncApplicationUpdateCommandToFuncApplicationMapping.class );

		default FuncApplicationId map(Long id){
			if (id == null) {
				return null;
			}
			return FuncApplicationId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param funcApplication
		 * @param funcApplicationUpdateCommand
		 */
		void fillFuncApplicationByFuncApplicationUpdateCommand(@MappingTarget FuncApplication funcApplication, FuncApplicationUpdateCommand funcApplicationUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param funcApplicationGateway
	 */
	@Autowired
	public void setFuncApplicationGateway(FuncApplicationGateway funcApplicationGateway) {
		this.funcApplicationGateway = funcApplicationGateway;
	}
}
