package com.particle.test.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.test.app.executor.TestCreateCommandExecutor;
import com.particle.test.app.executor.TestDeleteCommandExecutor;
import com.particle.test.app.executor.TestUpdateCommandExecutor;
import com.particle.test.client.api.ITestApplicationService;
import com.particle.test.client.dto.command.TestCreateCommand;
import com.particle.test.client.dto.command.TestUpdateCommand;
import com.particle.test.client.dto.data.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 测试 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Transactional
@Service
@CatchAndLog
public class TestApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITestApplicationService {

	private TestCreateCommandExecutor testCreateCommandExecutor;

	private TestDeleteCommandExecutor testDeleteCommandExecutor;

	private TestUpdateCommandExecutor testUpdateCommandExecutor;
	
	
	@Override
	public SingleResponse<TestVO> create(TestCreateCommand testCreateCommand) {
		return testCreateCommandExecutor.execute(testCreateCommand);
	}
	
	@Override
	public SingleResponse<TestVO> delete(IdCommand testDeleteCommand) {
		return testDeleteCommandExecutor.execute(testDeleteCommand);
	}
	
	@Override
	public SingleResponse<TestVO> update(TestUpdateCommand testUpdateCommand) {
		return testUpdateCommandExecutor.execute(testUpdateCommand);
	}

	@Autowired
	public void setTestCreateCommandExecutor(TestCreateCommandExecutor testCreateCommandExecutor) {
		this.testCreateCommandExecutor = testCreateCommandExecutor;
	}

	@Autowired
	public void setTestDeleteCommandExecutor(TestDeleteCommandExecutor testDeleteCommandExecutor) {
		this.testDeleteCommandExecutor = testDeleteCommandExecutor;
	}
	@Autowired
	public void setTestUpdateCommandExecutor(TestUpdateCommandExecutor testUpdateCommandExecutor) {
		this.testUpdateCommandExecutor = testUpdateCommandExecutor;
	}
}
