package com.particle.generator.infrastructure.generator.component;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.generator.domain.SubModule;
import com.particle.generator.domain.component.ComponentGenerateConf;
import com.particle.global.tool.str.PathTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 组件生成器
 * 主要实现过程：
 * 1. 将 componenttemplate 组件复制到临时目录
 * 2. 将复制完成的按新的组件名称替换修整
 * 3. 将修整完成的得到了目前路径
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 23:41
 */
@Slf4j
@Component
public class ComponentGenerator {

	/**
	 * 模板根组件名称
	 */
	private static String templatename = "componenttemplate";

	private static String templateName = "componentTemplate";
	private static String TemplateName = "ComponentTemplate";
	/**
	 * 第一个字母大写
	 */
	private static String Templatename = "Componenttemplate";


	/**
	 * 所有子模块名称
	 */
	private static List<String> subModuleNames = Arrays.stream(SubModule.values())
			.map(subModule -> StrUtil.format("{}-{}", templatename,subModule.name().toLowerCase()))
			.collect(Collectors.toList());


	public boolean componentGenerate(ComponentGenerateConf componentGenerateConf) {
		String componentModuleName = componentGenerateConf.getComponentModuleName();
		String projectAbsolutePath = componentGenerateConf.getProjectAbsolutePath();
		log.info("开始生成组件 moduleName={}", componentModuleName);
		String templateAbsolutePath = PathTool.concat(projectAbsolutePath,componentGenerateConf.getTemplateRelativePath(), templatename);
		String templateTempAbsolutePath = copyTemplateToTemp(templateAbsolutePath);
		handle(templateTempAbsolutePath, componentGenerateConf);

		// 重命名前先删除重命名后的根组件名称，防止冲突
		FileUtil.del(PathTool.concat(FileUtil.getParent(templateTempAbsolutePath,1),componentModuleName));
		// 重命名根组件,将模板名称重命名目标组件名称
		File rename = FileUtil.rename(new File(templateTempAbsolutePath), componentModuleName, false, true);

		// 将处理好的组件复制到目标
		String outputParentAbsolutePath = componentGenerateConf.outputAbsolutePath();
		File copy = FileUtil.copy(rename.getAbsolutePath(), outputParentAbsolutePath, componentGenerateConf.getFileOverride());

		// 处理parent pom 以添加 module
		String parentPomAbsolutePath = PathTool.concat(outputParentAbsolutePath, "pom.xml");
		if (FileUtil.exist(parentPomAbsolutePath)) {
			List<String> parentPomLines = FileUtil.readUtf8Lines(parentPomAbsolutePath);
			String parentModuleSegment = StrUtil.format("        <module>{}</module>", componentModuleName);
			String parentModuleSegmentTrim = parentModuleSegment.trim();
			for (int i = 0; i < parentPomLines.size(); i++) {
				// 已经存在不需要再添加
				if (parentPomLines.get(i).contains(parentModuleSegmentTrim)) {
					break;
				}

				// 找到modules结尾，说明上面判断未生效，直接添加
				if (parentPomLines.get(i).contains("</modules>")) {
					parentPomLines.add(i,parentModuleSegment);
					FileUtil.writeUtf8Lines(parentPomLines,parentPomAbsolutePath);
					break;
				}
			}
		}


		log.info("组件生成结束 moduleName={},absolutePath={}",
				componentModuleName,
				PathTool.concat(copy.getAbsolutePath(), componentModuleName));


		return true;
	}

	/**
	 * 将模板组件复制到临时目录
	 * @param templateAbsolutePath 模板的绝对路径
	 * @return newTempPath 以目标组件名称结尾的绝对路径
	 */
	private String copyTemplateToTemp(String templateAbsolutePath) {
		if (!templateAbsolutePath.endsWith(templatename)) {
			String format = StrUtil.format("templateAbsolutePath should end with {},provided is {} ", templatename, templateAbsolutePath);
			throw new IllegalArgumentException(format);
		}
		if (!FileUtil.isDirectory(templateAbsolutePath)) {
			String format = StrUtil.format("templateAbsolutePath not exist: ", templateAbsolutePath);
			throw new IllegalArgumentException(format);
		}
		String tempPath = FileUtil.getTmpDirPath();
		log.info("tempPath={}",tempPath);

		String newTempPath = tempPath + templatename;
		// 先删除临时目录，以防以前生成过，存在冲突
		boolean del = FileUtil.del(newTempPath);
		File copy = FileUtil.copy(templateAbsolutePath, tempPath, true);

		return newTempPath;
	}

	/**
	 * 开始处理
	 * @param templateTempAbsolutePath
	 * @param componentGenerateConf 组件生成配置
	 */
	private void handle(String templateTempAbsolutePath, ComponentGenerateConf componentGenerateConf) {
		File[] ls = FileUtil.ls(templateTempAbsolutePath);
		for (File file : ls) {
			if (file.isDirectory()) {
				handleDirectory(file,componentGenerateConf);
			}else{
				handleFile(file,componentGenerateConf);
			}
		}
	}

	/**
	 * 处理目录
	 * 将目录重命名及其子目录递归调用
	 * @param file
	 */
	private void handleDirectory(File file, ComponentGenerateConf componentGenerateConf) {
		String absolutePath = file.getAbsolutePath();
		// 子模块处理
		if (subModuleNames.contains(file.getName())) {
			List<String> subModules = componentGenerateConf.getSubModules().stream()
					.map(subModule -> StrUtil.format("{}-{}", templatename,subModule.name().toLowerCase()))
					.collect(Collectors.toList());

			// 不生成直接返回
			if (!subModules.contains(file.getName())) {
				return;
			}
			File rename = FileUtil.rename(file, file.getName().replace(templatename, componentGenerateConf.getComponentModuleName()), true);
			handle(rename.getAbsolutePath(),componentGenerateConf);
			return;
		}
		// 忽略 target 目录
		if("target".equals(file.getName())){
			FileUtil.del(file);
			return;
		}
		// package 处理
		String absolutePath1 = file.getParentFile().getAbsolutePath();
		if (absolutePath1.contains(StrUtil.format("{}src{}main{}java",File.separator,File.separator,File.separator))
				|| absolutePath1.contains(StrUtil.format("{}src{}test{}java",File.separator,File.separator,File.separator))) {
			if (templatename.equals(file.getName())) {
				String newPackageName = ComponentGenerateConf.componentModuleNameToPkg(componentGenerateConf.getComponentModuleName());
				File rename = FileUtil.rename(file, newPackageName, true);
				handle(rename.getAbsolutePath(),componentGenerateConf);
				return;
			}
		}
		// 其它目录处理
		if (file.getName().contains(templatename)) {
			String replace = componentGenerateConf.getComponentModuleName().toLowerCase();
			File rename = FileUtil.rename(file, file.getName().replace(templatename,replace), true);
			handle(rename.getAbsolutePath(),componentGenerateConf);
			return;
		}

		handle(file.getAbsolutePath(),componentGenerateConf);

	}

	/**
	 * 处理文件
	 * 文件内容替换及文件名重命名
	 * @param file
	 */
	private void handleFile(File file, ComponentGenerateConf componentGenerateConf) {
		if(file.getName().endsWith(".iml")){
			FileUtil.del(file);
			return;
		}
		String moduleNameToPackageName = ComponentGenerateConf.componentModuleNameToPkg(componentGenerateConf.getComponentModuleName());
		String moduleNameToCamelCase = StrUtil.toCamelCase(componentGenerateConf.getComponentModuleName().replace("-", CharUtil.UNDERLINE + ""));
				// 内容替换
				List < String > contentLines = FileUtil.readUtf8Lines(file);
		List<String> newContentLines = new ArrayList<>(contentLines.size());
		String newContentLine = null;
		for (String contentLine : contentLines) {
			newContentLine = contentLine;

			// java文件处理
			if (file.getName().endsWith(".java")) {
				String authorSymbol = "@author";
				String sinceSymbol = "@since";
				// author 过滤
				if (contentLine.contains(authorSymbol)) {
					newContentLine = newContentLine.substring(0,newContentLine.indexOf(authorSymbol) + authorSymbol.length()) + " " + componentGenerateConf.getAuthor();
				}
				if (contentLine.contains(sinceSymbol)) {
					newContentLine = newContentLine.substring(0,newContentLine.indexOf(sinceSymbol) + sinceSymbol.length()) + " " + LocalDateTimeUtil.formatNormal(LocalDateTime.now());
				}
				if (contentLine.startsWith("package ") || contentLine.startsWith("import ")) {
					newContentLine = newContentLine.replace(templatename, moduleNameToPackageName);
				}
				if (file.getName().equals("spring.factories ")) {
					newContentLine = newContentLine.replace(templatename, moduleNameToPackageName);
				}
			}

			// 关键字符替换
			if (contentLine.contains(templatename)) {
				newContentLine = newContentLine.replace(templatename, componentGenerateConf.getComponentModuleName());
			}
			// 文件内容替换，这里主要是替换java类名
			if (contentLine.contains(Templatename)) {
				newContentLine = newContentLine.replace(Templatename, StrUtil.upperFirst(moduleNameToPackageName));
			}
			if (contentLine.contains(templateName)) {
				newContentLine = newContentLine.replace(templateName, moduleNameToCamelCase);
			}
			if (contentLine.contains(TemplateName)) {
				newContentLine = newContentLine.replace(TemplateName, StrUtil.upperFirst(moduleNameToCamelCase));
			}

			newContentLines.add(newContentLine);
		}
		FileUtil.writeUtf8Lines(newContentLines,file);


		// 文件名处理
		if (file.getName().contains(templatename)) {
			String moduleNameToFileName = moduleNameToFileName(componentGenerateConf.getComponentModuleName());
			FileUtil.rename(file, file.getName().replace(templatename, moduleNameToFileName)
					, false, true);
		}
		// 首字母大写的一般为java文件，文件名规则和package类似
		if (file.getName().contains(Templatename)) {
			FileUtil.rename(file, file.getName().replace(Templatename, StrUtil.upperFirst(moduleNameToPackageName))
					, false, true);

		}
		if (file.getName().contains(TemplateName)) {
			FileUtil.rename(file, file.getName().replace(TemplateName, StrUtil.upperFirst(moduleNameToCamelCase))
					, false, true);

		}
		if (file.getName().contains(templateName)) {
			FileUtil.rename(file, file.getName().replace(templateName, moduleNameToCamelCase)
					, false, true);

		}
	}


	/**
	 * 模块名称转文件名
	 * @param moduleName
	 * @return
	 */
	private String moduleNameToFileName(String moduleName) {
		return moduleName.toLowerCase();
	}
}
