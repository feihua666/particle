package com.particle.crm.app.customer.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.crm.app.customer.structmapping.CrmCustomerContactAppStructMapping;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.crm.domain.customer.CrmCustomerContact;
import com.particle.crm.domain.customer.CrmCustomerContactId;
import com.particle.crm.domain.customer.gateway.CrmCustomerContactGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 客户联系方式 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Component
@Validated
public class CrmCustomerContactDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerContactGateway crmCustomerContactGateway;

	/**
	 * 执行 客户联系方式 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerContactVO> execute(@Valid IdCommand deleteCommand) {
		CrmCustomerContactId crmCustomerContactId = CrmCustomerContactId.of(deleteCommand.getId());
		CrmCustomerContact byId = crmCustomerContactGateway.getById(crmCustomerContactId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crmCustomerContactGateway.delete(crmCustomerContactId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrmCustomerContactAppStructMapping.instance.toCrmCustomerContactVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerContactGateway
	 */
	@Autowired
	public void setCrmCustomerContactGateway(CrmCustomerContactGateway crmCustomerContactGateway) {
		this.crmCustomerContactGateway = crmCustomerContactGateway;
	}
}
