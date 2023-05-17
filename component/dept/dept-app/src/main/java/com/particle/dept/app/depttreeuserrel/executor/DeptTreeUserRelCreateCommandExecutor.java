package com.particle.dept.app.depttreeuserrel.executor;

import com.particle.dept.app.depttreeuserrel.structmapping.DeptTreeUserRelAppStructMapping;
import com.particle.dept.client.depttreeuserrel.dto.command.DeptTreeUserRelCreateCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRel;
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
 * 部门树用户关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Component
@Validated
public class DeptTreeUserRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeUserRelGateway deptTreeUserRelGateway;

	/**
	 * 执行部门树用户关系添加指令
	 * @param deptTreeUserRelCreateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeUserRelVO> execute(@Valid DeptTreeUserRelCreateCommand deptTreeUserRelCreateCommand) {
		DeptTreeUserRel deptTreeUserRel = createByDeptTreeUserRelCreateCommand(deptTreeUserRelCreateCommand);
		deptTreeUserRel.setAddControl(deptTreeUserRelCreateCommand);
		boolean save = deptTreeUserRelGateway.save(deptTreeUserRel);
		if (save) {
			return SingleResponse.of(DeptTreeUserRelAppStructMapping.instance.toDeptTreeUserRelVO(deptTreeUserRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据部门树用户关系创建指令创建部门树用户关系模型
	 * @param deptTreeUserRelCreateCommand
	 * @return
	 */
	private DeptTreeUserRel createByDeptTreeUserRelCreateCommand(DeptTreeUserRelCreateCommand deptTreeUserRelCreateCommand){
		DeptTreeUserRel deptTreeUserRel = DeptTreeUserRel.create();
		DeptTreeUserRelCreateCommandToDeptTreeUserRelMapping.instance.fillDeptTreeUserRelByDeptTreeUserRelCreateCommand(deptTreeUserRel, deptTreeUserRelCreateCommand);
		return deptTreeUserRel;
	}

	@Mapper
	interface  DeptTreeUserRelCreateCommandToDeptTreeUserRelMapping{
		DeptTreeUserRelCreateCommandToDeptTreeUserRelMapping instance = Mappers.getMapper( DeptTreeUserRelCreateCommandToDeptTreeUserRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param deptTreeUserRel
		 * @param deptTreeUserRelCreateCommand
		 */
		void fillDeptTreeUserRelByDeptTreeUserRelCreateCommand(@MappingTarget DeptTreeUserRel deptTreeUserRel, DeptTreeUserRelCreateCommand deptTreeUserRelCreateCommand);
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
