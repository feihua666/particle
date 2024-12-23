package com.particle.dept.app.deptuserrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.deptuserrel.structmapping.DeptUserRelAppStructMapping;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.dept.domain.deptuserrel.DeptUserRel;
import com.particle.dept.domain.deptuserrel.DeptUserRelId;
import com.particle.dept.domain.deptuserrel.gateway.DeptUserRelGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 部门用户关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Component
@Validated
public class DeptUserRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DeptUserRelGateway deptUserRelGateway;

	/**
	 * 执行 部门用户关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DeptUserRelVO> execute(@Valid IdCommand deleteCommand) {
		DeptUserRelId deptUserRelId = DeptUserRelId.of(deleteCommand.getId());
		DeptUserRel byId = deptUserRelGateway.getById(deptUserRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = deptUserRelGateway.delete(deptUserRelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DeptUserRelAppStructMapping.instance.toDeptUserRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
