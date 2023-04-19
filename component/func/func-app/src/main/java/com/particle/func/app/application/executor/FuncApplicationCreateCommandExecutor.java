package com.particle.func.app.application.executor;

import com.particle.func.app.application.structmapping.FuncApplicationAppStructMapping;
import com.particle.func.client.application.dto.command.FuncApplicationCreateCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.func.domain.application.FuncApplication;
import com.particle.func.domain.application.gateway.FuncApplicationGateway;
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
 * 功能应用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Component
@Validated
public class FuncApplicationCreateCommandExecutor  extends AbstractBaseExecutor {

	private FuncApplicationGateway funcApplicationGateway;

	/**
	 * 执行功能应用添加指令
	 * @param funcApplicationCreateCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationVO> execute(@Valid FuncApplicationCreateCommand funcApplicationCreateCommand) {
		FuncApplication funcApplication = createByFuncApplicationCreateCommand(funcApplicationCreateCommand);
		funcApplication.setAddControl(funcApplicationCreateCommand);
		boolean save = funcApplicationGateway.save(funcApplication);
		if (save) {
			return SingleResponse.of(FuncApplicationAppStructMapping.instance.toFuncApplicationVO(funcApplication));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据功能应用创建指令创建功能应用模型
	 * @param funcApplicationCreateCommand
	 * @return
	 */
	private FuncApplication createByFuncApplicationCreateCommand(FuncApplicationCreateCommand funcApplicationCreateCommand){
		FuncApplication funcApplication = FuncApplication.create();
		FuncApplicationCreateCommandToFuncApplicationMapping.instance.fillFuncApplicationByFuncApplicationCreateCommand(funcApplication, funcApplicationCreateCommand);
		return funcApplication;
	}

	@Mapper
	interface  FuncApplicationCreateCommandToFuncApplicationMapping{
		FuncApplicationCreateCommandToFuncApplicationMapping instance = Mappers.getMapper( FuncApplicationCreateCommandToFuncApplicationMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param funcApplication
		 * @param funcApplicationCreateCommand
		 */
		void fillFuncApplicationByFuncApplicationCreateCommand(@MappingTarget FuncApplication funcApplication, FuncApplicationCreateCommand funcApplicationCreateCommand);
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
