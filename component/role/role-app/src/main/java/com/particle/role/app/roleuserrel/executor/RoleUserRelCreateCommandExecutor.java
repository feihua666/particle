package com.particle.role.app.roleuserrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.role.app.roleuserrel.structmapping.RoleUserRelAppStructMapping;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.role.domain.roleuserrel.RoleUserRel;
import com.particle.role.domain.roleuserrel.gateway.RoleUserRelGateway;
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
 * 角色用户关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleUserRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private RoleUserRelGateway roleUserRelGateway;

	/**
	 * 执行角色用户关系添加指令
	 * @param roleUserRelCreateCommand
	 * @return
	 */
	public SingleResponse<RoleUserRelVO> execute(@Valid RoleUserRelCreateCommand roleUserRelCreateCommand) {
		RoleUserRel roleUserRel = createByRoleUserRelCreateCommand(roleUserRelCreateCommand);
		boolean save = roleUserRelGateway.save(roleUserRel);
		if (save) {
			return SingleResponse.of(RoleUserRelAppStructMapping.instance.toRoleUserRelVO(roleUserRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据角色用户关系创建指令创建角色用户关系模型
	 * @param roleUserRelCreateCommand
	 * @return
	 */
	private RoleUserRel createByRoleUserRelCreateCommand(RoleUserRelCreateCommand roleUserRelCreateCommand){
		RoleUserRel roleUserRel = RoleUserRel.create();
		RoleUserRelCreateCommandToRoleUserRelMapping.instance.fillRoleUserRelByRoleUserRelCreateCommand(roleUserRel, roleUserRelCreateCommand);
		return roleUserRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  RoleUserRelCreateCommandToRoleUserRelMapping{
		RoleUserRelCreateCommandToRoleUserRelMapping instance = Mappers.getMapper( RoleUserRelCreateCommandToRoleUserRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param roleUserRel
		 * @param roleUserRelCreateCommand
		 */
		void fillRoleUserRelByRoleUserRelCreateCommand(@MappingTarget RoleUserRel roleUserRel, RoleUserRelCreateCommand roleUserRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param roleUserRelGateway
	 */
	@Autowired
	public void setRoleUserRelGateway(RoleUserRelGateway roleUserRelGateway) {
		this.roleUserRelGateway = roleUserRelGateway;
	}
}
