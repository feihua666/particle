package com.particle.func.app.executor;

import com.particle.func.app.structmapping.FuncAppStructMapping;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.domain.Func;
import com.particle.func.domain.gateway.FuncGateway;
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
 * 菜单功能 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class FuncUpdateCommandExecutor  extends AbstractBaseExecutor {

	private FuncGateway funcGateway;

	/**
	 * 执行 菜单功能 更新指令
	 * @param funcUpdateCommand
	 * @return
	 */
	public SingleResponse<FuncVO> execute(@Valid FuncUpdateCommand funcUpdateCommand) {
		Func func = createByFuncUpdateCommand(funcUpdateCommand);
		boolean save = funcGateway.save(func);
		if (save) {
			return SingleResponse.of(FuncAppStructMapping.instance.toFuncVO(func));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param funcUpdateCommand
	 * @return
	 */
	private Func createByFuncUpdateCommand(FuncUpdateCommand funcUpdateCommand){
		Func func = Func.create();
		FuncUpdateCommandToFuncMapping.instance.fillFuncByFuncUpdateCommand(func, funcUpdateCommand);
		return func;
	}

	@Mapper
	interface FuncUpdateCommandToFuncMapping{
		FuncUpdateCommandToFuncMapping instance = Mappers.getMapper(FuncUpdateCommandToFuncMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param func
		 * @param funcUpdateCommand
		 */
		void fillFuncByFuncUpdateCommand(@MappingTarget Func func, FuncUpdateCommand funcUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param funcGateway
	 */
	@Autowired
	public void setFuncGateway(FuncGateway funcGateway) {
		this.funcGateway = funcGateway;
	}
}
