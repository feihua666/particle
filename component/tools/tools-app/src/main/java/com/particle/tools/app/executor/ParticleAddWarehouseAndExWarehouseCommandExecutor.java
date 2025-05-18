package com.particle.tools.app.executor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.file.FileTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.tools.client.dto.command.AddWarehouseAndExWarehouseCommand;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 添加出库入库 指令执行器
 * 分两种情况
 * 1. 单条情况 以 DataCompanyBasic 为模板
 * 2. 分页情况 以 DataCompanyPunishment 为模板
 * </p>
 * 注意：主要针对 data 业务组件使用，主要用于针对单表的情况辅助生成一些代码，生成后，需要手动处理一些处理不到位的地方，建议从 xxxWarehouseCommand和xxxWarehouseCommandExecutor检查
 * @author yw
 * @since 2025-04-08 10:09:04
 */
@Component
@Validated
public class ParticleAddWarehouseAndExWarehouseCommandExecutor extends AbstractBaseExecutor {

	public static void main(String[] args) {
		AddWarehouseAndExWarehouseCommand addWarehouseAndExWarehouseCommand = new AddWarehouseAndExWarehouseCommand();
		addWarehouseAndExWarehouseCommand.setDomainName("DataCompanyIprPatentParty");
		addWarehouseAndExWarehouseCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/data");

		addWarehouseAndExWarehouseCommand.setTemplateComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/data");
		addWarehouseAndExWarehouseCommand.setIsSingle(false);
		new ParticleAddWarehouseAndExWarehouseCommandExecutor().addWarehouseAndExWarehouse(addWarehouseAndExWarehouseCommand);
	}
	// 以行政处罚作为分页模板
	// private String pageDomainName = "DataCompanyPunishment";
	// private String pageDomainName = "DataCompanyAnnualReportAdministrativeLicense";
	// private String pageDomainName = "DataCompanyCaseFiling";
	private String pageDomainName = "DataCompanyCaseFilingParty";
	// private String pageDomainName = "DataCompanyIprPatentApplicantRight";
	// private String pageDomainName = "DataCompanyIprPatentCited";
	// private String pageDomainName = "DataCompanyCourtAnnouncement";
	// private String pageDomainName = "DataCompanyCourtAnnouncementContent";
	// private String pageDomainName = "DataCompanyCourtAnnouncementParty";
	private String singleDomainName = "DataCompanyBasic";

	private String javaExt = ".java";
	private String srcPath = "src/main/java";
	private String templateClient = "data-client";
	private String templateApp = "data-app";
	// 出库
	private String templateExWarehouseQueryCommand = "com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyBasicExWarehouseQueryCommand";
	private String templateExWarehouseQueryCommandPath = templateExWarehouseQueryCommand.replace(".", File.separator) + javaExt;
	private String templateExWarehouseCommandExecutor = "com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyBasicExWarehouseCommandExecutor";
	private String templateExWarehouseCommandExecutorPath = templateExWarehouseCommandExecutor.replace(".", File.separator) + javaExt;
	private String templateExWarehouseVO = "com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO";
	private String templateExWarehouseVOPath = templateExWarehouseVO.replace(".", File.separator) + javaExt;

	private String templateExWarehouseAppStructMappingMethod1 = "    public abstract DataCompanyBasicExWarehouseVO dataCompanyBasicDOToDataCompanyBasicExWarehouseVO(DataCompanyBasicDO dataCompanyBasicDO);";
	private String templateExWarehouseAppStructMappingMethod2 = "    public abstract List<DataCompanyBasicExWarehouseVO> dataCompanyBasicDOsToDataCompanyBasicExWarehouseVOs(List<DataCompanyBasicDO> dataCompanyBasicDOs);";
	private String templateExWarehouseAppStructMappingMethod3 = "\t/**\n" +
			"\t * 分页转换\n" +
			"\t * @param page\n" +
			"\t * @return\n" +
			"\t */\n" +
			"\tpublic PageResponse<DataCompanyBasicExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyBasicDO> page) {\n" +
			"\t\treturn PageResponse.of(dataCompanyBasicDOsToDataCompanyBasicExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());\n" +
			"\t}";
	private String templateExWarehouseAppStructMappingImport = "import " + templateExWarehouseVO;

    private String templateExWarehouseRepresentationApplicationServiceMethod = "\n" +
            "\t/**\n" +
            "\t * 企业基本信息出库\n" +
            "\t * @param dataCompanyExWarehouseQueryCommand\n" +
            "\t * @return\n" +
            "\t */\n" +
            "\tpublic SingleResponse<DataCompanyBasicExWarehouseVO> exWarehouse(DataCompanyBasicExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);";
	private String templateExWarehouseRepresentationApplicationServiceImport1 = "import " + templateExWarehouseQueryCommand + ";";
	private String templateExWarehouseRepresentationApplicationServiceImport2 = "import " + templateExWarehouseVO + ";";

	private String templateExWarehouseRepresentationApplicationServiceImplMethod = "\n" +
			"    @Override\n" +
			"    public SingleResponse<DataCompanyBasicExWarehouseVO> exWarehouse(DataCompanyBasicExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {\n" +
			"        return dataCompanyBasicExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);\n" +
			"    }\n" +
			"\n";
	private String templateExWarehouseRepresentationApplicationServiceImplImport1 = "import " + templateExWarehouseQueryCommand + ";";
	private String templateExWarehouseRepresentationApplicationServiceImplImport2 = "import " + templateExWarehouseVO + ";";
	private String templateExWarehouseRepresentationApplicationServiceImplImport3 = "import " + templateExWarehouseCommandExecutor + ";";
	private String templateExWarehouseRepresentationApplicationServiceImplField = "    private DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor;";
	private String templateExWarehouseRepresentationApplicationServiceImplSetField = "    @Autowired\n" +
			"    public void setDataCompanyBasicExWarehouseCommandExecutor(DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor) {\n" +
			"        this.dataCompanyBasicExWarehouseCommandExecutor = dataCompanyBasicExWarehouseCommandExecutor;\n" +
			"    }";
	// 入库
    private String templateWarehouseCommand = "com.particle.data.client.company.dto.command.warehouse.DataCompanyBasicWarehouseCommand";
    private String templateWarehouseCommandPath = templateWarehouseCommand.replace(".", File.separator) + javaExt;
    private String templateWarehouseCommandExecutor = "com.particle.data.app.company.executor.warehouse.DataCompanyBasicWarehouseCommandExecutor";
    private String templateWarehouseCommandExecutorPath = templateWarehouseCommandExecutor.replace(".", File.separator) + javaExt;
    private String templateWarehouseApplicationServiceMethod = "\n" +
            "\t/**\n" +
            "\t * 企业基本信息入库\n" +
            "\t * @param dataCompanyBasicWarehouseCommand\n" +
            "\t * @return\n" +
            "\t */\n" +
            "\tSingleResponse<DataCompanyBasicExWarehouseVO> warehouse(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand);";
    private String templateWarehouseApplicationServiceImport1 = "import " + templateWarehouseCommand + ";";
    private String templateWarehouseApplicationServiceImport2 = "import " + templateExWarehouseVO + ";";

    private String templateWarehouseApplicationServiceImplMethod = "\n" +
            "    @Override\n" +
            "    public SingleResponse<DataCompanyBasicExWarehouseVO> warehouse(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand) {\n" +
            "        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);\n" +
            "    }\n";
    private String templateWarehouseApplicationServiceImplImport1 = "import " + templateWarehouseCommand + ";";
    private String templateWarehouseApplicationServiceImplImport2 = "import " + templateExWarehouseVO + ";";
    private String templateWarehouseApplicationServiceImplImport3 = "import " + templateWarehouseCommandExecutor + ";";
    private String templateWarehouseApplicationServiceImplField = "    private DataCompanyBasicWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;";
    private String templateWarehouseApplicationServiceImplSetField = "    @Autowired\n" +
            "    public void setDataCompanyBasicWarehouseCommandExecutor(DataCompanyBasicWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {\n" +
            "        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;\n" +
            "    }\n";

	private String allFieldEmptyTemplate = "\n" +
			"    /**\n" +
			"     * 判断是否所有字段都为空,主要用来检查是否需要更新数据\n" +
			"     * @return\n" +
			"     */\n" +
			"    public boolean allFieldEmpty() {\n" +
			"        return {{allFieldEmpty}};\n" +
			"    }";

	private String compareAndSetNullWhenEqualsTemplate = "\n" +
			"    /**\n" +
			"     * 主要用于更新，如果字段与现有数据一致，则设置为null\n" +
			"     * @param exWarehouseVO\n" +
			"     */\n" +
			"    public void compareAndSetNullWhenEquals(DataCompanyBasicExWarehouseVO exWarehouseVO) {\n" +
			"        {{compareAndSetNullWhenEquals}}\n" +
			"    }\n";

    private String createCommandCreateByWarehouseCommandTemplate = "\n" +
            "    public static DataCompanyBasicCreateCommand createByWarehouseCommand(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand){\n" +
            "        DataCompanyBasicCreateCommand command = new DataCompanyBasicCreateCommand();\n" +
            "        {{commandFields}}\n" +
            "\n" +
            "        return command;\n" +
            "    }";

    private String updateCommandCreateByWarehouseCommandTemplate = "\n" +
            "    public static DataCompanyBasicUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand){\n" +
            "        DataCompanyBasicUpdateCommand command = new DataCompanyBasicUpdateCommand();\n" +
            "        command.setId(id);\n" +
            "        command.setVersion(version);\n" +
            "        {{commandFields}}\n" +
            "\n" +
            "        return command;\n" +
            "    }";
	/**
	 * 添加出入库
	 * 以 DataCompanyBasic 为基础模板复制出来，然后修改模板内容
	 * @param addWarehouseAndExWarehouseCommand
	 * @return
	 */
	public Response addWarehouseAndExWarehouse(AddWarehouseAndExWarehouseCommand addWarehouseAndExWarehouseCommand) {

		Boolean isSingle = addWarehouseAndExWarehouseCommand.getIsSingle();
        if (!isSingle) {
			for (Field field : ReflectUtil.getFields(this.getClass())) {
				String name = field.getName();
				String value = (String) ReflectUtil.getFieldValue(this, name);
				if (singleDomainName.equals(value)) {
					continue;
				}
				if (pageDomainName.equals(value)) {
					continue;
				}

				value = value.replace(singleDomainName, pageDomainName);
				ReflectUtil.setFieldValue(this, name, value);
			}
        }

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
	private void handleExWarehouse(AddWarehouseAndExWarehouseCommand addWarehouseAndExWarehouseCommand) {
		String domainName = addWarehouseAndExWarehouseCommand.getDomainName();
		String componentBackendAbsolutePath = addWarehouseAndExWarehouseCommand.getComponentBackendAbsolutePath();
		// String templateDomainName = addWarehouseAndExWarehouseCommand.getTemplateDomainName();
		String templateDomainName = addWarehouseAndExWarehouseCommand.getIsSingle() ? singleDomainName : pageDomainName;
		String templateComponentBackendAbsolutePath = addWarehouseAndExWarehouseCommand.getTemplateComponentBackendAbsolutePath();

		File domainFile = getFile(domainName, componentBackendAbsolutePath);
		File templateDomainFile = getFile(templateDomainName, templateComponentBackendAbsolutePath);

		String domainFileCommentSimple = readJavaFileCommentSimple(domainFile.getAbsolutePath());
		String templateDomainFileCommentSimple = readJavaFileCommentSimple(templateDomainFile.getAbsolutePath());

        Map<String, String> replaceMap = new LinkedHashMap();
        if (!addWarehouseAndExWarehouseCommand.getIsSingle()) {
            // page
            replaceMap.put(singleDomainName, pageDomainName);
            replaceMap.put(StrUtil.lowerFirst(singleDomainName), StrUtil.lowerFirst(pageDomainName));
        }
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


		// 出库 VO 处理
		// 模板的绝对路径
		String templateExWarehouseVoAbsolutePath = FilePathTool.concat(templateComponentBackendAbsolutePath, templateClient,srcPath, templateExWarehouseVOPath);
		File sourcePropertyVoJavaFile = getFile(domainName + "VO", componentBackendAbsolutePath);
		copyJavaFileAndWriteProperties(templateExWarehouseVoAbsolutePath, sourcePropertyVoJavaFile.getAbsolutePath(), replaceMap);

		// 出库 Command 处理
		String templateExWarehouseQueryCommandAbsolutePath = FilePathTool.concat(templateComponentBackendAbsolutePath, templateClient,srcPath, templateExWarehouseQueryCommandPath);
		copy(templateExWarehouseQueryCommandAbsolutePath, replaceMap);

		// 出库 CommandExecutor 处理
		String templateExWarehouseCommandExecutorAbsolutePath = FilePathTool.concat(templateComponentBackendAbsolutePath, templateApp,srcPath, templateExWarehouseCommandExecutorPath);
		copy(templateExWarehouseCommandExecutorAbsolutePath, replaceMap);
		// 出库 AppStructMapping 添加映射处理
		File appStructMappingJavaFile = getFile(domainName + "AppStructMapping", componentBackendAbsolutePath);
		List<String> appStructMappingMethods = new ArrayList<>();
		appStructMappingMethods.add(templateExWarehouseAppStructMappingMethod1);
		appStructMappingMethods.add(templateExWarehouseAppStructMappingMethod2);
		appStructMappingMethods.add(templateExWarehouseAppStructMappingMethod3);
		List<String> appStructMappingImports = new ArrayList<>();
		appStructMappingImports.add(templateExWarehouseAppStructMappingImport);
		addMethod(appStructMappingJavaFile.getAbsolutePath(), appStructMappingImports, appStructMappingMethods,replaceMap);

		// 出库 RepresentationApplicationService 处理
		File RepresentationApplicationServiceJavaFile = getFile("I" + domainName + "RepresentationApplicationService", componentBackendAbsolutePath);
		List<String> RepresentationApplicationServiceMethods = new ArrayList<>();
		RepresentationApplicationServiceMethods.add(templateExWarehouseRepresentationApplicationServiceMethod);
		List<String> representationApplicationServiceImports = new ArrayList<>();
		representationApplicationServiceImports.add(templateExWarehouseRepresentationApplicationServiceImport1);
		representationApplicationServiceImports.add(templateExWarehouseRepresentationApplicationServiceImport2);
		addMethod(RepresentationApplicationServiceJavaFile.getAbsolutePath(), representationApplicationServiceImports, RepresentationApplicationServiceMethods,replaceMap);

		// 出库 RepresentationApplicationServiceImpl 处理
		File RepresentationApplicationServiceImplJavaFile = getFile(domainName + "RepresentationApplicationServiceImpl", componentBackendAbsolutePath);
		List<String> RepresentationApplicationServiceImplMethods = new ArrayList<>();
		RepresentationApplicationServiceImplMethods.add(templateExWarehouseRepresentationApplicationServiceImplMethod);
		List<String> representationApplicationServiceImplImports = new ArrayList<>();
		representationApplicationServiceImplImports.add(templateExWarehouseRepresentationApplicationServiceImplImport1);
		representationApplicationServiceImplImports.add(templateExWarehouseRepresentationApplicationServiceImplImport2);
		representationApplicationServiceImplImports.add(templateExWarehouseRepresentationApplicationServiceImplImport3);
		List<String> representationApplicationServiceImplFields = new ArrayList<>();
		representationApplicationServiceImplFields.add(templateExWarehouseRepresentationApplicationServiceImplField);
		List<String> representationApplicationServiceImplSetFields = new ArrayList<>();
		representationApplicationServiceImplSetFields.add(templateExWarehouseRepresentationApplicationServiceImplSetField);
		addMethodForImpl(RepresentationApplicationServiceImplJavaFile.getAbsolutePath(),
				representationApplicationServiceImplImports,
				representationApplicationServiceImplFields,
				RepresentationApplicationServiceImplMethods,
				representationApplicationServiceImplSetFields,
				replaceMap);

	}

	/**
	 * 处理入库
	 * @param addWarehouseAndExWarehouseCommand
	 */
	private void handleWarehouse(AddWarehouseAndExWarehouseCommand addWarehouseAndExWarehouseCommand) {
        String domainName = addWarehouseAndExWarehouseCommand.getDomainName();
        String componentBackendAbsolutePath = addWarehouseAndExWarehouseCommand.getComponentBackendAbsolutePath();
        // String templateDomainName = addWarehouseAndExWarehouseCommand.getTemplateDomainName();
        String templateDomainName = addWarehouseAndExWarehouseCommand.getIsSingle() ? singleDomainName : pageDomainName;
        String templateComponentBackendAbsolutePath = addWarehouseAndExWarehouseCommand.getTemplateComponentBackendAbsolutePath();

        File domainFile = getFile(domainName, componentBackendAbsolutePath);
        File templateDomainFile = getFile(templateDomainName, templateComponentBackendAbsolutePath);

        String domainFileCommentSimple = readJavaFileCommentSimple(domainFile.getAbsolutePath());
        String templateDomainFileCommentSimple = readJavaFileCommentSimple(templateDomainFile.getAbsolutePath());

        Map<String, String> replaceMap = new LinkedHashMap();
        if (!addWarehouseAndExWarehouseCommand.getIsSingle()) {
            // page
            replaceMap.put(singleDomainName, pageDomainName);
        }
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


        // 入库 Command 处理
        String templateWarehouseQueryCommandAbsolutePath = FilePathTool.concat(templateComponentBackendAbsolutePath, templateClient,srcPath, templateWarehouseCommandPath);
		File sourcePropertyVoJavaFile = getFile(domainName + "CreateCommand", componentBackendAbsolutePath);
		File copyed = copyJavaFileAndWriteProperties(templateWarehouseQueryCommandAbsolutePath, sourcePropertyVoJavaFile.getAbsolutePath(), replaceMap);
		List<String> sourcePropertyJavaPathLines = FileUtil.readUtf8Lines(copyed.getAbsolutePath());
		List<String> sourcePropertyJavaPathPropertyLines = readJavaFilePropertyLines(sourcePropertyJavaPathLines);
		List<Pair> handleJavaFileLinesProperties = handleJavaFileLinesProperties(sourcePropertyJavaPathPropertyLines);
		String allFieldEmpty = "";
		String compareAndSetNullWhenEquals = "";
		for (int i = 0; i < handleJavaFileLinesProperties.size(); i++) {
			Pair pair = handleJavaFileLinesProperties.get(i);
			String t = "String".equals(pair.getKey()) ? "StrUtil.isEmpty({})" : "Objects.isNull({})";
			t += "\n";

			String t1 = "        if ({}.equals({}, exWarehouseVO.get{}())) {\n" +
					"            this.{} = null;\n" +
					"        }" + "\n";
			if (i == 0) {
				allFieldEmpty += StrUtil.format(t, pair.getValue());
			}else{
				allFieldEmpty += "                && " + StrUtil.format(t, pair.getValue());
			}
			String tool = "BigDecimal".equals(pair.getKey()) ? "NumberUtil" : "Objects";

			compareAndSetNullWhenEquals += StrUtil.format(t1,tool, pair.getValue(), StrUtil.upperFirst(pair.getValue().toString()), pair.getValue());
		}
        List<String> imports = new ArrayList<>();
        imports.add("import java.util.Objects;");
        imports.add("import cn.hutool.core.util.NumberUtil;");
        allFieldEmpty = allFieldEmptyTemplate.replace("{{allFieldEmpty}}", allFieldEmpty);
        compareAndSetNullWhenEquals = compareAndSetNullWhenEqualsTemplate.replace("{{compareAndSetNullWhenEquals}}", compareAndSetNullWhenEquals);
		addMethod(copyed.getAbsolutePath(), imports, Lists.newArrayList(allFieldEmpty,compareAndSetNullWhenEquals),replaceMap);

        String commandFields = "";
        for (int i = 0; i < handleJavaFileLinesProperties.size(); i++) {
            Pair pair = handleJavaFileLinesProperties.get(i);
			if("latestHandleAt".equals(pair.getValue())){
				continue;
			}
            String t = "        command.{} = dataCompanyBasicWarehouseCommand.get{}();\n";
            commandFields += StrUtil.format(t, pair.getValue(), StrUtil.upperFirst(pair.getValue().toString()));
        }
        // 入库 CreateCommand 处理
        File createCommandFile = getFile(domainName + "CreateCommand", componentBackendAbsolutePath);
        String createCommandMethod = createCommandCreateByWarehouseCommandTemplate.replace("{{commandFields}}", commandFields);
        addMethod(createCommandFile.getAbsolutePath(), Lists.newArrayList("import " + templateWarehouseCommand + ";"), Lists.newArrayList(createCommandMethod),replaceMap);

        // 入库 UpdateCommand 处理

        File updateCommandFile = getFile(domainName + "UpdateCommand", componentBackendAbsolutePath);
        String updateCommandMethod = updateCommandCreateByWarehouseCommandTemplate.replace("{{commandFields}}", commandFields);
        addMethod(updateCommandFile.getAbsolutePath(), Lists.newArrayList("import " + templateWarehouseCommand + ";"), Lists.newArrayList(updateCommandMethod),replaceMap);

		// 入库 CommandExecutor 处理
        String templateWarehouseCommandExecutorAbsolutePath = FilePathTool.concat(templateComponentBackendAbsolutePath, templateApp,srcPath, templateWarehouseCommandExecutorPath);
        copy(templateWarehouseCommandExecutorAbsolutePath, replaceMap);


        // 入库 ApplicationService 处理
        File ApplicationServiceJavaFile = getFile("I" + domainName + "ApplicationService", componentBackendAbsolutePath);
        List<String> ApplicationServiceMethods = new ArrayList<>();
        ApplicationServiceMethods.add(templateWarehouseApplicationServiceMethod);
        List<String> representationApplicationServiceImports = new ArrayList<>();
        representationApplicationServiceImports.add(templateWarehouseApplicationServiceImport1);
        representationApplicationServiceImports.add(templateWarehouseApplicationServiceImport2);
        addMethod(ApplicationServiceJavaFile.getAbsolutePath(), representationApplicationServiceImports, ApplicationServiceMethods,replaceMap);

        // 入库 ApplicationServiceImpl 处理
        File ApplicationServiceImplJavaFile = getFile(domainName + "ApplicationServiceImpl", componentBackendAbsolutePath);
        List<String> ApplicationServiceImplMethods = new ArrayList<>();
        ApplicationServiceImplMethods.add(templateWarehouseApplicationServiceImplMethod);
        List<String> representationApplicationServiceImplImports = new ArrayList<>();
        representationApplicationServiceImplImports.add(templateWarehouseApplicationServiceImplImport1);
        representationApplicationServiceImplImports.add(templateWarehouseApplicationServiceImplImport2);
        representationApplicationServiceImplImports.add(templateWarehouseApplicationServiceImplImport3);
        List<String> representationApplicationServiceImplFields = new ArrayList<>();
        representationApplicationServiceImplFields.add(templateWarehouseApplicationServiceImplField);
        List<String> representationApplicationServiceImplSetFields = new ArrayList<>();
        representationApplicationServiceImplSetFields.add(templateWarehouseApplicationServiceImplSetField);
        addMethodForImpl(ApplicationServiceImplJavaFile.getAbsolutePath(),
                representationApplicationServiceImplImports,
                representationApplicationServiceImplFields,
                ApplicationServiceImplMethods,
                representationApplicationServiceImplSetFields,
                replaceMap);
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
	 * 处理java文件的属性行
	 * @param javaFileLines
	 * @return
	 */
	private List<Pair> handleJavaFileLinesProperties(List<String> javaFileLines) {
		List<Pair> result = new ArrayList<>();
		for (String javaFileLine : javaFileLines) {
			if (javaFileLine.contains("private")) {
				String[] split = javaFileLine.split(" ");
				String type = split[split.length - 2];
				String name = split[split.length - 1];
				result.add(Pair.of(type,name.replace(";","")));
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
	 * 复制java文件并写入属性
	 * @param sourceJavaPath 源文件java路径
	 * @param sourcePropertyJavaPath 待写入的属性的java路径
	 * @param replaceMap 替换的map
	 */
	private File copyJavaFileAndWriteProperties(String sourceJavaPath,String sourcePropertyJavaPath, Map<String,String> replaceMap) {
		File destFile = copy(sourceJavaPath, replaceMap);
		List<String> sourcePropertyJavaPathLines = FileUtil.readUtf8Lines(sourcePropertyJavaPath);
		List<String> sourcePropertyJavaPathPropertyLines = readJavaFilePropertyLines(sourcePropertyJavaPathLines);

		List<String> destFileLines = FileUtil.readUtf8Lines(destFile);
		List<String> destFileLinesWrite = writeJavaFilePropertyLines(destFileLines, sourcePropertyJavaPathPropertyLines);
		FileUtil.writeUtf8Lines(destFileLinesWrite, destFile );

		return destFile;
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

	/**
	 * 添加方法
	 * @param javaFilePath
	 * @param importLines
	 * @param methodLines
	 */
	private void addMethod(String javaFilePath,List<String> importLines, List<String> methodLines,Map<String,String> replaceMap) {
		List<String> readUtf8Lines = FileUtil.readUtf8Lines(javaFilePath);
		int lastImportIndex = 0;
		int lastRightBraceIndex = 0;
		for (int i = 0; i < readUtf8Lines.size(); i++) {
			if (readUtf8Lines.get(i).contains("import")) {
				lastImportIndex = i;
			}
			if (readUtf8Lines.get(i).contains("}")) {
				lastRightBraceIndex = i;
			}
		}
		methodLines = handleReplaceMapList(methodLines,replaceMap);
		importLines = handleReplaceMapList(importLines,replaceMap);

		readUtf8Lines.addAll(lastRightBraceIndex, methodLines);
		readUtf8Lines.addAll(lastImportIndex + 1, importLines);

		FileUtil.writeUtf8Lines(readUtf8Lines, javaFilePath);
	}
	/**
	 * 添加方法
	 * @param javaFilePath
	 * @param importLines
	 * @param methodLines
	 */
	private void addMethodForImpl(String javaFilePath,
								  List<String> importLines,
								  List<String> fieldLines,
								  List<String> methodLines,
								  List<String> setFieldLines,
								  Map<String,String> replaceMap) {
		List<String> readUtf8Lines = FileUtil.readUtf8Lines(javaFilePath);
		int lastImportIndex = 0;
		int firstOverrideIndex = 0;
		int firstAutowireIndex = 0;
		int lastRightBraceIndex = 0;
		for (int i = 0; i < readUtf8Lines.size(); i++) {
			if (readUtf8Lines.get(i).contains("import")) {
				lastImportIndex = i;
			}
			if (readUtf8Lines.get(i).contains("@Override")) {
				if (firstOverrideIndex == 0) {
					firstOverrideIndex = i;
				}
			}
			if (readUtf8Lines.get(i).contains("@Autowired")) {
				if (firstAutowireIndex == 0) {
					firstAutowireIndex = i;
				}
			}
			if (readUtf8Lines.get(i).contains("}")) {
				lastRightBraceIndex = i;
			}
		}
		methodLines = handleReplaceMapList(methodLines,replaceMap);
		fieldLines = handleReplaceMapList(fieldLines,replaceMap);
		importLines = handleReplaceMapList(importLines,replaceMap);
		setFieldLines = handleReplaceMapList(setFieldLines,replaceMap);

		readUtf8Lines.addAll(lastRightBraceIndex, setFieldLines);
		readUtf8Lines.addAll(firstAutowireIndex - 1, methodLines);
		readUtf8Lines.addAll(firstOverrideIndex - 1, fieldLines);
		readUtf8Lines.addAll(lastImportIndex + 1, importLines);

		FileUtil.writeUtf8Lines(readUtf8Lines, javaFilePath);
	}
}
