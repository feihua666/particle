package com.particle.role.app.roledatascoperel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.role.app.roledatascoperel.structmapping.RoleDataScopeRelAppStructMapping;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelUpdateCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRel;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRelId;
import com.particle.role.domain.roledatascoperel.gateway.RoleDataScopeRelGateway;
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
 * 角色数据范围关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class RoleDataScopeRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private RoleDataScopeRelGateway roleDataScopeRelGateway;

	/**
	 * 执行 角色数据范围关系 更新指令
	 * @param roleDataScopeRelUpdateCommand
	 * @return
	 */
	public SingleResponse<RoleDataScopeRelVO> execute(@Valid RoleDataScopeRelUpdateCommand roleDataScopeRelUpdateCommand) {
		RoleDataScopeRel roleDataScopeRel = createByRoleDataScopeRelUpdateCommand(roleDataScopeRelUpdateCommand);
		roleDataScopeRel.setUpdateControl(roleDataScopeRelUpdateCommand);
		boolean save = roleDataScopeRelGateway.save(roleDataScopeRel);
		if (save) {
			return SingleResponse.of(RoleDataScopeRelAppStructMapping.instance.toRoleDataScopeRelVO(roleDataScopeRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据角色数据范围关系更新指令创建角色数据范围关系模型
	 * @param roleDataScopeRelUpdateCommand
	 * @return
	 */
	private RoleDataScopeRel createByRoleDataScopeRelUpdateCommand(RoleDataScopeRelUpdateCommand roleDataScopeRelUpdateCommand){
		RoleDataScopeRel roleDataScopeRel = RoleDataScopeRel.create();
		RoleDataScopeRelUpdateCommandToRoleDataScopeRelMapping.instance.fillRoleDataScopeRelByRoleDataScopeRelUpdateCommand(roleDataScopeRel, roleDataScopeRelUpdateCommand);
		return roleDataScopeRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface RoleDataScopeRelUpdateCommandToRoleDataScopeRelMapping{
		RoleDataScopeRelUpdateCommandToRoleDataScopeRelMapping instance = Mappers.getMapper(RoleDataScopeRelUpdateCommandToRoleDataScopeRelMapping.class );

		default RoleDataScopeRelId map(Long id){
			if (id == null) {
				return null;
			}
			return RoleDataScopeRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param roleDataScopeRel
		 * @param roleDataScopeRelUpdateCommand
		 */
		void fillRoleDataScopeRelByRoleDataScopeRelUpdateCommand(@MappingTarget RoleDataScopeRel roleDataScopeRel, RoleDataScopeRelUpdateCommand roleDataScopeRelUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param roleDataScopeRelGateway
	 */
	@Autowired
	public void setRoleDataScopeRelGateway(RoleDataScopeRelGateway roleDataScopeRelGateway) {
		this.roleDataScopeRelGateway = roleDataScopeRelGateway;
	}
}
