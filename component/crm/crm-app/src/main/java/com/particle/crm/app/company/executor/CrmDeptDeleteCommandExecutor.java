package com.particle.crm.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.crm.app.company.structmapping.CrmDeptAppStructMapping;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
import com.particle.crm.domain.company.CrmDept;
import com.particle.crm.domain.company.CrmDeptId;
import com.particle.crm.domain.company.gateway.CrmDeptGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 客户公司部门 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Component
@Validated
public class CrmDeptDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrmDeptGateway crmDeptGateway;

	/**
	 * 执行 客户公司部门 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrmDeptVO> execute(@Valid IdCommand deleteCommand) {
		CrmDeptId crmDeptId = CrmDeptId.of(deleteCommand.getId());
		CrmDept byId = crmDeptGateway.getById(crmDeptId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crmDeptGateway.delete(crmDeptId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrmDeptAppStructMapping.instance.toCrmDeptVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param crmDeptGateway
	 */
	@Autowired
	public void setCrmDeptGateway(CrmDeptGateway crmDeptGateway) {
		this.crmDeptGateway = crmDeptGateway;
	}
}
