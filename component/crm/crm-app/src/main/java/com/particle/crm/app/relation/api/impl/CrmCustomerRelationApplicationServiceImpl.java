package com.particle.crm.app.relation.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.relation.executor.CrmCustomerRelationCreateCommandExecutor;
import com.particle.crm.app.relation.executor.CrmCustomerRelationDeleteCommandExecutor;
import com.particle.crm.app.relation.executor.CrmCustomerRelationUpdateCommandExecutor;
import com.particle.crm.client.relation.api.ICrmCustomerRelationApplicationService;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationCreateCommand;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationUpdateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 客户与客户关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Transactional
@Service
@CatchAndLog
public class CrmCustomerRelationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerRelationApplicationService {

	private CrmCustomerRelationCreateCommandExecutor crmCustomerRelationCreateCommandExecutor;

	private CrmCustomerRelationDeleteCommandExecutor crmCustomerRelationDeleteCommandExecutor;

	private CrmCustomerRelationUpdateCommandExecutor crmCustomerRelationUpdateCommandExecutor;


	@Override
	public SingleResponse<CrmCustomerRelationVO> create(CrmCustomerRelationCreateCommand crmCustomerRelationCreateCommand) {
		return crmCustomerRelationCreateCommandExecutor.execute(crmCustomerRelationCreateCommand);
	}

	@Override
	public SingleResponse<CrmCustomerRelationVO> delete(IdCommand deleteCommand) {
		return crmCustomerRelationDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<CrmCustomerRelationVO> update(CrmCustomerRelationUpdateCommand crmCustomerRelationUpdateCommand) {
		return crmCustomerRelationUpdateCommandExecutor.execute(crmCustomerRelationUpdateCommand);
	}

	@Autowired
	public void setCrmCustomerRelationCreateCommandExecutor(CrmCustomerRelationCreateCommandExecutor crmCustomerRelationCreateCommandExecutor) {
		this.crmCustomerRelationCreateCommandExecutor = crmCustomerRelationCreateCommandExecutor;
	}

	@Autowired
	public void setCrmCustomerRelationDeleteCommandExecutor(CrmCustomerRelationDeleteCommandExecutor crmCustomerRelationDeleteCommandExecutor) {
		this.crmCustomerRelationDeleteCommandExecutor = crmCustomerRelationDeleteCommandExecutor;
	}
	@Autowired
	public void setCrmCustomerRelationUpdateCommandExecutor(CrmCustomerRelationUpdateCommandExecutor crmCustomerRelationUpdateCommandExecutor) {
		this.crmCustomerRelationUpdateCommandExecutor = crmCustomerRelationUpdateCommandExecutor;
	}

}
