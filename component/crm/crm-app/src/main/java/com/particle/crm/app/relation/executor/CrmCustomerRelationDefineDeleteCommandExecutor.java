package com.particle.crm.app.relation.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.crm.app.relation.structmapping.CrmCustomerRelationDefineAppStructMapping;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
import com.particle.crm.domain.relation.CrmCustomerRelationDefine;
import com.particle.crm.domain.relation.CrmCustomerRelationDefineId;
import com.particle.crm.domain.relation.gateway.CrmCustomerRelationDefineGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 客户关系定义 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Component
@Validated
public class CrmCustomerRelationDefineDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerRelationDefineGateway crmCustomerRelationDefineGateway;

	/**
	 * 执行 客户关系定义 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationDefineVO> execute(@Valid IdCommand deleteCommand) {
		CrmCustomerRelationDefineId crmCustomerRelationDefineId = CrmCustomerRelationDefineId.of(deleteCommand.getId());
		CrmCustomerRelationDefine byId = crmCustomerRelationDefineGateway.getById(crmCustomerRelationDefineId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = crmCustomerRelationDefineGateway.delete(crmCustomerRelationDefineId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CrmCustomerRelationDefineAppStructMapping.instance.toCrmCustomerRelationDefineVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerRelationDefineGateway
	 */
	@Autowired
	public void setCrmCustomerRelationDefineGateway(CrmCustomerRelationDefineGateway crmCustomerRelationDefineGateway) {
		this.crmCustomerRelationDefineGateway = crmCustomerRelationDefineGateway;
	}
}
