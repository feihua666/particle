package com.particle.generator.test;

import cn.hutool.core.io.FileUtil;
import com.particle.generator.domain.Generator;
import com.particle.generator.domain.SubModule;
import com.particle.generator.domain.component.ComponentGenerateConf;
import com.particle.generator.infrastructure.generator.component.ComponentGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>
 * 组件生成器测试
 * </p>
 *
 * @author yangwei
 * @since 2022-07-06 13:21
 */
@SpringBootTest
public class ComponentGeneratorTest {

	@Autowired
	private ComponentGenerator componentGenerator;


	@Test
	public void componentGeneratorTest() {
		ComponentGenerateConf componentGenerateConf = ComponentGenerateConf.create(
				//System.getProperty("user.dir"),
				// particle 项目的根目录
				FileUtil.getParent(System.getProperty("user.dir"),3),
				"test",
				Arrays.stream(SubModule.values()).collect(Collectors.toList()),
				true,
				"yw"

		);
		componentGenerator.componentGenerate(componentGenerateConf);
	}

}
