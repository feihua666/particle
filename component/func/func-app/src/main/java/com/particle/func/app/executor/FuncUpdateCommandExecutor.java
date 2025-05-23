package com.particle.func.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.func.app.structmapping.FuncAppStructMapping;
import com.particle.func.client.dto.command.FuncMoveCommand;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.domain.Func;
import com.particle.func.domain.FuncId;
import com.particle.func.domain.gateway.FuncGateway;
import com.particle.func.infrastructure.service.IFuncService;
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

	@Autowired
	private IFuncService iFuncService;
	/**
	 * 移动节点
	 * @param funcMoveNodeCommand
	 * @return
	 */
	public SingleResponse<FuncVO> moveNode(@Valid FuncMoveCommand funcMoveNodeCommand){
		int moveNode = iFuncService.moveNode(funcMoveNodeCommand.getId(), funcMoveNodeCommand.getParentId());

		Func func = funcGateway.getById(FuncId.of(funcMoveNodeCommand.getId()));
		return SingleResponse.of(FuncAppStructMapping.instance.toFuncVO(func));
	}


	/**
	 * 执行 菜单功能 更新指令
	 * @param funcUpdateCommand
	 * @return
	 */
	public SingleResponse<FuncVO> execute(@Valid FuncUpdateCommand funcUpdateCommand) {
		Func func = createByFuncUpdateCommand(funcUpdateCommand);
		func.setCodeNullIfEmpty();
		func.setUpdateControl(funcUpdateCommand);
		func.assertUrlNotEmptyIfNeccessary();
		boolean save = funcGateway.save(func);
		if (save) {
			return SingleResponse.of(FuncAppStructMapping.instance.toFuncVO(func));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据菜单功能更新指令创建菜单功能模型
	 * @param funcUpdateCommand
	 * @return
	 */
	private Func createByFuncUpdateCommand(FuncUpdateCommand funcUpdateCommand){
		Func func = Func.create();
		FuncUpdateCommandToFuncMapping.instance.fillFuncByFuncUpdateCommand(func, funcUpdateCommand);
		if (!funcUpdateCommand.getIsDisabled()) {
			funcUpdateCommand.setDisabledReason(null);
			func.setDisabledReason(null);
		}
		return func;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface FuncUpdateCommandToFuncMapping{
		FuncUpdateCommandToFuncMapping instance = Mappers.getMapper(FuncUpdateCommandToFuncMapping.class );

		default FuncId map(Long id){
			if (id == null) {
				return null;
			}
			return FuncId.of(id);
		}
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
