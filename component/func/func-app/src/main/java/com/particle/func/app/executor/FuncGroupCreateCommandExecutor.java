package com.particle.func.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.func.app.structmapping.FuncGroupAppStructMapping;
import com.particle.func.client.dto.command.FuncGroupCreateCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.func.domain.FuncGroup;
import com.particle.func.domain.gateway.FuncGroupGateway;
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
 * 功能组 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Component
@Validated
public class FuncGroupCreateCommandExecutor  extends AbstractBaseExecutor {

	private FuncGroupGateway funcGroupGateway;

	/**
	 * 执行功能组添加指令
	 * @param funcGroupCreateCommand
	 * @return
	 */
	public SingleResponse<FuncGroupVO> execute(@Valid FuncGroupCreateCommand funcGroupCreateCommand) {
		FuncGroup funcGroup = createByFuncGroupCreateCommand(funcGroupCreateCommand);
		funcGroup.setCodeNullIfEmpty();
		funcGroup.setAddControl(funcGroupCreateCommand);
		boolean save = funcGroupGateway.save(funcGroup);
		if (save) {
			return SingleResponse.of(FuncGroupAppStructMapping.instance.toFuncGroupVO(funcGroup));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据功能组创建指令创建功能组模型
	 * @param funcGroupCreateCommand
	 * @return
	 */
	private FuncGroup createByFuncGroupCreateCommand(FuncGroupCreateCommand funcGroupCreateCommand){
		FuncGroup funcGroup = FuncGroup.create();
		FuncGroupCreateCommandToFuncGroupMapping.instance.fillFuncGroupByFuncGroupCreateCommand(funcGroup, funcGroupCreateCommand);
		return funcGroup;
	}

	@Mapper
	interface  FuncGroupCreateCommandToFuncGroupMapping{
		FuncGroupCreateCommandToFuncGroupMapping instance = Mappers.getMapper( FuncGroupCreateCommandToFuncGroupMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param funcGroup
		 * @param funcGroupCreateCommand
		 */
		void fillFuncGroupByFuncGroupCreateCommand(@MappingTarget FuncGroup funcGroup, FuncGroupCreateCommand funcGroupCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param funcGroupGateway
	 */
	@Autowired
	public void setFuncGroupGateway(FuncGroupGateway funcGroupGateway) {
		this.funcGroupGateway = funcGroupGateway;
	}
}
