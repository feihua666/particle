package com.particle.dept.app.executor;

import com.particle.dept.app.structmapping.DeptTreeNameAppStructMapping;
import com.particle.dept.client.dto.command.DeptTreeNameCreateCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
import com.particle.dept.domain.DeptTreeName;
import com.particle.dept.domain.gateway.DeptTreeNameGateway;
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
 * 部门树名称 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Component
@Validated
public class DeptTreeNameCreateCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeNameGateway deptTreeNameGateway;

	/**
	 * 执行部门树名称添加指令
	 * @param deptTreeNameCreateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeNameVO> execute(@Valid DeptTreeNameCreateCommand deptTreeNameCreateCommand) {
		DeptTreeName deptTreeName = createByDeptTreeNameCreateCommand(deptTreeNameCreateCommand);
		deptTreeName.setAddControl(deptTreeNameCreateCommand);
		boolean save = deptTreeNameGateway.save(deptTreeName);
		if (save) {
			return SingleResponse.of(DeptTreeNameAppStructMapping.instance.toDeptTreeNameVO(deptTreeName));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据部门树名称创建指令创建部门树名称模型
	 * @param deptTreeNameCreateCommand
	 * @return
	 */
	private DeptTreeName createByDeptTreeNameCreateCommand(DeptTreeNameCreateCommand deptTreeNameCreateCommand){
		DeptTreeName deptTreeName = DeptTreeName.create();
		DeptTreeNameCreateCommandToDeptTreeNameMapping.instance.fillDeptTreeNameByDeptTreeNameCreateCommand(deptTreeName, deptTreeNameCreateCommand);
		return deptTreeName;
	}

	@Mapper
	interface  DeptTreeNameCreateCommandToDeptTreeNameMapping{
		DeptTreeNameCreateCommandToDeptTreeNameMapping instance = Mappers.getMapper( DeptTreeNameCreateCommandToDeptTreeNameMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param deptTreeName
		 * @param deptTreeNameCreateCommand
		 */
		void fillDeptTreeNameByDeptTreeNameCreateCommand(@MappingTarget DeptTreeName deptTreeName, DeptTreeNameCreateCommand deptTreeNameCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param deptTreeNameGateway
	 */
	@Autowired
	public void setDeptTreeNameGateway(DeptTreeNameGateway deptTreeNameGateway) {
		this.deptTreeNameGateway = deptTreeNameGateway;
	}
}
