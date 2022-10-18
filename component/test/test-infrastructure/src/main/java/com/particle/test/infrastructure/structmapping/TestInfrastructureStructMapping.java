package com.particle.test.infrastructure.structmapping;

import com.particle.test.domain.Test;
import com.particle.test.domain.TestId;
import com.particle.test.infrastructure.dos.TestDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 测试 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Mapper
public abstract class TestInfrastructureStructMapping {
	public static TestInfrastructureStructMapping instance = Mappers.getMapper( TestInfrastructureStructMapping.class );

	protected TestId map(Long id){
		if (id == null) {
			return null;
		}
		return TestId.of(id);
	}
	protected Long map(TestId testId){
		if (testId == null) {
			return null;
		}
		return testId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TestInfrastructureStructMapping#map(java.lang.Long)}
	 * @param testDO
	 * @return
	 */
	public abstract Test testDOToTest(@MappingTarget Test test, TestDO testDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TestInfrastructureStructMapping#map(TestId)}
	 * @param test
	 * @return
	 */
	public abstract TestDO testToTestDO(Test test);

}
