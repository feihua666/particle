package com.particle.dept.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dept.app.structmapping.DeptAppStructMapping;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.dept.domain.Dept;
import com.particle.dept.domain.DeptId;
import com.particle.dept.domain.gateway.DeptGateway;
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
public class DeptDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DeptGateway deptGateway;

	/**
	 * 执行 部门 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DeptVO> execute(@Valid IdCommand deleteCommand) {
		DeptId deptId = DeptId.of(deleteCommand.getId());
		Dept byId = deptGateway.getById(deptId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = deptGateway.delete(deptId);
		if (delete) {
			return SingleResponse.of(DeptAppStructMapping.instance.toDeptVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
