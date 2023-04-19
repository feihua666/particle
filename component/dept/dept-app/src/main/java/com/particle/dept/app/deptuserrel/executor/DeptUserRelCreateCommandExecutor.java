package com.particle.dept.app.deptuserrel.executor;

import com.particle.dept.app.deptuserrel.structmapping.DeptUserRelAppStructMapping;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelCreateCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.dept.domain.deptuserrel.DeptUserRel;
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
 * 部门用户关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:09
 */
@Component
@Validated
public class DeptUserRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private DeptUserRelGateway deptUserRelGateway;

	/**
	 * 执行部门用户关系添加指令
	 * @param deptUserRelCreateCommand
	 * @return
	 */
	public SingleResponse<DeptUserRelVO> execute(@Valid DeptUserRelCreateCommand deptUserRelCreateCommand) {
		DeptUserRel deptUserRel = createByDeptUserRelCreateCommand(deptUserRelCreateCommand);
		deptUserRel.setAddControl(deptUserRelCreateCommand);
		boolean save = deptUserRelGateway.save(deptUserRel);
		if (save) {
			return SingleResponse.of(DeptUserRelAppStructMapping.instance.toDeptUserRelVO(deptUserRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据部门用户关系创建指令创建部门用户关系模型
	 * @param deptUserRelCreateCommand
	 * @return
	 */
	private DeptUserRel createByDeptUserRelCreateCommand(DeptUserRelCreateCommand deptUserRelCreateCommand){
		DeptUserRel deptUserRel = DeptUserRel.create();
		DeptUserRelCreateCommandToDeptUserRelMapping.instance.fillDeptUserRelByDeptUserRelCreateCommand(deptUserRel, deptUserRelCreateCommand);
		return deptUserRel;
	}

	@Mapper
	interface  DeptUserRelCreateCommandToDeptUserRelMapping{
		DeptUserRelCreateCommandToDeptUserRelMapping instance = Mappers.getMapper( DeptUserRelCreateCommandToDeptUserRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param deptUserRel
		 * @param deptUserRelCreateCommand
		 */
		void fillDeptUserRelByDeptUserRelCreateCommand(@MappingTarget DeptUserRel deptUserRel, DeptUserRelCreateCommand deptUserRelCreateCommand);
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
