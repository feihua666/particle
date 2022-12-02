package com.particle.generator.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.particle.generator.domain.SubModule;
import com.particle.generator.domain.TableType;
import com.particle.generator.domain.component.ComponentGenerateConf;
import com.particle.generator.domain.component.DatasourceConf;
import com.particle.generator.domain.component.OutputFileConf;
import com.particle.generator.domain.component.TableGenerateConf;
import com.particle.generator.infrastructure.generator.table.TableGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 根据表生成代码
 * </p>
 *
 * @author yangwei
 * @since 2022-07-08 10:37
 */
@SpringBootTest
public class TableGeneratorTest {
	public static String datasourceUrl = "jdbc:mysql://localhost/particle_dev?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";
	public static String datasourceUsername = "root";
	public static String datasourcePassword = "rootroot";

	@Autowired
	private TableGenerator tableGenerator;

	@Test
	public void tableGeneratorTest() {

		String componentModuleName = "func";
		TableType tableType = TableType.NORMAL;
		String author = "yw";
		String tableName = "component_func_group";
		String tablePrefix = "component";
		boolean fileOverride = true;
		boolean fileDelete = false;
		// 如果表多，建议添加，如果只有一张表，建议留空
		String packageModuleName = "";

		ComponentGenerateConf componentGenerateConf = ComponentGenerateConf.create(
				//System.getProperty("user.dir"),
				// particle 项目的根目录
				FileUtil.getParent(System.getProperty("user.dir"),3),
				componentModuleName,
				// rel关系表类型只生成INFRASTRUCTURE
				Arrays.stream(SubModule.values()).collect(Collectors.toList()),
				true,
				author,
				"component",
				"component"
		);

		List<SubModule> subModules = componentGenerateConf.getSubModules();
		for (SubModule subModule : subModules) {
			TableGenerateConf tableGenerateConf = TableGenerateConf.create(
					StrUtil.format("{}.{}",
							"com.particle",
							//该配置不要动
							ComponentGenerateConf.componentModuleNameToPkg(componentGenerateConf.getComponentModuleName())),
					packageModuleName,
					fileOverride,
					fileDelete,
					author,
					tableType,
					componentGenerateConf.subModuleAbsolutePath(subModule),
					subModule,
					tableName,
					tablePrefix,
					OutputFileConf.createAll(),
					DatasourceConf.create(datasourceUrl,
							datasourceUsername,
							datasourcePassword),
					componentGenerateConf.getComponentModuleName());
			tableGenerateConf.overrideByComponentGenerateConf(componentGenerateConf);

			tableGenerator.tableGenerate(tableGenerateConf);

		}

	}
}
