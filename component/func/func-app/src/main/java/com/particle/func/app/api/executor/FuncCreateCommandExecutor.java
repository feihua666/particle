package com.particle.func.app.api.executor;


import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.func.app.api.wrapper.FuncWrapper;
import com.particle.func.client.dto.command.CreateFuncCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.domain.Func;
import com.particle.func.domain.gateway.FuncGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 区域创建指令执行器
 * </p>
 *
 * @author yangwei
 * @since 2022-04-30 19:12
 */
@Component
@Validated
public class FuncCreateCommandExecutor extends AbstractBaseExecutor {

	private FuncGateway funcGateway;

	/**
	 * 执行区域添加指令
	 * @param createAreaCommand
	 * @return
	 */
	public SingleResponse<FuncVO> execute(@Valid CreateFuncCommand createFuncCommand) {
		Func func = createByFuncCreateCommand(createFuncCommand);
		boolean save = funcGateway.save(func);
		if (save) {
			return SingleResponse.of(FuncWrapper.instance.toAreaVO(func));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param createAreaCommand
	 * @return
	 */
	private Func createByFuncCreateCommand(CreateFuncCommand createFuncCommand){
		Func func = Func.create();
		FuncCreateCommandToFuncMapping.instance.fillFuncByFuncCreateCommand(func, createFuncCommand);
		return func;
	}

	@Mapper
	interface FuncCreateCommandToFuncMapping {
		FuncCreateCommandToFuncMapping instance = Mappers.getMapper( FuncCreateCommandToFuncMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @ MappingTarget标注的类UserRoleDto 为目标类，user类为源类，调用此方法，会把源类中的属性更新到目标类中。更新规则还是由@Mapping指定。
		 * @param area
		 * @param createAreaCommand
		 */
		void fillFuncByFuncCreateCommand(@MappingTarget Func func, CreateFuncCommand createFuncCommand);
	}

	/**
	 * 注入使用set方法
	 * @param areaGateway
	 */
	@Autowired
	public void setAreaGateway(FuncGateway funcGateway) {
		this.funcGateway = funcGateway;
	}
}
