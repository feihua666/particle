package com.particle.dept.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.structmapping.DeptTreeNameAppStructMapping;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
import com.particle.dept.domain.DeptTreeName;
import com.particle.dept.domain.DeptTreeNameId;
import com.particle.dept.domain.gateway.DeptTreeNameGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class DeptTreeNameDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeNameGateway deptTreeNameGateway;

	/**
	 * 执行 部门树名称 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DeptTreeNameVO> execute(@Valid IdCommand deleteCommand) {
		DeptTreeNameId deptTreeNameId = DeptTreeNameId.of(deleteCommand.getId());
		DeptTreeName byId = deptTreeNameGateway.getById(deptTreeNameId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = deptTreeNameGateway.delete(deptTreeNameId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DeptTreeNameAppStructMapping.instance.toDeptTreeNameVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
