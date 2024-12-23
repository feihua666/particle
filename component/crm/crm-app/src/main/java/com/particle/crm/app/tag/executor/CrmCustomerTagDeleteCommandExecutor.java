package com.particle.crm.app.tag.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.tag.structmapping.CrmCustomerTagAppStructMapping;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;
import com.particle.crm.domain.tag.CrmCustomerTag;
import com.particle.crm.domain.tag.CrmCustomerTagId;
import com.particle.crm.domain.tag.gateway.CrmCustomerTagGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 客户标签 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Component
@Validated
public class CrmCustomerTagDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerTagGateway crmCustomerTagGateway;

	/**
	 * 执行 客户标签 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagVO> execute(@Valid IdCommand deleteCommand) {
		CrmCustomerTagId crmCustomerTagId = CrmCustomerTagId.of(deleteCommand.getId());
		CrmCustomerTag byId = crmCustomerTagGateway.getById(crmCustomerTagId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crmCustomerTagGateway.delete(crmCustomerTagId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrmCustomerTagAppStructMapping.instance.toCrmCustomerTagVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerTagGateway
	 */
	@Autowired
	public void setCrmCustomerTagGateway(CrmCustomerTagGateway crmCustomerTagGateway) {
		this.crmCustomerTagGateway = crmCustomerTagGateway;
	}
}
