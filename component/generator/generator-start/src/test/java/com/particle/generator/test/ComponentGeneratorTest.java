package com.particle.generator.test;

import cn.hutool.core.io.FileUtil;
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
		/**
		 * 注意，如果在生成前父级pom中没有
		 * <modules>
		 * </modules>
		 * 那么需要手动添加
		 */
		ComponentGenerateConf componentGenerateConf = ComponentGenerateConf.create(
				//System.getProperty("user.dir"),
				// particle 项目的根目录
				FileUtil.getParent(System.getProperty("user.dir"),3),
				"user",
				Arrays.stream(SubModule.values()).collect(Collectors.toList()),
				true,
				"yw",
				"component",
				"component"

		);
		componentGenerator.componentGenerate(componentGenerateConf);
	}

}
