package com.particle.test.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.test.domain.Test;
import com.particle.test.domain.TestId;
import com.particle.test.domain.gateway.TestGateway;
import com.particle.test.infrastructure.dos.TestDO;
import com.particle.test.infrastructure.service.ITestService;
import com.particle.test.infrastructure.structmapping.TestInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 测试 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Component
public class TestGatewayImpl extends AbstractBaseGatewayImpl<TestId, Test> implements TestGateway {

	private ITestService iTestService;

	@Override
	public Test getById(TestId testId) {
		TestDO byId = iTestService.getById(testId.getId());
		Test test = DomainFactory.create(Test.class);
		test = TestInfrastructureStructMapping.instance. testDOToTest(test,byId);
		return test;
	}

	@Override
	public boolean doSave(Test test) {
		TestDO testDO = TestInfrastructureStructMapping.instance.testToTestDO(test);
		if (testDO.getId() == null) {
			TestDO add = iTestService.add(testDO);
			test.setId(TestId.of(add.getId()));
			return add != null;
		}
		TestDO update = iTestService.update(testDO);
		return update != null;
	}

	@Override
	public boolean delete(TestId testId) {
		return iTestService.deleteById(testId.getId());
	}


	@Autowired
	public void setITestService(ITestService iTestService) {
		this.iTestService = iTestService;
	}
}
