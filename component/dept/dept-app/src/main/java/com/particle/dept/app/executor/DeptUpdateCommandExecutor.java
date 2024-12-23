package com.particle.dept.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dept.app.structmapping.DeptAppStructMapping;
import com.particle.dept.client.dto.command.DeptUpdateCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.dept.domain.Dept;
import com.particle.dept.domain.DeptId;
import com.particle.dept.domain.gateway.DeptGateway;
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
 * 部门 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DeptUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DeptGateway deptGateway;

	/**
	 * 执行 部门 更新指令
	 * @param deptUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptVO> execute(@Valid DeptUpdateCommand deptUpdateCommand) {
		Dept dept = createByDeptUpdateCommand(deptUpdateCommand);
		dept.setUpdateControl(deptUpdateCommand);
		boolean save = deptGateway.save(dept);
		if (save) {
			return SingleResponse.of(DeptAppStructMapping.instance.toDeptVO(dept));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据部门更新指令创建部门模型
	 * @param deptUpdateCommand
	 * @return
	 */
	private Dept createByDeptUpdateCommand(DeptUpdateCommand deptUpdateCommand){
		Dept dept = Dept.create();
		DeptUpdateCommandToDeptMapping.instance.fillDeptByDeptUpdateCommand(dept, deptUpdateCommand);
		return dept;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DeptUpdateCommandToDeptMapping{
		DeptUpdateCommandToDeptMapping instance = Mappers.getMapper(DeptUpdateCommandToDeptMapping.class );

		default DeptId map(Long id){
			if (id == null) {
				return null;
			}
			return DeptId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dept
		 * @param deptUpdateCommand
		 */
		void fillDeptByDeptUpdateCommand(@MappingTarget Dept dept, DeptUpdateCommand deptUpdateCommand);
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
