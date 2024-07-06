package com.particle.crm.app.customer.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.crm.app.customer.structmapping.CrmCustomerAppStructMapping;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.crm.domain.customer.CrmCustomer;
import com.particle.crm.domain.customer.CrmCustomerId;
import com.particle.crm.domain.customer.gateway.CrmCustomerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 客户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Component
@Validated
public class CrmCustomerDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerGateway crmCustomerGateway;

	/**
	 * 执行 客户 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerVO> execute(@Valid IdCommand deleteCommand) {
		CrmCustomerId crmCustomerId = CrmCustomerId.of(deleteCommand.getId());
		CrmCustomer byId = crmCustomerGateway.getById(crmCustomerId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crmCustomerGateway.delete(crmCustomerId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrmCustomerAppStructMapping.instance.toCrmCustomerVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerGateway
	 */
	@Autowired
	public void setCrmCustomerGateway(CrmCustomerGateway crmCustomerGateway) {
		this.crmCustomerGateway = crmCustomerGateway;
	}
}
