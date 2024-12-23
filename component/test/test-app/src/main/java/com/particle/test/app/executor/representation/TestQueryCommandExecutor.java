package com.particle.test.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.test.app.structmapping.TestAppStructMapping;
import com.particle.test.client.dto.command.representation.TestPageQueryCommand;
import com.particle.test.client.dto.command.representation.TestQueryListCommand;
import com.particle.test.client.dto.data.TestVO;
import com.particle.test.infrastructure.dos.TestDO;
import com.particle.test.infrastructure.service.ITestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 测试 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-07-15
 */
@Component
@Validated
public class TestQueryCommandExecutor extends AbstractBaseQueryExecutor {

	private ITestService iTestService;

	/**
	 * 执行 测试 列表查询指令
	 * @param testQueryListCommand
	 * @return
	 */
	public MultiResponse<TestVO> execute(@Valid TestQueryListCommand testQueryListCommand) {
		List<TestDO> testDO = iTestService.list(testQueryListCommand);
		List<TestVO> testVOS = TestAppStructMapping.instance.testDOsToTestVOs(testDO);
		return MultiResponse.of(testVOS);
	}
	/**
	 * 执行 测试 分页查询指令
	 * @param testPageQueryCommand
	 * @return
	 */
	public PageResponse<TestVO> execute(@Valid TestPageQueryCommand testPageQueryCommand) {
		Page<TestDO> page = iTestService.listPage(testPageQueryCommand);
		return TestAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 测试 展示用详情查询指令
	 * @param testQueryDetailCommand
	 * @return
	 */
	public SingleResponse<TestVO> executeDetail(IdCommand testQueryDetailCommand) {
		TestDO byId = iTestService.getById(testQueryDetailCommand.getId());
		TestVO testVO = TestAppStructMapping.instance.testDOToTestVO(byId);
		return SingleResponse.of(testVO);
	}
	/**
	 * 执行 测试 更新用详情查询指令
	 * @param testQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TestVO> executeDetailForUpdate(IdCommand testQueryDetailForUpdateCommand) {
		TestDO byId = iTestService.getById(testQueryDetailForUpdateCommand.getId());
		TestVO testVO = TestAppStructMapping.instance.testDOToTestVO(byId);
		return SingleResponse.of(testVO);
	}

	@Autowired
	public void setITestService(ITestService iTestService) {
		this.iTestService = iTestService;
	}
}
