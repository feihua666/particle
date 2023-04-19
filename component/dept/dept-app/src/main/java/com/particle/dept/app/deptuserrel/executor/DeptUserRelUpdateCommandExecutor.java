package com.particle.dept.app.deptuserrel.executor;

import com.particle.dept.app.deptuserrel.structmapping.DeptUserRelAppStructMapping;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelUpdateCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.dept.domain.deptuserrel.DeptUserRel;
import com.particle.dept.domain.deptuserrel.DeptUserRelId;
import com.particle.dept.domain.deptuserrel.gateway.DeptUserRelGateway;
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
 * 部门用户关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DeptUserRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DeptUserRelGateway deptUserRelGateway;

	/**
	 * 执行 部门用户关系 更新指令
	 * @param deptUserRelUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptUserRelVO> execute(@Valid DeptUserRelUpdateCommand deptUserRelUpdateCommand) {
		DeptUserRel deptUserRel = createByDeptUserRelUpdateCommand(deptUserRelUpdateCommand);
		deptUserRel.setUpdateControl(deptUserRelUpdateCommand);
		boolean save = deptUserRelGateway.save(deptUserRel);
		if (save) {
			return SingleResponse.of(DeptUserRelAppStructMapping.instance.toDeptUserRelVO(deptUserRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param deptUserRelUpdateCommand
	 * @return
	 */
	private DeptUserRel createByDeptUserRelUpdateCommand(DeptUserRelUpdateCommand deptUserRelUpdateCommand){
		DeptUserRel deptUserRel = DeptUserRel.create();
		DeptUserRelUpdateCommandToDeptUserRelMapping.instance.fillDeptUserRelByDeptUserRelUpdateCommand(deptUserRel, deptUserRelUpdateCommand);
		return deptUserRel;
	}

	@Mapper
	interface DeptUserRelUpdateCommandToDeptUserRelMapping{
		DeptUserRelUpdateCommandToDeptUserRelMapping instance = Mappers.getMapper(DeptUserRelUpdateCommandToDeptUserRelMapping.class );

		default DeptUserRelId map(Long id){
			if (id == null) {
				return null;
			}
			return DeptUserRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param deptUserRel
		 * @param deptUserRelUpdateCommand
		 */
		void fillDeptUserRelByDeptUserRelUpdateCommand(@MappingTarget DeptUserRel deptUserRel, DeptUserRelUpdateCommand deptUserRelUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param deptUserRelGateway
	 */
	@Autowired
	public void setDeptUserRelGateway(DeptUserRelGateway deptUserRelGateway) {
		this.deptUserRelGateway = deptUserRelGateway;
	}
}
