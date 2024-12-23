package com.particle.func.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.func.app.structmapping.FuncGroupAppStructMapping;
import com.particle.func.client.dto.command.FuncGroupUpdateCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.func.domain.FuncGroup;
import com.particle.func.domain.FuncGroupId;
import com.particle.func.domain.gateway.FuncGroupGateway;
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
 * 功能组 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Component
@Validated
public class FuncGroupUpdateCommandExecutor  extends AbstractBaseExecutor {

	private FuncGroupGateway funcGroupGateway;

	/**
	 * 执行 功能组 更新指令
	 * @param funcGroupUpdateCommand
	 * @return
	 */
	public SingleResponse<FuncGroupVO> execute(@Valid FuncGroupUpdateCommand funcGroupUpdateCommand) {
		FuncGroup funcGroup = createByFuncGroupUpdateCommand(funcGroupUpdateCommand);
		funcGroup.setCodeNullIfEmpty();
		funcGroup.setUpdateControl(funcGroupUpdateCommand);
		boolean save = funcGroupGateway.save(funcGroup);
		if (save) {
			return SingleResponse.of(FuncGroupAppStructMapping.instance.toFuncGroupVO(funcGroup));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据功能组更新指令创建功能组模型
	 * @param funcGroupUpdateCommand
	 * @return
	 */
	private FuncGroup createByFuncGroupUpdateCommand(FuncGroupUpdateCommand funcGroupUpdateCommand){
		FuncGroup funcGroup = FuncGroup.create();
		FuncGroupUpdateCommandToFuncGroupMapping.instance.fillFuncGroupByFuncGroupUpdateCommand(funcGroup, funcGroupUpdateCommand);
		return funcGroup;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface FuncGroupUpdateCommandToFuncGroupMapping{
		FuncGroupUpdateCommandToFuncGroupMapping instance = Mappers.getMapper(FuncGroupUpdateCommandToFuncGroupMapping.class );

		default FuncGroupId map(Long id){
			if (id == null) {
				return null;
			}
			return FuncGroupId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param funcGroup
		 * @param funcGroupUpdateCommand
		 */
		void fillFuncGroupByFuncGroupUpdateCommand(@MappingTarget FuncGroup funcGroup, FuncGroupUpdateCommand funcGroupUpdateCommand);
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
