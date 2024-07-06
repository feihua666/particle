package com.particle.dept.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dept.app.structmapping.DeptTreeAppStructMapping;
import com.particle.dept.client.dto.data.DeptTreeVO;
import com.particle.dept.domain.DeptTree;
import com.particle.dept.domain.DeptTreeId;
import com.particle.dept.domain.gateway.DeptTreeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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
public class DeptTreeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeGateway deptTreeGateway;

	/**
	 * 执行 部门树 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DeptTreeVO> execute(@Valid IdCommand deleteCommand) {
		DeptTreeId deptTreeId = DeptTreeId.of(deleteCommand.getId());
		DeptTree byId = deptTreeGateway.getById(deptTreeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = deptTreeGateway.delete(deptTreeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DeptTreeAppStructMapping.instance.toDeptTreeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
