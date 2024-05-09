package com.particle.crm.app.customer.api.impl;

import com.particle.crm.app.customer.executor.CrmCustomerContactCreateCommandExecutor;
import com.particle.crm.app.customer.executor.CrmCustomerContactDeleteCommandExecutor;
import com.particle.crm.app.customer.executor.CrmCustomerContactUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.customer.dto.command.CrmCustomerContactUpdateCommand;
import com.particle.crm.client.customer.api.ICrmCustomerContactApplicationService;
import com.particle.crm.client.customer.dto.command.CrmCustomerContactCreateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
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
 * 客户联系方式 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Transactional
@Service
@CatchAndLog
public class CrmCustomerContactApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerContactApplicationService {

	private CrmCustomerContactCreateCommandExecutor crmCustomerContactCreateCommandExecutor;

	private CrmCustomerContactDeleteCommandExecutor crmCustomerContactDeleteCommandExecutor;

	private CrmCustomerContactUpdateCommandExecutor crmCustomerContactUpdateCommandExecutor;


	@Override
	public SingleResponse<CrmCustomerContactVO> create(CrmCustomerContactCreateCommand crmCustomerContactCreateCommand) {
		return crmCustomerContactCreateCommandExecutor.execute(crmCustomerContactCreateCommand);
	}

	@Override
	public SingleResponse<CrmCustomerContactVO> delete(IdCommand deleteCommand) {
		return crmCustomerContactDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<CrmCustomerContactVO> update(CrmCustomerContactUpdateCommand crmCustomerContactUpdateCommand) {
		return crmCustomerContactUpdateCommandExecutor.execute(crmCustomerContactUpdateCommand);
	}

	@Autowired
	public void setCrmCustomerContactCreateCommandExecutor(CrmCustomerContactCreateCommandExecutor crmCustomerContactCreateCommandExecutor) {
		this.crmCustomerContactCreateCommandExecutor = crmCustomerContactCreateCommandExecutor;
	}

	@Autowired
	public void setCrmCustomerContactDeleteCommandExecutor(CrmCustomerContactDeleteCommandExecutor crmCustomerContactDeleteCommandExecutor) {
		this.crmCustomerContactDeleteCommandExecutor = crmCustomerContactDeleteCommandExecutor;
	}
	@Autowired
	public void setCrmCustomerContactUpdateCommandExecutor(CrmCustomerContactUpdateCommandExecutor crmCustomerContactUpdateCommandExecutor) {
		this.crmCustomerContactUpdateCommandExecutor = crmCustomerContactUpdateCommandExecutor;
	}

}
