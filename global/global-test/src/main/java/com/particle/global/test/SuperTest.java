package com.particle.global.test;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;

/**
 * <p>
 * 测试基类
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 23:17
 */
public abstract class SuperTest {


	/**
	 * 模拟数据
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	protected  <T> T mock(Class<T> clazz) {
		return JMockData.mock(clazz, mockConfig());
	}

	/**
	 * 模拟数据配置
	 * @return
	 */
	protected MockConfig mockConfig(){
		return new MockConfig();
	}
}
