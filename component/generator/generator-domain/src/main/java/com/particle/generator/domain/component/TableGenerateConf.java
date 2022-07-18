package com.particle.generator.domain.component;

import com.particle.generator.domain.*;
import com.particle.global.dto.basic.Value;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 表生成配置，值对象
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 22:59
 */
@Data
@Builder
public class TableGenerateConf extends Value {

	/**
	 * 生成的父包
	 * 如：com.particle
	 */
	private String packageParent;
	/**
	 * 分包名
	 * 如：user
	 * {@link TableGenerateConf#packageParent} + {@link TableGenerateConf#packageModuleName} 组成最终的父包名
	 */
	private String packageModuleName;
	/**
	 * 是否单文件覆盖
	 */
	private Boolean fileOverride;
	/**
	 * 是否删除生成的文件，删除优先，不会再生成
	 */
	private Boolean fileDelete;
	/**
	 * 作者
	 */
	private String author;

	/**
	 * 表类型
	 */
	private TableType tableType;

	/**
	 * 生成的目标绝对路径父目录
	 * 子模块的绝对路径
	 */
	private String outputAbsoluteDir;

	/**
	 * 要生成的子模块
	 */
	private SubModule subModule;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 表前缀，如果指定表前缀，生成实体时会去掉对应的前缀
	 * 不需要带下划线如：component_area 表的前端是 component
	 */
	private String tablePrefix;
	/**
	 * 要生成的文件
	 */
	private List<OutputFileConf> outputFileConfs;

	/**
	 * 数据源
	 */
	private DatasourceConf datasourceConf;

	/**
	 * 模块的名称
	 * 应该从 {@link ComponentGenerateConf#componentModuleName} 传递
	 */
	private String componentModuleName;

	/**
	 * 将pkg转为路径分隔符
	 * @param pkg
	 * @return
	 */
	public static String pkgPath(String pkg) {
		if (pkg == null) {
			return pkg;
		}
		return pkg.replace(".", File.separator);
	}


	/**
	 * 覆盖配置
	 * @param componentGenerateConf
	 */
	public void overrideByComponentGenerateConf(ComponentGenerateConf componentGenerateConf){
		fileOverride = Optional.ofNullable(fileOverride).orElse(componentGenerateConf.getFileOverride());
		author = Optional.ofNullable(author).orElse(componentGenerateConf.getAuthor());
		outputAbsoluteDir = Optional.of(outputAbsoluteDir).orElse(componentGenerateConf.subModuleAbsolutePath(subModule));
		componentModuleName = Optional.of(componentModuleName).orElse(componentGenerateConf.getComponentModuleName());
	}


	public static TableGenerateConf create(String packageParent,
										   String packageModuleName,
										   Boolean fileOverride,
										   Boolean fileDelete,
										   String author,
										   TableType tableType,
										   String outputAbsoluteDir,
										   SubModule subModule,
										   String tableName,
										   String tablePrefix,
										   List<OutputFileConf> outputFileConfs,
										   DatasourceConf datasourceConf,
										   String componentModuleName) {
		return TableGenerateConf.builder()
				.packageParent(packageParent)
				.packageModuleName(packageModuleName)
				.fileOverride(fileOverride)
				.fileDelete(fileDelete)
				.author(author)
				.tableType(tableType)
				.outputAbsoluteDir(outputAbsoluteDir)
				.subModule(subModule)
				.tableName(tableName)
				.tablePrefix(tablePrefix)
				.outputFileConfs(outputFileConfs)
				.datasourceConf(datasourceConf)
				.componentModuleName(componentModuleName)
				.build();
	}



}
