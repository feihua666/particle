package com.particle.dept.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.executor.DeptCreateCommandExecutor;
import com.particle.dept.app.executor.DeptDeleteCommandExecutor;
import com.particle.dept.app.executor.DeptUpdateCommandExecutor;
import com.particle.dept.client.api.IDeptApplicationService;
import com.particle.dept.client.dto.command.DeptCreateCommand;
import com.particle.dept.client.dto.command.DeptUpdateCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 部门 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Transactional
@Service
@CatchAndLog
public class DeptApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptApplicationService {

	private DeptCreateCommandExecutor deptCreateCommandExecutor;

	private DeptDeleteCommandExecutor deptDeleteCommandExecutor;

	private DeptUpdateCommandExecutor deptUpdateCommandExecutor;


	@Override
	public SingleResponse<DeptVO> create(DeptCreateCommand deptCreateCommand) {
		return deptCreateCommandExecutor.execute(deptCreateCommand);
	}

	@Override
	public SingleResponse<DeptVO> delete(IdCommand deleteCommand) {
		return deptDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DeptVO> update(DeptUpdateCommand deptUpdateCommand) {
		return deptUpdateCommandExecutor.execute(deptUpdateCommand);
	}

	@Autowired
	public void setDeptCreateCommandExecutor(DeptCreateCommandExecutor deptCreateCommandExecutor) {
		this.deptCreateCommandExecutor = deptCreateCommandExecutor;
	}

	@Autowired
	public void setDeptDeleteCommandExecutor(DeptDeleteCommandExecutor deptDeleteCommandExecutor) {
		this.deptDeleteCommandExecutor = deptDeleteCommandExecutor;
	}
	@Autowired
	public void setDeptUpdateCommandExecutor(DeptUpdateCommandExecutor deptUpdateCommandExecutor) {
		this.deptUpdateCommandExecutor = deptUpdateCommandExecutor;
	}

}
