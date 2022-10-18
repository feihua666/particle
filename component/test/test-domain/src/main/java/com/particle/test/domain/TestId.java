package com.particle.test.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 测试 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
public class TestId extends Id {

	public TestId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 测试 领域模型id
	 * @param id
	 * @return
	 */
	public static TestId of(Long id){
		return new TestId(id);
	}
}
