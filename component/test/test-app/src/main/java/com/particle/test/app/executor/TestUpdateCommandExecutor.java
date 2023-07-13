package com.particle.test.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.test.app.structmapping.TestAppStructMapping;
import com.particle.test.client.dto.command.TestUpdateCommand;
import com.particle.test.client.dto.data.TestVO;
import com.particle.test.domain.Test;
import com.particle.test.domain.TestId;
import com.particle.test.domain.gateway.TestGateway;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 测试 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Component
@Validated
public class TestUpdateCommandExecutor extends AbstractBaseExecutor {

	private TestGateway testGateway;

	/**
	 * 执行 测试 更新指令
	 * @param testUpdateCommand
	 * @return
	 */
	public SingleResponse<TestVO> execute(@Valid TestUpdateCommand testUpdateCommand) {
		Test test = createByTestUpdateCommand(testUpdateCommand);
		boolean save = testGateway.save(test);
		if (save) {
			return SingleResponse.of(TestAppStructMapping.instance.toTestVO(test));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据测试更新指令创建测试模型
	 * @param testUpdateCommand
	 * @return
	 */
	private Test createByTestUpdateCommand(TestUpdateCommand testUpdateCommand){
		Test test = Test.create();
		TestUpdateCommandToTestMapping.instance.fillTestByTestUpdateCommand(test, testUpdateCommand);
		return test;
	}

	@Mapper
	interface TestUpdateCommandToTestMapping{
		TestUpdateCommandToTestMapping instance = Mappers.getMapper(TestUpdateCommandToTestMapping.class );

		default TestId map(Long id){
			if (id == null) {
				return null;
			}
			return TestId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param test
		 * @param testUpdateCommand
		 */
		void fillTestByTestUpdateCommand(@MappingTarget Test test, TestUpdateCommand testUpdateCommand);
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
