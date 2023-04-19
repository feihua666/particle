package com.particle.dept.app.executor;

import com.particle.dept.app.structmapping.DeptTreeNameAppStructMapping;
import com.particle.dept.client.dto.command.DeptTreeNameUpdateCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
import com.particle.dept.domain.DeptTreeName;
import com.particle.dept.domain.DeptTreeNameId;
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
 * 部门树名称 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DeptTreeNameUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeNameGateway deptTreeNameGateway;

	/**
	 * 执行 部门树名称 更新指令
	 * @param deptTreeNameUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeNameVO> execute(@Valid DeptTreeNameUpdateCommand deptTreeNameUpdateCommand) {
		DeptTreeName deptTreeName = createByDeptTreeNameUpdateCommand(deptTreeNameUpdateCommand);
		deptTreeName.setUpdateControl(deptTreeNameUpdateCommand);
		boolean save = deptTreeNameGateway.save(deptTreeName);
		if (save) {
			return SingleResponse.of(DeptTreeNameAppStructMapping.instance.toDeptTreeNameVO(deptTreeName));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param deptTreeNameUpdateCommand
	 * @return
	 */
	private DeptTreeName createByDeptTreeNameUpdateCommand(DeptTreeNameUpdateCommand deptTreeNameUpdateCommand){
		DeptTreeName deptTreeName = DeptTreeName.create();
		DeptTreeNameUpdateCommandToDeptTreeNameMapping.instance.fillDeptTreeNameByDeptTreeNameUpdateCommand(deptTreeName, deptTreeNameUpdateCommand);
		return deptTreeName;
	}

	@Mapper
	interface DeptTreeNameUpdateCommandToDeptTreeNameMapping{
		DeptTreeNameUpdateCommandToDeptTreeNameMapping instance = Mappers.getMapper(DeptTreeNameUpdateCommandToDeptTreeNameMapping.class );

		default DeptTreeNameId map(Long id){
			if (id == null) {
				return null;
			}
			return DeptTreeNameId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param deptTreeName
		 * @param deptTreeNameUpdateCommand
		 */
		void fillDeptTreeNameByDeptTreeNameUpdateCommand(@MappingTarget DeptTreeName deptTreeName, DeptTreeNameUpdateCommand deptTreeNameUpdateCommand);
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
