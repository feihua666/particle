package com.particle.dept.app.depttreeuserrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dept.app.depttreeuserrel.structmapping.DeptTreeUserRelAppStructMapping;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRel;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRelId;
import com.particle.dept.domain.depttreeuserrel.gateway.DeptTreeUserRelGateway;
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
public class DeptTreeUserRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DeptTreeUserRelGateway deptTreeUserRelGateway;

	/**
	 * 执行 部门树用户关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DeptTreeUserRelVO> execute(@Valid IdCommand deleteCommand) {
		DeptTreeUserRelId deptTreeUserRelId = DeptTreeUserRelId.of(deleteCommand.getId());
		DeptTreeUserRel byId = deptTreeUserRelGateway.getById(deptTreeUserRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = deptTreeUserRelGateway.delete(deptTreeUserRelId);
		if (delete) {
			return SingleResponse.of(DeptTreeUserRelAppStructMapping.instance.toDeptTreeUserRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
