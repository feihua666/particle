package com.particle.generator.domain.component;

import cn.hutool.core.util.StrUtil;
import com.particle.generator.domain.SubModule;
import com.particle.global.dto.basic.Value;
import com.particle.global.tool.str.PathTool;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 组件生成配置，值对象
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 22:59
 */
@Data
@Builder
public class ComponentGenerateConf extends Value {
	/**
	 * 项目根路径，不要以斜杠结尾
	 * 获取模板组件和生成目标组件的基础目录
	 */
	private String projectAbsolutePath;
	/**
	 * 组件名称,单词分隔请使用连字符 横杠（-）
	 * 示例：user、resource-module
	 */
	private String componentModuleName;

	/**
	 * 需要生成的子模块
	 */
	private List<SubModule> subModules;
	/**
	 * 是否单文件覆盖
	 */
	private Boolean fileOverride;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 组件模板相对路径
	 * 相对于 {@link ComponentGenerateConf#projectAbsolutePath}
	 */
	private String templateRelativePath;
	/**
	 * 生成组件相对路径
	 * 相对于 {@link ComponentGenerateConf#projectAbsolutePath}
	 */
	private String outputRelativePath;

	/**
	 * 生成组件绝对路径父路径
	 * @return
	 */
	public String outputAbsolutePath() {
		return PathTool.concat(projectAbsolutePath, outputRelativePath);
	}

	/**
	 * 子模块的绝对路径
	 * @param subModule
	 * @return
	 */
	public String subModuleAbsolutePath(SubModule subModule) {
		String subModuleName = StrUtil.join("-", componentModuleName, subModule.realSubModuleName());
		return PathTool.concat(projectAbsolutePath, outputRelativePath, componentModuleName, subModuleName);

	}


	/**
	 * 将模块名称转为java包名
	 * @param componentModuleName
	 * @return
	 */
	public static String componentModuleNameToPkg(String componentModuleName) {
		return componentModuleName.toLowerCase().replace("-", "");
	}

	/**
	 * 创建对象
	 * @param projectAbsolutePath
	 * @param componentModuleName
	 * @param subModules
	 * @param fileOverride
	 * @return
	 */
	public static ComponentGenerateConf create(String projectAbsolutePath,
											   String componentModuleName,
											   List<SubModule> subModules,
											   Boolean fileOverride,
											   String author) {
		return ComponentGenerateConf.builder()
				.projectAbsolutePath(projectAbsolutePath)
				.componentModuleName(componentModuleName)
				.subModules(subModules)
				.fileOverride(fileOverride)
				.author(author)
				.templateRelativePath("component")
				.outputRelativePath("component")
				.build();
	}
	public static ComponentGenerateConf create(String projectAbsolutePath,
											   String componentModuleName,
											   List<SubModule> subModules,
											   Boolean fileOverride,
											   String author,
											   String templateRelativePath,
											   String outputRelativePath) {
		return ComponentGenerateConf.builder()
				.projectAbsolutePath(projectAbsolutePath)
				.componentModuleName(componentModuleName)
				.subModules(subModules)
				.fileOverride(fileOverride)
				.author(author)
				.templateRelativePath(templateRelativePath)
				.outputRelativePath(outputRelativePath)
				.build();
	}
}
