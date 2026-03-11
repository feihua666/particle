package com.particle.global.test;

import com.github.jsonzou.jmockdata.MockConfig;
import com.particle.global.dto.basic.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * controller 测试基类
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 23:15
 */
public class ControllerTest extends SuperTest {

	protected MockMvc mockMvc;

	protected static MockConfig mockConfig = null;
	static {
		mockConfig = new MockConfig();
		List<String> excludes = new ArrayList();
		excludes.add(DTO.PROPERTY_SERIALVERSIONUID);
		mockConfig.excludes(excludes.toArray(new String[0]));
	}

	@Override
	public MockConfig mockConfig() {
		return mockConfig;
	}

	@Autowired
	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
}
