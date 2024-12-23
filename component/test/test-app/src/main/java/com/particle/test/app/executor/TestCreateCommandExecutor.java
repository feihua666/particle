package com.particle.test.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.test.app.structmapping.TestAppStructMapping;
import com.particle.test.client.dto.command.TestCreateCommand;
import com.particle.test.client.dto.data.TestVO;
import com.particle.test.domain.Test;
import com.particle.test.domain.gateway.TestGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class TestCreateCommandExecutor extends AbstractBaseExecutor {

	private TestGateway testGateway;

	/**
	 * 执行测试添加指令
	 * @param testCreateCommand
	 * @return
	 */
	public SingleResponse<TestVO> execute(@Valid TestCreateCommand testCreateCommand) {
		Test test = createByTestCreateCommand(testCreateCommand);
		boolean save = testGateway.save(test);
		if (save) {
			test = testGateway.getById(test.getId());
			return SingleResponse.of(TestAppStructMapping.instance.toTestVO(test));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据测试创建指令创建测试模型
	 * @param testCreateCommand
	 * @return
	 */
	private Test createByTestCreateCommand(TestCreateCommand testCreateCommand){
		Test test = Test.create();
		TestCreateCommandToTestMapping.instance.fillTestByTestCreateCommand(test, testCreateCommand);
		return test;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  TestCreateCommandToTestMapping{
		TestCreateCommandToTestMapping instance = Mappers.getMapper( TestCreateCommandToTestMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param test
		 * @param testCreateCommand
		 */
		void fillTestByTestCreateCommand(@MappingTarget Test test, TestCreateCommand testCreateCommand);
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
