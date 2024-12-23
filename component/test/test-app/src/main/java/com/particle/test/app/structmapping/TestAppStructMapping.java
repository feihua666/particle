package com.particle.test.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.test.client.dto.data.TestVO;
import com.particle.test.domain.Test;
import com.particle.test.domain.TestId;
import com.particle.test.infrastructure.dos.TestDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 测试 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TestAppStructMapping {
	public static TestAppStructMapping instance = Mappers.getMapper( TestAppStructMapping.class );

	protected Long map(TestId testId){
		if (testId == null) {
			return null;
		}
		return testId.getId();
	}
	/**
	 * 测试领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TestAppStructMapping#map(TestId)}
	 * @param test
	 * @return
	 */
	public abstract TestVO toTestVO(Test test);


	/**
	 * 数据对象转视图对象
	 * @param testDO
	 * @return
	 */
	public abstract TestVO testDOToTestVO(TestDO testDO);

	/**
	 * 批量转换
	 * @param testDOS
	 * @return
	 */
	public abstract List<TestVO> testDOsToTestVOs(List<TestDO> testDOS);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TestVO> infrastructurePageToPageResponse(Page<TestDO> page) {
		return PageResponse.of(testDOsToTestVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
