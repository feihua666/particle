package com.particle.crm.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.crm.app.company.structmapping.CrmCompanyAppStructMapping;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.crm.domain.company.CrmCompany;
import com.particle.crm.domain.company.CrmCompanyId;
import com.particle.crm.domain.company.gateway.CrmCompanyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 客户公司 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Component
@Validated
public class CrmCompanyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrmCompanyGateway crmCompanyGateway;

	/**
	 * 执行 客户公司 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrmCompanyVO> execute(@Valid IdCommand deleteCommand) {
		CrmCompanyId crmCompanyId = CrmCompanyId.of(deleteCommand.getId());
		CrmCompany byId = crmCompanyGateway.getById(crmCompanyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crmCompanyGateway.delete(crmCompanyId);
		if (delete) {
			return SingleResponse.of(CrmCompanyAppStructMapping.instance.toCrmCompanyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param crmCompanyGateway
	 */
	@Autowired
	public void setCrmCompanyGateway(CrmCompanyGateway crmCompanyGateway) {
		this.crmCompanyGateway = crmCompanyGateway;
	}
}
