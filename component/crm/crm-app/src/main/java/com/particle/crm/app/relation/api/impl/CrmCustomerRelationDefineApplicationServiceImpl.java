package com.particle.crm.app.relation.api.impl;

import com.particle.crm.app.relation.executor.CrmCustomerRelationDefineCreateCommandExecutor;
import com.particle.crm.app.relation.executor.CrmCustomerRelationDefineDeleteCommandExecutor;
import com.particle.crm.app.relation.executor.CrmCustomerRelationDefineUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationDefineUpdateCommand;
import com.particle.crm.client.relation.api.ICrmCustomerRelationDefineApplicationService;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationDefineCreateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 客户关系定义 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Transactional
@Service
@CatchAndLog
public class CrmCustomerRelationDefineApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerRelationDefineApplicationService {

	private CrmCustomerRelationDefineCreateCommandExecutor crmCustomerRelationDefineCreateCommandExecutor;

	private CrmCustomerRelationDefineDeleteCommandExecutor crmCustomerRelationDefineDeleteCommandExecutor;

	private CrmCustomerRelationDefineUpdateCommandExecutor crmCustomerRelationDefineUpdateCommandExecutor;


	@Override
	public SingleResponse<CrmCustomerRelationDefineVO> create(CrmCustomerRelationDefineCreateCommand crmCustomerRelationDefineCreateCommand) {
		return crmCustomerRelationDefineCreateCommandExecutor.execute(crmCustomerRelationDefineCreateCommand);
	}

	@Override
	public SingleResponse<CrmCustomerRelationDefineVO> delete(IdCommand deleteCommand) {
		return crmCustomerRelationDefineDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<CrmCustomerRelationDefineVO> update(CrmCustomerRelationDefineUpdateCommand crmCustomerRelationDefineUpdateCommand) {
		return crmCustomerRelationDefineUpdateCommandExecutor.execute(crmCustomerRelationDefineUpdateCommand);
	}

	@Autowired
	public void setCrmCustomerRelationDefineCreateCommandExecutor(CrmCustomerRelationDefineCreateCommandExecutor crmCustomerRelationDefineCreateCommandExecutor) {
		this.crmCustomerRelationDefineCreateCommandExecutor = crmCustomerRelationDefineCreateCommandExecutor;
	}

	@Autowired
	public void setCrmCustomerRelationDefineDeleteCommandExecutor(CrmCustomerRelationDefineDeleteCommandExecutor crmCustomerRelationDefineDeleteCommandExecutor) {
		this.crmCustomerRelationDefineDeleteCommandExecutor = crmCustomerRelationDefineDeleteCommandExecutor;
	}
	@Autowired
	public void setCrmCustomerRelationDefineUpdateCommandExecutor(CrmCustomerRelationDefineUpdateCommandExecutor crmCustomerRelationDefineUpdateCommandExecutor) {
		this.crmCustomerRelationDefineUpdateCommandExecutor = crmCustomerRelationDefineUpdateCommandExecutor;
	}

}
