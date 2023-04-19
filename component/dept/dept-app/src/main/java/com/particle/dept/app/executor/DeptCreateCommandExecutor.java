package com.particle.dept.app.executor;

import com.particle.dept.app.structmapping.DeptAppStructMapping;
import com.particle.dept.client.dto.command.DeptCreateCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.dept.domain.Dept;
import com.particle.dept.domain.gateway.DeptGateway;
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
 * 部门 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Component
@Validated
public class DeptCreateCommandExecutor  extends AbstractBaseExecutor {

	private DeptGateway deptGateway;

	/**
	 * 执行部门添加指令
	 * @param deptCreateCommand
	 * @return
	 */
	public SingleResponse<DeptVO> execute(@Valid DeptCreateCommand deptCreateCommand) {
		Dept dept = createByDeptCreateCommand(deptCreateCommand);
		dept.setAddControl(deptCreateCommand);
		boolean save = deptGateway.save(dept);
		if (save) {
			return SingleResponse.of(DeptAppStructMapping.instance.toDeptVO(dept));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据部门创建指令创建部门模型
	 * @param deptCreateCommand
	 * @return
	 */
	private Dept createByDeptCreateCommand(DeptCreateCommand deptCreateCommand){
		Dept dept = Dept.create();
		DeptCreateCommandToDeptMapping.instance.fillDeptByDeptCreateCommand(dept, deptCreateCommand);
		return dept;
	}

	@Mapper
	interface  DeptCreateCommandToDeptMapping{
		DeptCreateCommandToDeptMapping instance = Mappers.getMapper( DeptCreateCommandToDeptMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dept
		 * @param deptCreateCommand
		 */
		void fillDeptByDeptCreateCommand(@MappingTarget Dept dept, DeptCreateCommand deptCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param deptGateway
	 */
	@Autowired
	public void setDeptGateway(DeptGateway deptGateway) {
		this.deptGateway = deptGateway;
	}
}
