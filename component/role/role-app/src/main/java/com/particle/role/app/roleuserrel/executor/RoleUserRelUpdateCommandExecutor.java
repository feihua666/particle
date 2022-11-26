package com.particle.role.app.roleuserrel.executor;

import com.particle.role.app.roleuserrel.structmapping.RoleUserRelAppStructMapping;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelUpdateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.role.domain.roleuserrel.RoleUserRel;
import com.particle.role.domain.roleuserrel.RoleUserRelId;
import com.particle.role.domain.roleuserrel.gateway.RoleUserRelGateway;
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
 * 角色用户关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleUserRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private RoleUserRelGateway roleUserRelGateway;

	/**
	 * 执行 角色用户关系 更新指令
	 * @param roleUserRelUpdateCommand
	 * @return
	 */
	public SingleResponse<RoleUserRelVO> execute(@Valid RoleUserRelUpdateCommand roleUserRelUpdateCommand) {
		RoleUserRel roleUserRel = createByRoleUserRelUpdateCommand(roleUserRelUpdateCommand);
		boolean save = roleUserRelGateway.save(roleUserRel);
		if (save) {
			return SingleResponse.of(RoleUserRelAppStructMapping.instance.toRoleUserRelVO(roleUserRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param roleUserRelUpdateCommand
	 * @return
	 */
	private RoleUserRel createByRoleUserRelUpdateCommand(RoleUserRelUpdateCommand roleUserRelUpdateCommand){
		RoleUserRel roleUserRel = RoleUserRel.create();
		RoleUserRelUpdateCommandToRoleUserRelMapping.instance.fillRoleUserRelByRoleUserRelUpdateCommand(roleUserRel, roleUserRelUpdateCommand);
		return roleUserRel;
	}

	@Mapper
	interface RoleUserRelUpdateCommandToRoleUserRelMapping{
		RoleUserRelUpdateCommandToRoleUserRelMapping instance = Mappers.getMapper(RoleUserRelUpdateCommandToRoleUserRelMapping.class );

		default RoleUserRelId map(Long id){
			if (id == null) {
				return null;
			}
			return RoleUserRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param roleUserRel
		 * @param roleUserRelUpdateCommand
		 */
		void fillRoleUserRelByRoleUserRelUpdateCommand(@MappingTarget RoleUserRel roleUserRel, RoleUserRelUpdateCommand roleUserRelUpdateCommand);
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
