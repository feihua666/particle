package com.particle.global.test;

import cn.hutool.core.util.ReflectUtil;
import com.github.jsonzou.jmockdata.MockConfig;
import com.particle.global.dto.basic.DTO;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
		excludes.add(BaseDO.PROPERTY_ID);
		Field[] fields = ReflectUtil.getFields(BaseTreeDO.class);
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers())) {
				excludes.add(field.getName());
			}
		}
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
