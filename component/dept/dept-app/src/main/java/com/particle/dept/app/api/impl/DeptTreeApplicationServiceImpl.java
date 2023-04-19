package com.particle.dept.app.api.impl;

import com.particle.dept.app.executor.DeptTreeCreateCommandExecutor;
import com.particle.dept.app.executor.DeptTreeDeleteCommandExecutor;
import com.particle.dept.app.executor.DeptTreeUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.dto.command.DeptTreeUpdateCommand;
import com.particle.dept.client.api.IDeptTreeApplicationService;
import com.particle.dept.client.dto.command.DeptTreeCreateCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;
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
 * 部门树 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Transactional
@Service
@CatchAndLog
public class DeptTreeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptTreeApplicationService {

	private DeptTreeCreateCommandExecutor deptTreeCreateCommandExecutor;

	private DeptTreeDeleteCommandExecutor deptTreeDeleteCommandExecutor;

	private DeptTreeUpdateCommandExecutor deptTreeUpdateCommandExecutor;


	@Override
	public SingleResponse<DeptTreeVO> create(DeptTreeCreateCommand deptTreeCreateCommand) {
		return deptTreeCreateCommandExecutor.execute(deptTreeCreateCommand);
	}

	@Override
	public SingleResponse<DeptTreeVO> delete(IdCommand deleteCommand) {
		return deptTreeDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DeptTreeVO> update(DeptTreeUpdateCommand deptTreeUpdateCommand) {
		return deptTreeUpdateCommandExecutor.execute(deptTreeUpdateCommand);
	}

	@Autowired
	public void setDeptTreeCreateCommandExecutor(DeptTreeCreateCommandExecutor deptTreeCreateCommandExecutor) {
		this.deptTreeCreateCommandExecutor = deptTreeCreateCommandExecutor;
	}

	@Autowired
	public void setDeptTreeDeleteCommandExecutor(DeptTreeDeleteCommandExecutor deptTreeDeleteCommandExecutor) {
		this.deptTreeDeleteCommandExecutor = deptTreeDeleteCommandExecutor;
	}
	@Autowired
	public void setDeptTreeUpdateCommandExecutor(DeptTreeUpdateCommandExecutor deptTreeUpdateCommandExecutor) {
		this.deptTreeUpdateCommandExecutor = deptTreeUpdateCommandExecutor;
	}

}
