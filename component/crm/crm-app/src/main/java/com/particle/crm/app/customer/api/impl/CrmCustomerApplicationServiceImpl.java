package com.particle.crm.app.customer.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.customer.executor.CrmCustomerCreateCommandExecutor;
import com.particle.crm.app.customer.executor.CrmCustomerDeleteCommandExecutor;
import com.particle.crm.app.customer.executor.CrmCustomerUpdateCommandExecutor;
import com.particle.crm.client.customer.api.ICrmCustomerApplicationService;
import com.particle.crm.client.customer.dto.command.CrmCustomerCreateCommand;
import com.particle.crm.client.customer.dto.command.CrmCustomerUpdateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 客户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Transactional
@Service
@CatchAndLog
public class CrmCustomerApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerApplicationService {

	private CrmCustomerCreateCommandExecutor crmCustomerCreateCommandExecutor;

	private CrmCustomerDeleteCommandExecutor crmCustomerDeleteCommandExecutor;

	private CrmCustomerUpdateCommandExecutor crmCustomerUpdateCommandExecutor;


	@Override
	public SingleResponse<CrmCustomerVO> create(CrmCustomerCreateCommand crmCustomerCreateCommand) {
		return crmCustomerCreateCommandExecutor.execute(crmCustomerCreateCommand);
	}

	@Override
	public SingleResponse<CrmCustomerVO> delete(IdCommand deleteCommand) {
		return crmCustomerDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<CrmCustomerVO> update(CrmCustomerUpdateCommand crmCustomerUpdateCommand) {
		return crmCustomerUpdateCommandExecutor.execute(crmCustomerUpdateCommand);
	}

	@Autowired
	public void setCrmCustomerCreateCommandExecutor(CrmCustomerCreateCommandExecutor crmCustomerCreateCommandExecutor) {
		this.crmCustomerCreateCommandExecutor = crmCustomerCreateCommandExecutor;
	}

	@Autowired
	public void setCrmCustomerDeleteCommandExecutor(CrmCustomerDeleteCommandExecutor crmCustomerDeleteCommandExecutor) {
		this.crmCustomerDeleteCommandExecutor = crmCustomerDeleteCommandExecutor;
	}
	@Autowired
	public void setCrmCustomerUpdateCommandExecutor(CrmCustomerUpdateCommandExecutor crmCustomerUpdateCommandExecutor) {
		this.crmCustomerUpdateCommandExecutor = crmCustomerUpdateCommandExecutor;
	}

}
