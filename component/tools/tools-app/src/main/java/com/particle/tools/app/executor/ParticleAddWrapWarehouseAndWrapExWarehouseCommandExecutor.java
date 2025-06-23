package com.particle.tools.app.executor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.file.FileTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.tools.client.dto.command.AddWrapWarehouseAndWrapExWarehouseCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 添加包装出库入库 指令执行器
 * 以 DataCompanyPunishment 为模板
 * </p>
 * @author yw
 * @since 2025-06-20 18:12:05
 */
@Component
@Validated
public class ParticleAddWrapWarehouseAndWrapExWarehouseCommandExecutor extends AbstractBaseExecutor {

	public static void main(String[] args) {
		AddWrapWarehouseAndWrapExWarehouseCommand addWarehouseAndExWarehouseCommand = new AddWrapWarehouseAndWrapExWarehouseCommand();
		addWarehouseAndExWarehouseCommand.setDomainName("DataCompanySpotCheck");
		addWarehouseAndExWarehouseCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/data");

		addWarehouseAndExWarehouseCommand.setTemplateComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/data");
		new ParticleAddWrapWarehouseAndWrapExWarehouseCommandExecutor().addWrapWarehouseAndWrapExWarehouse(addWarehouseAndExWarehouseCommand);
	}
	// 以行政处罚作为分页模板
	private String templateDomainName = "DataCompanyPunishment";

	private String javaExt = ".java";
	private String srcPath = "src/main/java";
	private String templateClient = "data-client";
	private String templateApp = "data-app";
	// 出库
	private String templateExWarehouseCommandExecutor = "com.particle.data.app.company.executor.representation.exwarehousewrap." + templateDomainName + "WrapExWarehouseCommandExecutor";
	private String templateExWarehouseCommandExecutorPath = templateExWarehouseCommandExecutor.replace(".", File.separator) + javaExt;

	// 入库
    private String templateWarehouseCommandExecutor = "com.particle.data.app.company.executor.warehousewrap." + templateDomainName + "WrapWarehouseCommandExecutor";
    private String templateWarehouseCommandExecutorPath = templateWarehouseCommandExecutor.replace(".", File.separator) + javaExt;

	/**
	 * 添加出入库
	 * 以 DataCompanyBasic 为基础模板复制出来，然后修改模板内容
	 * @param addWarehouseAndExWarehouseCommand
	 * @return
	 */
	public Response addWrapWarehouseAndWrapExWarehouse(AddWrapWarehouseAndWrapExWarehouseCommand addWarehouseAndExWarehouseCommand) {

		// 出库处理
		handleExWarehouse(addWarehouseAndExWarehouseCommand);
		// 入库处理
		handleWarehouse(addWarehouseAndExWarehouseCommand);
		return Response.buildSuccess();
	}

	/**
	 * 处理出库
	 * @param addWarehouseAndExWarehouseCommand
	 */
	private void handleExWarehouse(AddWrapWarehouseAndWrapExWarehouseCommand addWarehouseAndExWarehouseCommand) {
		String domainName = addWarehouseAndExWarehouseCommand.getDomainName();
		String componentBackendAbsolutePath = addWarehouseAndExWarehouseCommand.getComponentBackendAbsolutePath();
		String templateDomainName = this.templateDomainName;
		String templateComponentBackendAbsolutePath = addWarehouseAndExWarehouseCommand.getTemplateComponentBackendAbsolutePath();

		File domainFile = getFile(domainName, componentBackendAbsolutePath);
		File templateDomainFile = getFile(templateDomainName, templateComponentBackendAbsolutePath);

		String domainFileCommentSimple = readJavaFileCommentSimple(domainFile.getAbsolutePath());
		String templateDomainFileCommentSimple = readJavaFileCommentSimple(templateDomainFile.getAbsolutePath());

        Map<String, String> replaceMap = new LinkedHashMap();

        replaceMap.put(templateDomainName, domainName);
        replaceMap.put(StrUtil.lowerFirst(templateDomainName), StrUtil.lowerFirst(domainName));
        replaceMap.put(templateDomainFileCommentSimple, domainFileCommentSimple);
        replaceMap.put("企业基本信息", domainFileCommentSimple);
        replaceMap.put("企业行政处罚", domainFileCommentSimple);
        replaceMap.put("企业年报行政许可", domainFileCommentSimple);
		replaceMap.put("企业立案信息当事人", domainFileCommentSimple);
		replaceMap.put("企业立案信息", domainFileCommentSimple);
		replaceMap.put("企业知识产权专利申请权利人", domainFileCommentSimple);
		replaceMap.put("企业知识产权专利被引证信息", domainFileCommentSimple);
		replaceMap.put("企业法院公告内容", domainFileCommentSimple);
		replaceMap.put("企业法院公告当事人", domainFileCommentSimple);
		replaceMap.put("企业法院公告", domainFileCommentSimple);


		// 出库 CommandExecutor 处理
		String templateExWarehouseCommandExecutorAbsolutePath = FilePathTool.concat(templateComponentBackendAbsolutePath, templateApp,srcPath, templateExWarehouseCommandExecutorPath);
		copy(templateExWarehouseCommandExecutorAbsolutePath, replaceMap);

	}

	/**
	 * 处理入库
	 * @param addWarehouseAndExWarehouseCommand
	 */
	private void handleWarehouse(AddWrapWarehouseAndWrapExWarehouseCommand addWarehouseAndExWarehouseCommand) {
        String domainName = addWarehouseAndExWarehouseCommand.getDomainName();
        String componentBackendAbsolutePath = addWarehouseAndExWarehouseCommand.getComponentBackendAbsolutePath();
        String templateDomainName = this.templateDomainName;
        String templateComponentBackendAbsolutePath = addWarehouseAndExWarehouseCommand.getTemplateComponentBackendAbsolutePath();

        File domainFile = getFile(domainName, componentBackendAbsolutePath);
        File templateDomainFile = getFile(templateDomainName, templateComponentBackendAbsolutePath);

        String domainFileCommentSimple = readJavaFileCommentSimple(domainFile.getAbsolutePath());
        String templateDomainFileCommentSimple = readJavaFileCommentSimple(templateDomainFile.getAbsolutePath());

        Map<String, String> replaceMap = new LinkedHashMap();

        replaceMap.put(templateDomainName, domainName);
        replaceMap.put(StrUtil.lowerFirst(templateDomainName), StrUtil.lowerFirst(domainName));
        replaceMap.put(templateDomainFileCommentSimple, domainFileCommentSimple);
        replaceMap.put("企业基本信息", domainFileCommentSimple);
        replaceMap.put("企业行政处罚", domainFileCommentSimple);
        replaceMap.put("企业年报行政许可", domainFileCommentSimple);
        replaceMap.put("企业立案信息当事人", domainFileCommentSimple);
        replaceMap.put("企业立案信息", domainFileCommentSimple);
        replaceMap.put("企业知识产权专利申请权利人", domainFileCommentSimple);
        replaceMap.put("企业知识产权专利被引证信息", domainFileCommentSimple);
		replaceMap.put("企业法院公告内容", domainFileCommentSimple);
		replaceMap.put("企业法院公告当事人", domainFileCommentSimple);
        replaceMap.put("企业法院公告", domainFileCommentSimple);

		// 入库 CommandExecutor 处理
		String templateWarehouseCommandExecutorAbsolutePath = FilePathTool.concat(templateComponentBackendAbsolutePath, templateApp,srcPath, templateWarehouseCommandExecutorPath);
		copy(templateWarehouseCommandExecutorAbsolutePath, replaceMap);

	}

	/**
	 * 在一个目录下查询一个文件
	 * @param nameWithOutSuffix 文件名后缀，不带扩展名
	 * @param componentBackendAbsolutePath
	 * @return
	 */
	private File getFile(String nameWithOutSuffix, String componentBackendAbsolutePath) {
		List<File> fileList = FileTool.getFiles(file -> {
			// XxxxxCreateCommand.java
			String name = FileUtil.getName(file);
			// XxxxxCreateCommand
			// name = name.substring(0,name.lastIndexOf("."));
			boolean equalsAny = StrUtil.equalsAny(name,
					nameWithOutSuffix + ".java"
			);
			return equalsAny;
		}, componentBackendAbsolutePath,Integer.MAX_VALUE,1);

		return fileList.get(0);
	}

	/**
	 * 获取java文件的属性行
	 * @param javaFileLines
	 * @return
	 */
	private List<String> readJavaFilePropertyLines(List<String> javaFileLines) {
		List<String> result = new ArrayList<>();
		boolean start = false;
		for (String javaFileLine : javaFileLines) {
			if (!start && javaFileLine.contains(" class") && javaFileLine.contains("{")) {
				start = true;
				continue;
			}
			if (start && javaFileLine.contains("}")) {
				start = false;
			}
			if (start) {
				result.add(javaFileLine);
			}
		}
		return result;
	}


	/**
	 * 添加属性行
	 * @param javaFileLines
	 * @param propertyLines
	 * @return
	 */
	private List<String> writeJavaFilePropertyLines(List<String> javaFileLines, List<String> propertyLines) {
		List<String> result = new ArrayList<>();
		boolean start = false;
		String placeholder = "javaFileLine placeholder";
		for (String javaFileLine : javaFileLines) {
			if (!start && javaFileLine.contains(" class") && javaFileLine.contains("{")) {
				start = true;
				result.add(javaFileLine);
				continue;
			}
			if (start && javaFileLine.contains("}")) {
				start = false;
			}
			if (start) {
                if (!result.contains(placeholder)) {
					result.add(placeholder);
                }
			}else {
				result.add(javaFileLine);
			}
		}
		List<String> newResult = new ArrayList<>();
		for (String s : result) {
			if (s.equals(placeholder)) {
				newResult.addAll(propertyLines);
			}else {
				newResult.add(s);
			}
		}
		return newResult;
	}

	/**
	 * 复制文件
	 * @param sourcePath
	 * @param replaceMap
	 * @return 返回新的文件
	 */
	private File copy(String sourcePath, Map<String,String> replaceMap) {
		String destPath = handleReplaceMap(sourcePath,replaceMap);
		File destPathFile = FileUtil.copy(sourcePath, destPath, true);
		String readUtf8String = FileUtil.readUtf8String(destPathFile);
		FileUtil.writeUtf8String(handleReplaceMap(readUtf8String,replaceMap),destPathFile);
		return destPathFile;
	}


	/**
	 * 替换模板
	 * @param source
	 * @param replaceMap
	 * @return
	 */
	private String handleReplaceMap(String source, Map<String, String> replaceMap) {
		for (Map.Entry<String, String> stringStringEntry : replaceMap.entrySet()) {
			System.out.println(stringStringEntry.getKey() + ":" + stringStringEntry.getValue());
			source = source.replace(stringStringEntry.getKey(), stringStringEntry.getValue());
		}
		return source;
	}

	/**
	 * 替换多个模板
	 * @param sourceList
	 * @param replaceMap
	 * @return
	 */
	private List<String> handleReplaceMapList(List<String> sourceList, Map<String, String> replaceMap) {
		return sourceList.stream().map(s -> handleReplaceMap(s,replaceMap)).collect(Collectors.toList());
	}

	/**
	 * 读取一个类文件的简单注释
	 * @param javaFilePath
	 * @return
	 */
	private String readJavaFileCommentSimple(String javaFilePath) {
		List<String> javaFileLines = FileUtil.readUtf8Lines(javaFilePath);
		String result = null;
		boolean start = false;
		for (String javaFileLine : javaFileLines) {
			if (!start && javaFileLine.contains("* <p>")) {
				start = true;
				continue;
			}
			if (start && javaFileLine.contains("* </p>")) {
				start = false;
			}
			if (start) {
				result = javaFileLine;
			}
			if (result != null) {
				break;
			}
		}
		result = result.replace("*", "").trim().split(" ")[0];
		return result;
	}

}
