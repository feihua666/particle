package com.particle.test.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.test.app.structmapping.TestAppStructMapping;
import com.particle.test.client.dto.command.TestDeleteCommand;
import com.particle.test.client.dto.data.TestVO;
import com.particle.test.domain.Test;
import com.particle.test.domain.TestId;
import com.particle.test.domain.gateway.TestGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 测试 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Component
@Validated
public class TestDeleteCommandExecutor extends AbstractBaseExecutor {

	private TestGateway testGateway;

	/**
	 * 执行 测试 删除指令
	 * @param testDeleteCommand
	 * @return
	 */
	public SingleResponse<TestVO> execute(@Valid TestDeleteCommand testDeleteCommand) {
		TestId testId = TestId.of(testDeleteCommand.getId());
		Test byId = testGateway.getById(testId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = testGateway.delete(testId);
		if (delete) {
			return SingleResponse.of(TestAppStructMapping.instance.toTestVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param testGateway
	 */
	@Autowired
	public void setTestGateway(TestGateway testGateway) {
		this.testGateway = testGateway;
	}
}
