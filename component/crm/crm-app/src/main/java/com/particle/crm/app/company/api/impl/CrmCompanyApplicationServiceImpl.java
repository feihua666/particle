package com.particle.crm.app.company.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.company.executor.CrmCompanyCreateCommandExecutor;
import com.particle.crm.app.company.executor.CrmCompanyDeleteCommandExecutor;
import com.particle.crm.app.company.executor.CrmCompanyUpdateCommandExecutor;
import com.particle.crm.client.company.api.ICrmCompanyApplicationService;
import com.particle.crm.client.company.dto.command.CrmCompanyCreateCommand;
import com.particle.crm.client.company.dto.command.CrmCompanyUpdateCommand;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 客户公司 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Transactional
@Service
@CatchAndLog
public class CrmCompanyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCompanyApplicationService {

	private CrmCompanyCreateCommandExecutor crmCompanyCreateCommandExecutor;

	private CrmCompanyDeleteCommandExecutor crmCompanyDeleteCommandExecutor;

	private CrmCompanyUpdateCommandExecutor crmCompanyUpdateCommandExecutor;


	@Override
	public SingleResponse<CrmCompanyVO> create(CrmCompanyCreateCommand crmCompanyCreateCommand) {
		return crmCompanyCreateCommandExecutor.execute(crmCompanyCreateCommand);
	}

	@Override
	public SingleResponse<CrmCompanyVO> delete(IdCommand deleteCommand) {
		return crmCompanyDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<CrmCompanyVO> update(CrmCompanyUpdateCommand crmCompanyUpdateCommand) {
		return crmCompanyUpdateCommandExecutor.execute(crmCompanyUpdateCommand);
	}

	@Autowired
	public void setCrmCompanyCreateCommandExecutor(CrmCompanyCreateCommandExecutor crmCompanyCreateCommandExecutor) {
		this.crmCompanyCreateCommandExecutor = crmCompanyCreateCommandExecutor;
	}

	@Autowired
	public void setCrmCompanyDeleteCommandExecutor(CrmCompanyDeleteCommandExecutor crmCompanyDeleteCommandExecutor) {
		this.crmCompanyDeleteCommandExecutor = crmCompanyDeleteCommandExecutor;
	}
	@Autowired
	public void setCrmCompanyUpdateCommandExecutor(CrmCompanyUpdateCommandExecutor crmCompanyUpdateCommandExecutor) {
		this.crmCompanyUpdateCommandExecutor = crmCompanyUpdateCommandExecutor;
	}

}
