package com.particle.crm.app.company.api.impl;

import com.particle.crm.app.company.executor.CrmDeptCreateCommandExecutor;
import com.particle.crm.app.company.executor.CrmDeptDeleteCommandExecutor;
import com.particle.crm.app.company.executor.CrmDeptUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.company.dto.command.CrmDeptUpdateCommand;
import com.particle.crm.client.company.api.ICrmDeptApplicationService;
import com.particle.crm.client.company.dto.command.CrmDeptCreateCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
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
 * 客户公司部门 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Transactional
@Service
@CatchAndLog
public class CrmDeptApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmDeptApplicationService {

	private CrmDeptCreateCommandExecutor crmDeptCreateCommandExecutor;

	private CrmDeptDeleteCommandExecutor crmDeptDeleteCommandExecutor;

	private CrmDeptUpdateCommandExecutor crmDeptUpdateCommandExecutor;


	@Override
	public SingleResponse<CrmDeptVO> create(CrmDeptCreateCommand crmDeptCreateCommand) {
		return crmDeptCreateCommandExecutor.execute(crmDeptCreateCommand);
	}

	@Override
	public SingleResponse<CrmDeptVO> delete(IdCommand deleteCommand) {
		return crmDeptDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<CrmDeptVO> update(CrmDeptUpdateCommand crmDeptUpdateCommand) {
		return crmDeptUpdateCommandExecutor.execute(crmDeptUpdateCommand);
	}

	@Autowired
	public void setCrmDeptCreateCommandExecutor(CrmDeptCreateCommandExecutor crmDeptCreateCommandExecutor) {
		this.crmDeptCreateCommandExecutor = crmDeptCreateCommandExecutor;
	}

	@Autowired
	public void setCrmDeptDeleteCommandExecutor(CrmDeptDeleteCommandExecutor crmDeptDeleteCommandExecutor) {
		this.crmDeptDeleteCommandExecutor = crmDeptDeleteCommandExecutor;
	}
	@Autowired
	public void setCrmDeptUpdateCommandExecutor(CrmDeptUpdateCommandExecutor crmDeptUpdateCommandExecutor) {
		this.crmDeptUpdateCommandExecutor = crmDeptUpdateCommandExecutor;
	}

}
