package com.particle.test.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.test.app.executor.representation.TestQueryCommandExecutor;
import com.particle.test.client.api.representation.ITestRepresentationApplicationService;
import com.particle.test.client.dto.command.representation.TestPageQueryCommand;
import com.particle.test.client.dto.command.representation.TestQueryDetailCommand;
import com.particle.test.client.dto.command.representation.TestQueryDetailForUpdateCommand;
import com.particle.test.client.dto.command.representation.TestQueryListCommand;
import com.particle.test.client.dto.data.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Service
@CatchAndLog
public class TestRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITestRepresentationApplicationService {

	private TestQueryCommandExecutor testQueryCommandExecutor;


	@Override
	public SingleResponse<TestVO> queryDetail(TestQueryDetailCommand testQueryDetailCommand) {
		return testQueryCommandExecutor.execute(testQueryDetailCommand);
	}

	@Override
	public SingleResponse<TestVO> queryDetailForUpdate(TestQueryDetailForUpdateCommand testQueryDetailForUpdateCommand) {
		return testQueryCommandExecutor.execute(testQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<TestVO> pageQuery(TestPageQueryCommand testPageQueryCommand) {
		return testQueryCommandExecutor.execute(testPageQueryCommand);
	}

	@Override
	public MultiResponse<TestVO> queryList(TestQueryListCommand testQueryListCommand) {
		return testQueryCommandExecutor.execute(testQueryListCommand);
	}

	@Autowired
	public void setTestQueryCommandExecutor(TestQueryCommandExecutor testQueryCommandExecutor) {
		this.testQueryCommandExecutor = testQueryCommandExecutor;
	}
}
