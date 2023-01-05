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

		// 模块名称，支持中划线
		String componentModuleName = "low-code";
		// 模块输出位置，相对路径，相对于 particle相对路径
		String outputRelativePath = "component";
		// 作者
		String author = "yw";




		// 模块模板 相对路径，相对于 particle相对路径，如果在这里执行，基本不用改
		String templateRelativePath = "component";


		/**
		 * 注意，如果在生成前父级pom中没有
		 * <modules>
		 * </modules>
		 * 那么需要手动添加
		 */
		ComponentGenerateConf componentGenerateConf = ComponentGenerateConf.create(
				//System.getProperty("user.dir"),
				// particle 项目的根目录,如：/Users/yw/fh/git-source/particle
				FileUtil.getParent(System.getProperty("user.dir"),3),
				componentModuleName,
				Arrays.stream(SubModule.values()).collect(Collectors.toList()),
				true,
				author,
				templateRelativePath,
				outputRelativePath

		);
		componentGenerator.componentGenerate(componentGenerateConf);
	}

}
