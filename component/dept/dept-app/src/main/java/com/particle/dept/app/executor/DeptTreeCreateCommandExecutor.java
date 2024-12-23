package com.particle.dept.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dept.app.structmapping.DeptTreeAppStructMapping;
import com.particle.dept.client.dto.command.DeptTreeCreateCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;
import com.particle.dept.domain.DeptTree;
import com.particle.dept.domain.gateway.DeptTreeGateway;
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
 * 部门树 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Component
@Validated
public class DeptTreeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeGateway deptTreeGateway;

	/**
	 * 执行部门树添加指令
	 * @param deptTreeCreateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeVO> execute(@Valid DeptTreeCreateCommand deptTreeCreateCommand) {
		DeptTree deptTree = createByDeptTreeCreateCommand(deptTreeCreateCommand);
		deptTree.setAddControl(deptTreeCreateCommand);
		boolean save = deptTreeGateway.save(deptTree);
		if (save) {
			return SingleResponse.of(DeptTreeAppStructMapping.instance.toDeptTreeVO(deptTree));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据部门树创建指令创建部门树模型
	 * @param deptTreeCreateCommand
	 * @return
	 */
	private DeptTree createByDeptTreeCreateCommand(DeptTreeCreateCommand deptTreeCreateCommand){
		DeptTree deptTree = DeptTree.create();
		DeptTreeCreateCommandToDeptTreeMapping.instance.fillDeptTreeByDeptTreeCreateCommand(deptTree, deptTreeCreateCommand);
		return deptTree;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DeptTreeCreateCommandToDeptTreeMapping{
		DeptTreeCreateCommandToDeptTreeMapping instance = Mappers.getMapper( DeptTreeCreateCommandToDeptTreeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param deptTree
		 * @param deptTreeCreateCommand
		 */
		void fillDeptTreeByDeptTreeCreateCommand(@MappingTarget DeptTree deptTree, DeptTreeCreateCommand deptTreeCreateCommand);
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
