package com.particle.tools.app.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.file.FileTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.global.tool.str.StringTool;
import com.particle.tools.client.dto.command.AddOpenapiExecuteProviderCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 添加 开放接口执行供应商 指令执行器
 * 以 DataCompanyDiscreditedJudgmentDebtorOpenapiExecuteProvider 为模板
 * </p>
 * 注意：主要针对 data 业务组件使用，主要用于针对开放接口执行供应商辅助生成一些代码，生成后，需要手动处理一些处理不到位的地方
 * @author yw
 * @since 2025-04-27 11:36:45
 */
@Component
@Validated
public class ParticleAddOpenapiExecuteProviderCommandExecutor extends AbstractBaseExecutor {

	public static void main(String[] args) {
		AddOpenapiExecuteProviderCommand addOpenapiExecuteProviderCommand = new AddOpenapiExecuteProviderCommand();
		addOpenapiExecuteProviderCommand.setDomainName("DataCompanySpotCheck");
		addOpenapiExecuteProviderCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/data");

		addOpenapiExecuteProviderCommand.setTemplateComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/data");
		addOpenapiExecuteProviderCommand.setIsSingle(false);
		new ParticleAddOpenapiExecuteProviderCommandExecutor().addWarehouseAndExWarehouse(addOpenapiExecuteProviderCommand);
	}

	/**
	 * 添加出入库
	 * 以 DataCompanyBasic 为基础模板复制出来，然后修改模板内容
	 * @param addOpenapiExecuteProviderCommand
	 * @return
	 */
	public Response addWarehouseAndExWarehouse(AddOpenapiExecuteProviderCommand addOpenapiExecuteProviderCommand) {
		Boolean isSingle = addOpenapiExecuteProviderCommand.getIsSingle();
		String controllerApi = handlerControllerApi(isSingle,
				addOpenapiExecuteProviderCommand.getDomainName(),
				"DataCompany",
				addOpenapiExecuteProviderCommand.getComponentBackendAbsolutePath());
		System.out.println(controllerApi);

		handleOpenapiExecuteProvider(isSingle,
				addOpenapiExecuteProviderCommand.getDomainName(),
				"DataCompany",
				addOpenapiExecuteProviderCommand.getComponentBackendAbsolutePath(),
				addOpenapiExecuteProviderCommand.getTemplateComponentBackendAbsolutePath());

		handleWarehouseCommand(addOpenapiExecuteProviderCommand.getDomainName(),
				addOpenapiExecuteProviderCommand.getComponentBackendAbsolutePath());

		return Response.buildSuccess();
	}

	/**
	 * 处理入库指令
	 * @param domainName
	 * @param componentBackendAbsolutePath
	 */
	private void handleWarehouseCommand(String domainName,
										String componentBackendAbsolutePath){
		File warehouseCommandFile = getFile(domainName + "WarehouseCommand", componentBackendAbsolutePath);
		File createCommandFile = getFile(domainName + "CreateCommand", componentBackendAbsolutePath);

		List<String> createCommandFileList = FileUtil.readUtf8Lines(createCommandFile);
		int startIndex = -1;
		int entIndex = -1;
		for (int i = 0; i < createCommandFileList.size(); i++) {
			String string = createCommandFileList.get(i);
			if (string.contains("public static") && string.contains("createByWarehouseCommand")) {
				startIndex = i;
			}
			if (startIndex > 0 && string.trim().equals("}")) {
				entIndex = i;
				break;
			}
		}
		List<String> createByWarehouseCommandList = CollectionUtil.sub(createCommandFileList,startIndex, entIndex + 1);
		Map<String, String> replaceMap = new LinkedHashMap<>();
		// 参数类型
		replaceMap.put(domainName + "WarehouseCommand", "xxxWarehouseCommand");
		// 返回类型
		replaceMap.put(domainName + "CreateCommand", domainName + "WarehouseCommand");
		// 方法名
		replaceMap.put("createByWarehouseCommand", "createBy" + domainName + "ExWarehouseVO");
		// 参数类型
		replaceMap.put("xxxWarehouseCommand", domainName + "ExWarehouseVO");
		// 参数名
		replaceMap.put(StrUtil.lowerFirst(domainName + "WarehouseCommand"), "exWarehouseVO");
		List<String> createByExWarehouseVOList = createByWarehouseCommandList.stream().map(s -> handleReplaceMap(s, replaceMap)).collect(Collectors.toList());


		List<String> warehouseCommandFileList = FileUtil.readUtf8Lines(warehouseCommandFile);
		int lastIndex = -1;
		for (int i = 0; i < warehouseCommandFileList.size(); i++) {
			String string = warehouseCommandFileList.get(i);
            if (string.trim().equals("}")) {
                lastIndex = i;
            }
		}
		warehouseCommandFileList.addAll(lastIndex, createByExWarehouseVOList);

		FileUtil.writeUtf8Lines(warehouseCommandFileList, warehouseCommandFile.getAbsolutePath());
	}
	/**
	 * 处理 开放接口执行供应商
	 * @param isSingle
	 * @param domainName
	 * @param domainPrefix
	 * @param componentBackendAbsolutePath
	 * @param templateComponentBackendAbsolutePath
	 */
	private void handleOpenapiExecuteProvider(Boolean isSingle,
											  String domainName,
											  String domainPrefix,
											  String componentBackendAbsolutePath,
											  String templateComponentBackendAbsolutePath){
		String targetDirPath = "data-app/src/main/java/com/particle/data/app/company/openapi/localdatabasedata";
		// String templatePath = "data-app/src/main/java/com/particle/data/app/company/openapi/localdatabasedata/DataCompanyDiscreditedJudgmentDebtorOpenapiExecuteProvider.java";
		String templatePath = "data-app/src/main/java/com/particle/data/app/company/openapi/localdatabasedata/DataCompanyCourtAnnouncementOpenapiExecuteProvider.java";

		String targetAbsoluteDirPath = FilePathTool.concat(componentBackendAbsolutePath, targetDirPath);
		String templateAbsolutePath = FilePathTool.concat(templateComponentBackendAbsolutePath, templatePath);

		File domainFile = getFile(domainName, componentBackendAbsolutePath);
		String domainCommentSimple = readJavaFileCommentSimple(domainFile.getAbsolutePath());

		Map<String, String> replaceMap = new LinkedHashMap<>();
		if (isSingle) {
			replaceMap.put("PageResponse", "SingleResponse");
		}
		replaceMap.put("DataCompany", domainPrefix);
		replaceMap.put("dataCompany", StrUtil.lowerFirst(domainPrefix));

		String domainSuffix = domainName.replace(domainPrefix, "");

		replaceMap.put("DiscreditedJudgmentDebtor", domainSuffix);
		replaceMap.put("discreditedJudgmentDebtor", StrUtil.lowerFirst(domainSuffix));
		replaceMap.put("企业失信被执行人", domainCommentSimple);
		replaceMap.put("discredited_judgment_debtor", StringTool.humpToLine(domainSuffix));

		replaceMap.put("CourtAnnouncement", domainSuffix);
		replaceMap.put("courtAnnouncement", StrUtil.lowerFirst(domainSuffix));
		replaceMap.put("企业法院公告", domainCommentSimple);
		replaceMap.put("court_announcement", StringTool.humpToLine(domainSuffix));


		copy(templateAbsolutePath, replaceMap);

	}
	/**
	 * 处理 controller api
	 * @param isSingle
	 * @param domainName
	 * @param domainPrefix
	 * @param componentBackendAbsolutePath
	 * @return
	 */
	private String handlerControllerApi(Boolean isSingle,
									  String domainName,
									  String domainPrefix,
									  String componentBackendAbsolutePath) {
		String template = "    @PreAuthorize(\"hasAuthority('company:openapi:discreditedJudgmentDebtor')\")\n" +
				"    @Operation(summary = \"企业失信被执行人信息接口\")\n" +
				"    @PostMapping(\"/discreditedJudgmentDebtor\")\n" +
				"    public PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> discreditedJudgmentDebtor(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,\n" +
				"                                                                                                     @RequestBody DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand){\n" +
				"        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand, null);\n" +
				"    }";

		File domainFile = getFile(domainName, componentBackendAbsolutePath);
		String domainCommentSimple = readJavaFileCommentSimple(domainFile.getAbsolutePath());


		Map<String, String> replaceMap = new LinkedHashMap<>();
		if (isSingle) {
			replaceMap.put("PageResponse", "SingleResponse");
		}
		replaceMap.put("DataCompany", domainPrefix);
		replaceMap.put("dataCompany", StrUtil.lowerFirst(domainPrefix));

		String domainSuffix = domainName.replace(domainPrefix, "");

		replaceMap.put("DiscreditedJudgmentDebtor", domainSuffix);
		replaceMap.put("discreditedJudgmentDebtor", StrUtil.lowerFirst(domainSuffix));

		replaceMap.put("企业失信被执行人", domainCommentSimple);

		template = handleReplaceMap(template, replaceMap);
		return template;
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
			source = source.replace(stringStringEntry.getKey(), stringStringEntry.getValue());
		}
		return source;
	}
}
