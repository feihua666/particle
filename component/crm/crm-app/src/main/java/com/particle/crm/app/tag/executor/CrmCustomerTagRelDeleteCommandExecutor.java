package com.particle.crm.app.tag.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.crm.app.tag.structmapping.CrmCustomerTagRelAppStructMapping;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;
import com.particle.crm.domain.tag.CrmCustomerTagRel;
import com.particle.crm.domain.tag.CrmCustomerTagRelId;
import com.particle.crm.domain.tag.gateway.CrmCustomerTagRelGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 客户标签关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Component
@Validated
public class CrmCustomerTagRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerTagRelGateway crmCustomerTagRelGateway;

	/**
	 * 执行 客户标签关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagRelVO> execute(@Valid IdCommand deleteCommand) {
		CrmCustomerTagRelId crmCustomerTagRelId = CrmCustomerTagRelId.of(deleteCommand.getId());
		CrmCustomerTagRel byId = crmCustomerTagRelGateway.getById(crmCustomerTagRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crmCustomerTagRelGateway.delete(crmCustomerTagRelId);
		if (delete) {
			return SingleResponse.of(CrmCustomerTagRelAppStructMapping.instance.toCrmCustomerTagRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerTagRelGateway
	 */
	@Autowired
	public void setCrmCustomerTagRelGateway(CrmCustomerTagRelGateway crmCustomerTagRelGateway) {
		this.crmCustomerTagRelGateway = crmCustomerTagRelGateway;
	}
}
