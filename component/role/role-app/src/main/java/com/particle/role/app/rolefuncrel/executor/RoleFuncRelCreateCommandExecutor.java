package com.particle.role.app.rolefuncrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.role.app.rolefuncrel.structmapping.RoleFuncRelAppStructMapping;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelCreateCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.domain.rolefuncrel.RoleFuncRel;
import com.particle.role.domain.rolefuncrel.gateway.RoleFuncRelGateway;
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
 * 角色菜单功能关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleFuncRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private RoleFuncRelGateway roleFuncRelGateway;

	/**
	 * 执行角色菜单功能关系添加指令
	 * @param roleFuncRelCreateCommand
	 * @return
	 */
	public SingleResponse<RoleFuncRelVO> execute(@Valid RoleFuncRelCreateCommand roleFuncRelCreateCommand) {
		RoleFuncRel roleFuncRel = createByRoleFuncRelCreateCommand(roleFuncRelCreateCommand);
		boolean save = roleFuncRelGateway.save(roleFuncRel);
		if (save) {
			return SingleResponse.of(RoleFuncRelAppStructMapping.instance.toRoleFuncRelVO(roleFuncRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据角色菜单功能关系创建指令创建角色菜单功能关系模型
	 * @param roleFuncRelCreateCommand
	 * @return
	 */
	private RoleFuncRel createByRoleFuncRelCreateCommand(RoleFuncRelCreateCommand roleFuncRelCreateCommand){
		RoleFuncRel roleFuncRel = RoleFuncRel.create();
		RoleFuncRelCreateCommandToRoleFuncRelMapping.instance.fillRoleFuncRelByRoleFuncRelCreateCommand(roleFuncRel, roleFuncRelCreateCommand);
		return roleFuncRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  RoleFuncRelCreateCommandToRoleFuncRelMapping{
		RoleFuncRelCreateCommandToRoleFuncRelMapping instance = Mappers.getMapper( RoleFuncRelCreateCommandToRoleFuncRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param roleFuncRel
		 * @param roleFuncRelCreateCommand
		 */
		void fillRoleFuncRelByRoleFuncRelCreateCommand(@MappingTarget RoleFuncRel roleFuncRel, RoleFuncRelCreateCommand roleFuncRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param roleFuncRelGateway
	 */
	@Autowired
	public void setRoleFuncRelGateway(RoleFuncRelGateway roleFuncRelGateway) {
		this.roleFuncRelGateway = roleFuncRelGateway;
	}
}
