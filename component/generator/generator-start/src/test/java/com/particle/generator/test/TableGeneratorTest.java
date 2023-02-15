package com.particle.generator.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.particle.generator.domain.MethodEnum;
import com.particle.generator.domain.OutputFileEnum;
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

import java.util.ArrayList;
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

		String componentModuleName = "low-code";
		TableType tableType = TableType.NORMAL;
		String author = "yw";
		String tableName = "component_lowcode_segment_gen";
		String tablePrefix = "component";
		boolean fileOverride = true;
		boolean fileDelete = false;
		// 如果表多，建议添加，如果只有一张表，建议留空，如果有子模块，需要修改 自动配置中的（xxx-boot-starter） MapperScan 注解以扫描到mapper
		String packageModuleName = "generator";
		// 是否只修改了字段，修改了字段，只生成实体就行
		boolean isUpdateFieldOny = false;

		ComponentGenerateConf componentGenerateConf = ComponentGenerateConf.create(
				//System.getProperty("user.dir"),
				// particle 项目的根目录
				FileUtil.getParent(System.getProperty("user.dir"),3),
				componentModuleName,
				// rel关系表类型只生成INFRASTRUCTURE
				isUpdateFieldOny ? Arrays.asList(SubModule.CLIENT,SubModule.INFRASTRUCTURE) : Arrays.stream(SubModule.values()).collect(Collectors.toList()),
				true,
				author,
				"component",
				"component"
		);

		List<SubModule> subModules = componentGenerateConf.getSubModules();
		List<OutputFileConf> outputFileConfs = OutputFileConf.createAll();
		if (isUpdateFieldOny) {

			outputFileConfs = outputFileConfs.stream().filter(item ->
					item.getOutputFileEnum() == OutputFileEnum.entity
					|| item.getOutputFileEnum() == OutputFileEnum.createCommand
					|| item.getOutputFileEnum() == OutputFileEnum.updateCommand
					|| item.getOutputFileEnum() == OutputFileEnum.queryListCommand
					|| item.getOutputFileEnum() == OutputFileEnum.pageQueryCommand
			).collect(Collectors.toList());
		}
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
					outputFileConfs,
					DatasourceConf.create(datasourceUrl,
							datasourceUsername,
							datasourcePassword),
					componentGenerateConf.getComponentModuleName());
			tableGenerateConf.overrideByComponentGenerateConf(componentGenerateConf);

			tableGenerator.tableGenerate(tableGenerateConf);

		}

	}
}
