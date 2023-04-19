package com.particle.func.app.funcapplicationfuncrel.executor;

import com.particle.func.app.funcapplicationfuncrel.structmapping.FuncApplicationFuncRelAppStructMapping;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncApplicationFuncRelCreateCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRel;
import com.particle.func.domain.funcapplicationfuncrel.gateway.FuncApplicationFuncRelGateway;
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
 * 功能应用功能关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Component
@Validated
public class FuncApplicationFuncRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private FuncApplicationFuncRelGateway funcApplicationFuncRelGateway;

	/**
	 * 执行功能应用功能关系添加指令
	 * @param funcApplicationFuncRelCreateCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationFuncRelVO> execute(@Valid FuncApplicationFuncRelCreateCommand funcApplicationFuncRelCreateCommand) {
		FuncApplicationFuncRel funcApplicationFuncRel = createByFuncApplicationFuncRelCreateCommand(funcApplicationFuncRelCreateCommand);
		funcApplicationFuncRel.setAddControl(funcApplicationFuncRelCreateCommand);
		boolean save = funcApplicationFuncRelGateway.save(funcApplicationFuncRel);
		if (save) {
			return SingleResponse.of(FuncApplicationFuncRelAppStructMapping.instance.toFuncApplicationFuncRelVO(funcApplicationFuncRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据功能应用功能关系创建指令创建功能应用功能关系模型
	 * @param funcApplicationFuncRelCreateCommand
	 * @return
	 */
	private FuncApplicationFuncRel createByFuncApplicationFuncRelCreateCommand(FuncApplicationFuncRelCreateCommand funcApplicationFuncRelCreateCommand){
		FuncApplicationFuncRel funcApplicationFuncRel = FuncApplicationFuncRel.create();
		FuncApplicationFuncRelCreateCommandToFuncApplicationFuncRelMapping.instance.fillFuncApplicationFuncRelByFuncApplicationFuncRelCreateCommand(funcApplicationFuncRel, funcApplicationFuncRelCreateCommand);
		return funcApplicationFuncRel;
	}

	@Mapper
	interface  FuncApplicationFuncRelCreateCommandToFuncApplicationFuncRelMapping{
		FuncApplicationFuncRelCreateCommandToFuncApplicationFuncRelMapping instance = Mappers.getMapper( FuncApplicationFuncRelCreateCommandToFuncApplicationFuncRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param funcApplicationFuncRel
		 * @param funcApplicationFuncRelCreateCommand
		 */
		void fillFuncApplicationFuncRelByFuncApplicationFuncRelCreateCommand(@MappingTarget FuncApplicationFuncRel funcApplicationFuncRel, FuncApplicationFuncRelCreateCommand funcApplicationFuncRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param funcApplicationFuncRelGateway
	 */
	@Autowired
	public void setFuncApplicationFuncRelGateway(FuncApplicationFuncRelGateway funcApplicationFuncRelGateway) {
		this.funcApplicationFuncRelGateway = funcApplicationFuncRelGateway;
	}
}
