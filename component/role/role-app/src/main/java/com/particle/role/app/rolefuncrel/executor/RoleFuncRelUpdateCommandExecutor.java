package com.particle.role.app.rolefuncrel.executor;

import com.particle.role.app.rolefuncrel.structmapping.RoleFuncRelAppStructMapping;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelUpdateCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.domain.rolefuncrel.RoleFuncRel;
import com.particle.role.domain.rolefuncrel.RoleFuncRelId;
import com.particle.role.domain.rolefuncrel.gateway.RoleFuncRelGateway;
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
 * 角色菜单功能关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleFuncRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private RoleFuncRelGateway roleFuncRelGateway;

	/**
	 * 执行 角色菜单功能关系 更新指令
	 * @param roleFuncRelUpdateCommand
	 * @return
	 */
	public SingleResponse<RoleFuncRelVO> execute(@Valid RoleFuncRelUpdateCommand roleFuncRelUpdateCommand) {
		RoleFuncRel roleFuncRel = createByRoleFuncRelUpdateCommand(roleFuncRelUpdateCommand);
		boolean save = roleFuncRelGateway.save(roleFuncRel);
		if (save) {
			return SingleResponse.of(RoleFuncRelAppStructMapping.instance.toRoleFuncRelVO(roleFuncRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param roleFuncRelUpdateCommand
	 * @return
	 */
	private RoleFuncRel createByRoleFuncRelUpdateCommand(RoleFuncRelUpdateCommand roleFuncRelUpdateCommand){
		RoleFuncRel roleFuncRel = RoleFuncRel.create();
		RoleFuncRelUpdateCommandToRoleFuncRelMapping.instance.fillRoleFuncRelByRoleFuncRelUpdateCommand(roleFuncRel, roleFuncRelUpdateCommand);
		return roleFuncRel;
	}

	@Mapper
	interface RoleFuncRelUpdateCommandToRoleFuncRelMapping{
		RoleFuncRelUpdateCommandToRoleFuncRelMapping instance = Mappers.getMapper(RoleFuncRelUpdateCommandToRoleFuncRelMapping.class );

		default RoleFuncRelId map(Long id){
			if (id == null) {
				return null;
			}
			return RoleFuncRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param roleFuncRel
		 * @param roleFuncRelUpdateCommand
		 */
		void fillRoleFuncRelByRoleFuncRelUpdateCommand(@MappingTarget RoleFuncRel roleFuncRel, RoleFuncRelUpdateCommand roleFuncRelUpdateCommand);
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
