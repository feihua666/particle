package com.particle.global.test;

import com.github.jsonzou.jmockdata.MockConfig;

/**
 * <p>
 * 远程调用测试基类
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 23:33
 */
public class FeignClientTest extends SuperTest{

	@Override
	protected MockConfig mockConfig() {
		return ControllerTest.mockConfig;
	}
}
