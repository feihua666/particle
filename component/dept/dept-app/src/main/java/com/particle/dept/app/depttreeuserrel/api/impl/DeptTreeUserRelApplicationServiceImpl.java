package com.particle.dept.app.depttreeuserrel.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.depttreeuserrel.executor.DeptTreeUserRelCreateCommandExecutor;
import com.particle.dept.app.depttreeuserrel.executor.DeptTreeUserRelDeleteCommandExecutor;
import com.particle.dept.app.depttreeuserrel.executor.DeptTreeUserRelUpdateCommandExecutor;
import com.particle.dept.client.depttreeuserrel.api.IDeptTreeUserRelApplicationService;
import com.particle.dept.client.depttreeuserrel.dto.command.DeptTreeUserRelCreateCommand;
import com.particle.dept.client.depttreeuserrel.dto.command.DeptTreeUserRelUpdateCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 部门树用户关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Transactional
@Service
@CatchAndLog
public class DeptTreeUserRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptTreeUserRelApplicationService {

	private DeptTreeUserRelCreateCommandExecutor deptTreeUserRelCreateCommandExecutor;

	private DeptTreeUserRelDeleteCommandExecutor deptTreeUserRelDeleteCommandExecutor;

	private DeptTreeUserRelUpdateCommandExecutor deptTreeUserRelUpdateCommandExecutor;


	@Override
	public SingleResponse<DeptTreeUserRelVO> create(DeptTreeUserRelCreateCommand deptTreeUserRelCreateCommand) {
		return deptTreeUserRelCreateCommandExecutor.execute(deptTreeUserRelCreateCommand);
	}

	@Override
	public SingleResponse<DeptTreeUserRelVO> delete(IdCommand deleteCommand) {
		return deptTreeUserRelDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DeptTreeUserRelVO> update(DeptTreeUserRelUpdateCommand deptTreeUserRelUpdateCommand) {
		return deptTreeUserRelUpdateCommandExecutor.execute(deptTreeUserRelUpdateCommand);
	}

	@Autowired
	public void setDeptTreeUserRelCreateCommandExecutor(DeptTreeUserRelCreateCommandExecutor deptTreeUserRelCreateCommandExecutor) {
		this.deptTreeUserRelCreateCommandExecutor = deptTreeUserRelCreateCommandExecutor;
	}

	@Autowired
	public void setDeptTreeUserRelDeleteCommandExecutor(DeptTreeUserRelDeleteCommandExecutor deptTreeUserRelDeleteCommandExecutor) {
		this.deptTreeUserRelDeleteCommandExecutor = deptTreeUserRelDeleteCommandExecutor;
	}
	@Autowired
	public void setDeptTreeUserRelUpdateCommandExecutor(DeptTreeUserRelUpdateCommandExecutor deptTreeUserRelUpdateCommandExecutor) {
		this.deptTreeUserRelUpdateCommandExecutor = deptTreeUserRelUpdateCommandExecutor;
	}

}
