package com.particle.dept.app.executor;

import com.particle.dept.app.structmapping.DeptTreeAppStructMapping;
import com.particle.dept.client.dto.command.DeptTreeUpdateCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;
import com.particle.dept.domain.DeptTree;
import com.particle.dept.domain.DeptTreeId;
import com.particle.dept.domain.gateway.DeptTreeGateway;
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
 * 部门树 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DeptTreeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeGateway deptTreeGateway;

	/**
	 * 执行 部门树 更新指令
	 * @param deptTreeUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeVO> execute(@Valid DeptTreeUpdateCommand deptTreeUpdateCommand) {
		DeptTree deptTree = createByDeptTreeUpdateCommand(deptTreeUpdateCommand);
		deptTree.setUpdateControl(deptTreeUpdateCommand);
		boolean save = deptTreeGateway.save(deptTree);
		if (save) {
			return SingleResponse.of(DeptTreeAppStructMapping.instance.toDeptTreeVO(deptTree));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param deptTreeUpdateCommand
	 * @return
	 */
	private DeptTree createByDeptTreeUpdateCommand(DeptTreeUpdateCommand deptTreeUpdateCommand){
		DeptTree deptTree = DeptTree.create();
		DeptTreeUpdateCommandToDeptTreeMapping.instance.fillDeptTreeByDeptTreeUpdateCommand(deptTree, deptTreeUpdateCommand);
		return deptTree;
	}

	@Mapper
	interface DeptTreeUpdateCommandToDeptTreeMapping{
		DeptTreeUpdateCommandToDeptTreeMapping instance = Mappers.getMapper(DeptTreeUpdateCommandToDeptTreeMapping.class );

		default DeptTreeId map(Long id){
			if (id == null) {
				return null;
			}
			return DeptTreeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param deptTree
		 * @param deptTreeUpdateCommand
		 */
		void fillDeptTreeByDeptTreeUpdateCommand(@MappingTarget DeptTree deptTree, DeptTreeUpdateCommand deptTreeUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param deptTreeGateway
	 */
	@Autowired
	public void setDeptTreeGateway(DeptTreeGateway deptTreeGateway) {
		this.deptTreeGateway = deptTreeGateway;
	}
}
