package com.particle.dept.app.api.impl;

import com.particle.dept.app.executor.DeptTreeNameCreateCommandExecutor;
import com.particle.dept.app.executor.DeptTreeNameDeleteCommandExecutor;
import com.particle.dept.app.executor.DeptTreeNameUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.dto.command.DeptTreeNameUpdateCommand;
import com.particle.dept.client.api.IDeptTreeNameApplicationService;
import com.particle.dept.client.dto.command.DeptTreeNameCreateCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
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
 * 部门树名称 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Transactional
@Service
@CatchAndLog
public class DeptTreeNameApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptTreeNameApplicationService {

	private DeptTreeNameCreateCommandExecutor deptTreeNameCreateCommandExecutor;

	private DeptTreeNameDeleteCommandExecutor deptTreeNameDeleteCommandExecutor;

	private DeptTreeNameUpdateCommandExecutor deptTreeNameUpdateCommandExecutor;


	@Override
	public SingleResponse<DeptTreeNameVO> create(DeptTreeNameCreateCommand deptTreeNameCreateCommand) {
		return deptTreeNameCreateCommandExecutor.execute(deptTreeNameCreateCommand);
	}

	@Override
	public SingleResponse<DeptTreeNameVO> delete(IdCommand deleteCommand) {
		return deptTreeNameDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DeptTreeNameVO> update(DeptTreeNameUpdateCommand deptTreeNameUpdateCommand) {
		return deptTreeNameUpdateCommandExecutor.execute(deptTreeNameUpdateCommand);
	}

	@Autowired
	public void setDeptTreeNameCreateCommandExecutor(DeptTreeNameCreateCommandExecutor deptTreeNameCreateCommandExecutor) {
		this.deptTreeNameCreateCommandExecutor = deptTreeNameCreateCommandExecutor;
	}

	@Autowired
	public void setDeptTreeNameDeleteCommandExecutor(DeptTreeNameDeleteCommandExecutor deptTreeNameDeleteCommandExecutor) {
		this.deptTreeNameDeleteCommandExecutor = deptTreeNameDeleteCommandExecutor;
	}
	@Autowired
	public void setDeptTreeNameUpdateCommandExecutor(DeptTreeNameUpdateCommandExecutor deptTreeNameUpdateCommandExecutor) {
		this.deptTreeNameUpdateCommandExecutor = deptTreeNameUpdateCommandExecutor;
	}

}
