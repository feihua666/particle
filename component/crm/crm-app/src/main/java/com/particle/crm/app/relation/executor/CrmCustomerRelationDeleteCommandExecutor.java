package com.particle.crm.app.relation.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.crm.app.relation.structmapping.CrmCustomerRelationAppStructMapping;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import com.particle.crm.domain.relation.CrmCustomerRelation;
import com.particle.crm.domain.relation.CrmCustomerRelationId;
import com.particle.crm.domain.relation.gateway.CrmCustomerRelationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 客户与客户关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Component
@Validated
public class CrmCustomerRelationDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerRelationGateway crmCustomerRelationGateway;

	/**
	 * 执行 客户与客户关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationVO> execute(@Valid IdCommand deleteCommand) {
		CrmCustomerRelationId crmCustomerRelationId = CrmCustomerRelationId.of(deleteCommand.getId());
		CrmCustomerRelation byId = crmCustomerRelationGateway.getById(crmCustomerRelationId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crmCustomerRelationGateway.delete(crmCustomerRelationId);
		if (delete) {
			return SingleResponse.of(CrmCustomerRelationAppStructMapping.instance.toCrmCustomerRelationVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerRelationGateway
	 */
	@Autowired
	public void setCrmCustomerRelationGateway(CrmCustomerRelationGateway crmCustomerRelationGateway) {
		this.crmCustomerRelationGateway = crmCustomerRelationGateway;
	}
}
