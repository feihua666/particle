package com.particle.func.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.func.app.structmapping.FuncAppStructMapping;
import com.particle.func.client.dto.command.FuncCreateCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.domain.Func;
import com.particle.func.domain.gateway.FuncGateway;
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
 * 菜单功能 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class FuncCreateCommandExecutor  extends AbstractBaseExecutor {

	private FuncGateway funcGateway;

	/**
	 * 执行菜单功能添加指令
	 * @param funcCreateCommand
	 * @return
	 */
	public SingleResponse<FuncVO> execute(@Valid FuncCreateCommand funcCreateCommand) {
		Func func = createByFuncCreateCommand(funcCreateCommand);
		func.setCodeNullIfEmpty();
		func.setAddControl(funcCreateCommand);
		func.assertUrlNotEmptyIfNeccessary();
		boolean save = funcGateway.save(func);
		if (save) {
			return SingleResponse.of(FuncAppStructMapping.instance.toFuncVO(func));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据菜单功能创建指令创建菜单功能模型
	 * @param funcCreateCommand
	 * @return
	 */
	private Func createByFuncCreateCommand(FuncCreateCommand funcCreateCommand){
		Func func = Func.create();
		FuncCreateCommandToFuncMapping.instance.fillFuncByFuncCreateCommand(func, funcCreateCommand);
		if (!funcCreateCommand.getIsDisabled()) {
			funcCreateCommand.setDisabledReason(null);
			func.setDisabledReason(null);
		}
		return func;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  FuncCreateCommandToFuncMapping{
		FuncCreateCommandToFuncMapping instance = Mappers.getMapper( FuncCreateCommandToFuncMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param func
		 * @param funcCreateCommand
		 */
		void fillFuncByFuncCreateCommand(@MappingTarget Func func, FuncCreateCommand funcCreateCommand);
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
