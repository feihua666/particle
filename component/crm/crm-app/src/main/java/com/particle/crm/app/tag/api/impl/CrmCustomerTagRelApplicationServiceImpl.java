package com.particle.crm.app.tag.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.tag.executor.CrmCustomerTagRelCreateCommandExecutor;
import com.particle.crm.app.tag.executor.CrmCustomerTagRelDeleteCommandExecutor;
import com.particle.crm.app.tag.executor.CrmCustomerTagRelUpdateCommandExecutor;
import com.particle.crm.client.tag.api.ICrmCustomerTagRelApplicationService;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagRelCreateCommand;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagRelUpdateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 客户标签关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Transactional
@Service
@CatchAndLog
public class CrmCustomerTagRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerTagRelApplicationService {

	private CrmCustomerTagRelCreateCommandExecutor crmCustomerTagRelCreateCommandExecutor;

	private CrmCustomerTagRelDeleteCommandExecutor crmCustomerTagRelDeleteCommandExecutor;

	private CrmCustomerTagRelUpdateCommandExecutor crmCustomerTagRelUpdateCommandExecutor;


	@Override
	public SingleResponse<CrmCustomerTagRelVO> create(CrmCustomerTagRelCreateCommand crmCustomerTagRelCreateCommand) {
		return crmCustomerTagRelCreateCommandExecutor.execute(crmCustomerTagRelCreateCommand);
	}

	@Override
	public SingleResponse<CrmCustomerTagRelVO> delete(IdCommand deleteCommand) {
		return crmCustomerTagRelDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<CrmCustomerTagRelVO> update(CrmCustomerTagRelUpdateCommand crmCustomerTagRelUpdateCommand) {
		return crmCustomerTagRelUpdateCommandExecutor.execute(crmCustomerTagRelUpdateCommand);
	}

	@Autowired
	public void setCrmCustomerTagRelCreateCommandExecutor(CrmCustomerTagRelCreateCommandExecutor crmCustomerTagRelCreateCommandExecutor) {
		this.crmCustomerTagRelCreateCommandExecutor = crmCustomerTagRelCreateCommandExecutor;
	}

	@Autowired
	public void setCrmCustomerTagRelDeleteCommandExecutor(CrmCustomerTagRelDeleteCommandExecutor crmCustomerTagRelDeleteCommandExecutor) {
		this.crmCustomerTagRelDeleteCommandExecutor = crmCustomerTagRelDeleteCommandExecutor;
	}
	@Autowired
	public void setCrmCustomerTagRelUpdateCommandExecutor(CrmCustomerTagRelUpdateCommandExecutor crmCustomerTagRelUpdateCommandExecutor) {
		this.crmCustomerTagRelUpdateCommandExecutor = crmCustomerTagRelUpdateCommandExecutor;
	}

}
