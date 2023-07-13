package com.particle.dept.app.depttreeuserrel.executor;

import com.particle.dept.app.depttreeuserrel.structmapping.DeptTreeUserRelAppStructMapping;
import com.particle.dept.client.depttreeuserrel.dto.command.DeptTreeUserRelUpdateCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRel;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRelId;
import com.particle.dept.domain.depttreeuserrel.gateway.DeptTreeUserRelGateway;
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
 * 部门树用户关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DeptTreeUserRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeUserRelGateway deptTreeUserRelGateway;

	/**
	 * 执行 部门树用户关系 更新指令
	 * @param deptTreeUserRelUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeUserRelVO> execute(@Valid DeptTreeUserRelUpdateCommand deptTreeUserRelUpdateCommand) {
		DeptTreeUserRel deptTreeUserRel = createByDeptTreeUserRelUpdateCommand(deptTreeUserRelUpdateCommand);
		deptTreeUserRel.setUpdateControl(deptTreeUserRelUpdateCommand);
		boolean save = deptTreeUserRelGateway.save(deptTreeUserRel);
		if (save) {
			return SingleResponse.of(DeptTreeUserRelAppStructMapping.instance.toDeptTreeUserRelVO(deptTreeUserRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据部门树用户关系更新指令创建部门树用户关系模型
	 * @param deptTreeUserRelUpdateCommand
	 * @return
	 */
	private DeptTreeUserRel createByDeptTreeUserRelUpdateCommand(DeptTreeUserRelUpdateCommand deptTreeUserRelUpdateCommand){
		DeptTreeUserRel deptTreeUserRel = DeptTreeUserRel.create();
		DeptTreeUserRelUpdateCommandToDeptTreeUserRelMapping.instance.fillDeptTreeUserRelByDeptTreeUserRelUpdateCommand(deptTreeUserRel, deptTreeUserRelUpdateCommand);
		return deptTreeUserRel;
	}

	@Mapper
	interface DeptTreeUserRelUpdateCommandToDeptTreeUserRelMapping{
		DeptTreeUserRelUpdateCommandToDeptTreeUserRelMapping instance = Mappers.getMapper(DeptTreeUserRelUpdateCommandToDeptTreeUserRelMapping.class );

		default DeptTreeUserRelId map(Long id){
			if (id == null) {
				return null;
			}
			return DeptTreeUserRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param deptTreeUserRel
		 * @param deptTreeUserRelUpdateCommand
		 */
		void fillDeptTreeUserRelByDeptTreeUserRelUpdateCommand(@MappingTarget DeptTreeUserRel deptTreeUserRel, DeptTreeUserRelUpdateCommand deptTreeUserRelUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param deptTreeUserRelGateway
	 */
	@Autowired
	public void setDeptTreeUserRelGateway(DeptTreeUserRelGateway deptTreeUserRelGateway) {
		this.deptTreeUserRelGateway = deptTreeUserRelGateway;
	}
}
