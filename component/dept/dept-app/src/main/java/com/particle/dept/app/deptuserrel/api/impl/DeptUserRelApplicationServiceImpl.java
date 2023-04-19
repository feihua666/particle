package com.particle.dept.app.deptuserrel.api.impl;

import com.particle.dept.app.deptuserrel.executor.DeptUserRelCreateCommandExecutor;
import com.particle.dept.app.deptuserrel.executor.DeptUserRelDeleteCommandExecutor;
import com.particle.dept.app.deptuserrel.executor.DeptUserRelUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelUpdateCommand;
import com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelCreateCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
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
 * 部门用户关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:09
 */
@Transactional
@Service
@CatchAndLog
public class DeptUserRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptUserRelApplicationService {

	private DeptUserRelCreateCommandExecutor deptUserRelCreateCommandExecutor;

	private DeptUserRelDeleteCommandExecutor deptUserRelDeleteCommandExecutor;

	private DeptUserRelUpdateCommandExecutor deptUserRelUpdateCommandExecutor;


	@Override
	public SingleResponse<DeptUserRelVO> create(DeptUserRelCreateCommand deptUserRelCreateCommand) {
		return deptUserRelCreateCommandExecutor.execute(deptUserRelCreateCommand);
	}

	@Override
	public SingleResponse<DeptUserRelVO> delete(IdCommand deleteCommand) {
		return deptUserRelDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DeptUserRelVO> update(DeptUserRelUpdateCommand deptUserRelUpdateCommand) {
		return deptUserRelUpdateCommandExecutor.execute(deptUserRelUpdateCommand);
	}

	@Autowired
	public void setDeptUserRelCreateCommandExecutor(DeptUserRelCreateCommandExecutor deptUserRelCreateCommandExecutor) {
		this.deptUserRelCreateCommandExecutor = deptUserRelCreateCommandExecutor;
	}

	@Autowired
	public void setDeptUserRelDeleteCommandExecutor(DeptUserRelDeleteCommandExecutor deptUserRelDeleteCommandExecutor) {
		this.deptUserRelDeleteCommandExecutor = deptUserRelDeleteCommandExecutor;
	}
	@Autowired
	public void setDeptUserRelUpdateCommandExecutor(DeptUserRelUpdateCommandExecutor deptUserRelUpdateCommandExecutor) {
		this.deptUserRelUpdateCommandExecutor = deptUserRelUpdateCommandExecutor;
	}

}
