package com.particle.crm.app.tag.api.impl;

import com.particle.crm.app.tag.executor.CrmCustomerTagCreateCommandExecutor;
import com.particle.crm.app.tag.executor.CrmCustomerTagDeleteCommandExecutor;
import com.particle.crm.app.tag.executor.CrmCustomerTagUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagUpdateCommand;
import com.particle.crm.client.tag.api.ICrmCustomerTagApplicationService;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagCreateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;
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
 * 客户标签 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Transactional
@Service
@CatchAndLog
public class CrmCustomerTagApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerTagApplicationService {

	private CrmCustomerTagCreateCommandExecutor crmCustomerTagCreateCommandExecutor;

	private CrmCustomerTagDeleteCommandExecutor crmCustomerTagDeleteCommandExecutor;

	private CrmCustomerTagUpdateCommandExecutor crmCustomerTagUpdateCommandExecutor;


	@Override
	public SingleResponse<CrmCustomerTagVO> create(CrmCustomerTagCreateCommand crmCustomerTagCreateCommand) {
		return crmCustomerTagCreateCommandExecutor.execute(crmCustomerTagCreateCommand);
	}

	@Override
	public SingleResponse<CrmCustomerTagVO> delete(IdCommand deleteCommand) {
		return crmCustomerTagDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<CrmCustomerTagVO> update(CrmCustomerTagUpdateCommand crmCustomerTagUpdateCommand) {
		return crmCustomerTagUpdateCommandExecutor.execute(crmCustomerTagUpdateCommand);
	}

	@Autowired
	public void setCrmCustomerTagCreateCommandExecutor(CrmCustomerTagCreateCommandExecutor crmCustomerTagCreateCommandExecutor) {
		this.crmCustomerTagCreateCommandExecutor = crmCustomerTagCreateCommandExecutor;
	}

	@Autowired
	public void setCrmCustomerTagDeleteCommandExecutor(CrmCustomerTagDeleteCommandExecutor crmCustomerTagDeleteCommandExecutor) {
		this.crmCustomerTagDeleteCommandExecutor = crmCustomerTagDeleteCommandExecutor;
	}
	@Autowired
	public void setCrmCustomerTagUpdateCommandExecutor(CrmCustomerTagUpdateCommandExecutor crmCustomerTagUpdateCommandExecutor) {
		this.crmCustomerTagUpdateCommandExecutor = crmCustomerTagUpdateCommandExecutor;
	}

}
