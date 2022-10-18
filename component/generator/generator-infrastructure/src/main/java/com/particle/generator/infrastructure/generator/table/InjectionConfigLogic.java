package com.particle.generator.infrastructure.generator.table;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.google.common.collect.Lists;
import com.particle.generator.domain.MethodEnum;
import com.particle.generator.domain.OutputFileEnum;
import com.particle.generator.domain.RelationConstants;
import com.particle.generator.domain.SubModule;
import com.particle.generator.domain.component.OutputFileConf;
import com.particle.generator.domain.component.TableGenerateConf;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义注入逻辑
 * 主要是添加额外模板
 * </p>
 *
 * @author yangwei
 * @since 2022-07-07 14:18
 */
@Slf4j
public class InjectionConfigLogic {

	/**
	 * 自定义注入数据都放在这个key下面
	 */
	public static String injection = "injection";
	/**
	 * 表配置
	 */
	private static String tableGenerateConf = "tableGenerateConf";
	/**
	 * 自定义模板和文件
	 */
	private static String customFile = "customFile";
	/**
	 * 自定义map
	 */
	private static String customMap = "customMap";
	/**
	 * 模板配置
	 */
	private static String injectionTemplateConfigMap = "injectionTemplateConfigMap";

	public static String tableDdlClassPath = "db";

	/**
	 * 入口方法配置
	 * @param builder
	 * @param tableGenerateConf
	 */
	public static void config(InjectionConfig.Builder builder, TableGenerateConf tableGenerateConf) {
		builder.beforeOutputFile((tableInfo, objectMap) -> {

			Map<String, Object> map = new HashMap<>();
			// 自定义注入根属性
			objectMap.put(injection, map);
			String entityName = tableInfo.getEntityName();
			String originEntityName = StrategyConfigLogic.removeEntitySuffix(entityName);

			Map<OutputFileEnum, TemplateAndFileName> outputFileEnumTemplateAndFileNameMap = injectionTemplateConfigMap(StrUtil.upperFirst(originEntityName), tableInfo.getName());
			// 注入自定义属性
			map.put(InjectionConfigLogic.tableGenerateConf, tableGenerateConf);
			map.put(InjectionConfigLogic.customFile, customFile(tableInfo, objectMap,outputFileEnumTemplateAndFileNameMap,tableGenerateConf));
			Map<String, Object> customMap = customMap(tableInfo, objectMap, outputFileEnumTemplateAndFileNameMap, tableGenerateConf);
			map.put(InjectionConfigLogic.customMap, customMap);
			map.put(InjectionConfigLogic.injectionTemplateConfigMap, outputFileEnumTemplateAndFileNameMap);
			// customMap 是所有要渲染的数据，这里平铺到 injection 里
			map.putAll(customMap);


		});
	}

	/**
	 * 自定义文件
	 * @param tableGenerateConf
	 * @return key为文件名称，value为模板路径
	 */
	private static Map<OutputFileEnum, TemplateAndFileName> customFile(TableInfo tableInfo, Map<String, Object> objectMap, Map<OutputFileEnum, TemplateAndFileName> outputFileEnumTemplateAndFileNameMap,TableGenerateConf tableGenerateConf) {
		Map<OutputFileEnum, TemplateAndFileName> map = new HashMap<>();
		/**
		 * 一个module只能生成这么多的文件
		 */
		List<OutputFileEnum> outputFileEnums = RelationConstants.subModuleAndOutputFileRelation.get(tableGenerateConf.getSubModule());
		for (OutputFileConf outputFileConf : tableGenerateConf.getOutputFileConfs()) {
			OutputFileEnum outputFileEnum = outputFileConf.getOutputFileEnum();
			if (outputFileEnums.contains(outputFileEnum)) {
				TemplateAndFileName templateAndFileName = outputFileEnumTemplateAndFileNameMap.get(outputFileEnum);
				map.put(outputFileEnum, templateAndFileName);
			}else {
				log.debug("can not generate file {} in subModule {},ignored!",outputFileEnum.name(),tableGenerateConf.getSubModule().name());
			}
		}

		return map;
	}

	/**
	 * 自定义数据入口
	 * @param tableGenerateConf
	 * @return
	 */
	private static Map<String, Object> customMap(TableInfo tableInfo, Map<String, Object> objectMap,Map<OutputFileEnum, TemplateAndFileName> outputFileEnumTemplateAndFileNameMap, TableGenerateConf tableGenerateConf) {

		return doCustomMap(tableInfo,objectMap,outputFileEnumTemplateAndFileNameMap,tableGenerateConf);
	}

	/**
	 * 注入自定义数据
	 *
	 * @param tableGenerateConf
	 * @return
	 */
	private static Map<String, Object> doCustomMap(TableInfo tableInfo,
												   Map<String, Object> objectMap,
												   Map<OutputFileEnum, TemplateAndFileName> outputFileEnumTemplateAndFileNameMap,
												   TableGenerateConf tableGenerateConf) {
		Map<String, Object> injection = new HashMap<>();
		// 将配置放到渲染数据中方便使用
		injection.put(InjectionConfigLogic.tableGenerateConf, tableGenerateConf);
		DataSourceConfig dataSourceConfig = (DataSourceConfig) objectMap.get("dataSourceConfig");
		Connection conn = dataSourceConfig.getConn();

		String tableSql = createTableSql(conn, tableInfo.getName());
		Arrays.stream(OutputFileEnum.values()).forEach(outputFileEnum -> {
			TemplateAndFileName templateAndFileName = outputFileEnumTemplateAndFileNameMap.get(outputFileEnum);
			SubModule subModule = RelationConstants.ownerSubModule(outputFileEnum);
			List<String> pkgSegmentList = new ArrayList<>();
			if (!templateAndFileName.getIgnoreParentPackage()) {
				pkgSegmentList.add(StrUtil.nullToEmpty(tableGenerateConf.getPackageParent()));
				pkgSegmentList.add(StrUtil.nullToEmpty(Optional.ofNullable(subModule).map(SubModule::subModuleNameToPkg).orElse(null)));
			}
			pkgSegmentList.add(StrUtil.nullToEmpty(tableGenerateConf.getPackageModuleName()));
			pkgSegmentList.add(StrUtil.nullToEmpty(templateAndFileName.getPackageName()));
			// 为空的过滤掉
			pkgSegmentList = pkgSegmentList.stream().filter(StrUtil::isNotEmpty).collect(Collectors.toList());
			// 转一下实体，主要是去掉DO
			String entityName = tableInfo.getEntityName();
			entityName = StrategyConfigLogic.removeEntitySuffix(entityName);

			OutputFileConf outputFileEnumOutputFileConf = tableGenerateConf.getOutputFileConfs().stream().filter(outputFileConf -> outputFileConf.getOutputFileEnum() == outputFileEnum).findFirst().get();
			Map<String, Boolean> method = new HashMap<>();
			for (MethodEnum value : MethodEnum.values()) {
				method.put(value.name(), outputFileEnumOutputFileConf.getMethodEnums().contains(value));

			}
			// 注入数据
			InjectionData injectionData = InjectionData.create(
					String.join(StringPool.DOT, pkgSegmentList),
					Lists.newArrayList(),
					// 暂时为空，这在模板里写死了
					null,
					templateAndFileName.getUrlPrefix(),
					templateAndFileName.getRelativeSubPath(),
					templateAndFileName.getClassName(),
					templateAndFileName.getAuthorityPrefix(),
					StrUtil.removeSuffix(tableInfo.getComment(),"表"),
					StringUtils.camelToHyphen(entityName),
					StrUtil.lowerFirst(entityName),
					method,
					StrUtil.lowerFirst(entityName),
					StrUtil.lowerFirst(entityName),
					tableSql
			);

			injection.put(outputFileEnum.name(), injectionData);
		});
		return injection;
	}


	/**
	 * 获取建表语句
	 * 仅支持mysql
	 * @param connection
	 * @param tableName
	 * @return
	 */
	private static String createTableSql(Connection connection, String tableName) {
		try (
				Connection conn = connection;
				PreparedStatement preparedStatement = conn.prepareStatement("show create table " + tableName);
				ResultSet results = preparedStatement.executeQuery()) {
			while (results.next()) {
				String createTableSql = results.getString("Create Table");
				return "DROP TABLE IF EXISTS "+ tableName +";"
						+ System.getProperty("line.separator")
						+ createTableSql + ";"
						+ System.getProperty("line.separator");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception：" + e.getMessage());
		}
		return "仅支持mysql";
	}

	/**
	 * 自定义输出文件和模板与文件名
	 * 注意，模板名称和文件名称需要作为唯一键，在输入模板时需要使用
	 */
	public static Map<OutputFileEnum, TemplateAndFileName> injectionTemplateConfigMap(String entityFirstUpper,String tableName) {
		Map<OutputFileEnum, TemplateAndFileName> injectionTemplateConfigMap = new HashMap<>();
		/**
		 * adapter
		 */
		injectionTemplateConfigMap.put(OutputFileEnum.adminWebController,
				TemplateAndFileName.create("/templates/adapter/adminWebController.java",
						entityFirstUpper + "AdminWebController.java",
						"web.admin","/admin/web","/src/main/java","admin:web",false));
		injectionTemplateConfigMap.put(OutputFileEnum.adminWapController,
				TemplateAndFileName.create("/templates/adapter/adminWapController.java",
						entityFirstUpper + "AdminWapController.java",
						"wap.admin","/admin/wap","/src/main/java","admin:wap",false));
		injectionTemplateConfigMap.put(OutputFileEnum.adminMobileController,
				TemplateAndFileName.create("/templates/adapter/adminMobileController.java",
						entityFirstUpper + "AdminMobileController.java",
						"mobile.admin","/admin/mobile","/src/main/java","admin:mobile",false));
		injectionTemplateConfigMap.put(OutputFileEnum.frontWebController,
				TemplateAndFileName.create("/templates/adapter/frontWebController.java",
						entityFirstUpper + "FrontWebController.java",
						"web.front","/front/web","/src/main/java","front:web",false));
		injectionTemplateConfigMap.put(OutputFileEnum.frontWapController,
				TemplateAndFileName.create("/templates/adapter/frontWapController.java",
						entityFirstUpper + "FrontWapController.java",
						"wap.front","/front/wap","/src/main/java","front:wap",false));
		injectionTemplateConfigMap.put(OutputFileEnum.frontMobileController,
				TemplateAndFileName.create("/templates/adapter/frontMobileController.java",
						entityFirstUpper + "FrontMobileController.java",
						"mobile.front","/front/mobile","/src/main/java","front:mobile",false));
		injectionTemplateConfigMap.put(OutputFileEnum.rpcController,
				TemplateAndFileName.create("/templates/adapter/rpcController.java",
						entityFirstUpper + "RpcController.java",
						"rpc","/rpc","/src/main/java","",false));
		/**
		 * feign-client
		 */

		injectionTemplateConfigMap.put(OutputFileEnum.rpcFeignClient,
				TemplateAndFileName.create("/templates/adapter-feign-client/rpcFeignClient.java",
						entityFirstUpper + "RpcFeignClient.java",
						"rpc","/rpc","/src/main/java","",false));
		/**
		 * app
		 */
		injectionTemplateConfigMap.put(OutputFileEnum.applicationServiceImpl,
				TemplateAndFileName.create("/templates/app/applicationServiceImpl.java",
						entityFirstUpper + "ApplicationServiceImpl.java"
						,"api.impl",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.representationApplicationServiceImpl,
				TemplateAndFileName.create("/templates/app/representationApplicationServiceImpl.java",
						entityFirstUpper + "RepresentationApplicationServiceImpl.java"
						,"api.impl.representation",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.createCommandExecutor,
				TemplateAndFileName.create("/templates/app/createCommandExecutor.java",
						entityFirstUpper + "CreateCommandExecutor.java",
						"executor",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.updateCommandExecutor,
				TemplateAndFileName.create("/templates/app/updateCommandExecutor.java",
						entityFirstUpper + "UpdateCommandExecutor.java",
						"executor",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.deleteCommandExecutor,
				TemplateAndFileName.create("/templates/app/deleteCommandExecutor.java",
						entityFirstUpper + "DeleteCommandExecutor.java",
						"executor",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.queryCommandExecutor,
				TemplateAndFileName.create("/templates/app/queryCommandExecutor.java",
						entityFirstUpper + "QueryCommandExecutor.java",
						"executor.representation",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.appStructMapping,
				TemplateAndFileName.create("/templates/app/appStructMapping.java",
						entityFirstUpper + "AppStructMapping.java",
						"structmapping",null,"/src/main/java","",false));
		/**
		 * client
		 */
		injectionTemplateConfigMap.put(OutputFileEnum.applicationService,
				TemplateAndFileName.create("/templates/client/applicationService.java",
						"I" + entityFirstUpper + "ApplicationService.java",
						"api",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.representationApplicationService,
				TemplateAndFileName.create("/templates/client/representationApplicationService.java",
						"I" + entityFirstUpper + "RepersentationApplicationService.java",
						"api.representation",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.createCommand,
				TemplateAndFileName.create("/templates/client/createCommand.java",
						entityFirstUpper + "CreateCommand.java",
						"dto.command",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.updateCommand,
				TemplateAndFileName.create("/templates/client/updateCommand.java",
						entityFirstUpper + "UpdateCommand.java",
						"dto.command",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.queryDetailForUpdateCommand,
				TemplateAndFileName.create("/templates/client/queryDetailForUpdateCommand.java",
						entityFirstUpper + "QueryDetailForUpdateCommand.java",
						"dto.command.representation",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.queryDetailCommand,
				TemplateAndFileName.create("/templates/client/queryDetailCommand.java",
						entityFirstUpper + "QueryDetailCommand.java",
						"dto.command.representation",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.deleteCommand,
				TemplateAndFileName.create("/templates/client/deleteCommand.java",
						entityFirstUpper + "DeleteCommand.java",
						"dto.command",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.queryListCommand,
				TemplateAndFileName.create("/templates/client/queryListCommand.java",
						entityFirstUpper + "QueryListCommand.java",
						"dto.command.representation",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.pageQueryCommand,
				TemplateAndFileName.create("/templates/client/pageQueryCommand.java",
						entityFirstUpper + "PageQueryCommand.java",
						"dto.command.representation",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.vo,
				TemplateAndFileName.create("/templates/client/vo.java",
						entityFirstUpper + "VO.java",
						"dto.data",null,"/src/main/java","",false));
		/**
		 * domain
		 */
		injectionTemplateConfigMap.put(OutputFileEnum.domainObject,
				TemplateAndFileName.create("/templates/domain/domainObject.java",
						entityFirstUpper + ".java",
						"",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.gateway,
				TemplateAndFileName.create("/templates/domain/gateway.java",
						entityFirstUpper + "Gateway.java",
						"gateway",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.idObject,
				TemplateAndFileName.create("/templates/domain/idObject.java",
						entityFirstUpper + "Id.java",
						"",null,"/src/main/java","",false));
		/**
		 * infrastructure
		 */
		injectionTemplateConfigMap.put(OutputFileEnum.gatewayImpl,
				TemplateAndFileName.create("/templates/infrastructure/gatewayImpl.java",
						entityFirstUpper + "GatewayImpl.java",
						"gateway.impl",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.infrastructureStructMapping,
				TemplateAndFileName.create("/templates/infrastructure/infrastructureStructMapping.java",
						entityFirstUpper + "InfrastructureStructMapping.java",
						"structmapping",null,"/src/main/java","",false));
		/**
		 * mybatisplus 原始文件
		 * 去掉了controller和other
		 * 用在基础设施层
		 */
		injectionTemplateConfigMap.put(OutputFileEnum.entity,
				TemplateAndFileName.create("/templates/infrastructure/entity.java",
						entityFirstUpper + "DO.java",
						"dos",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.service,
				TemplateAndFileName.create("/templates/infrastructure/service.java",
						"I"+ entityFirstUpper +"Service.java",
						"service",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.serviceImpl,
				TemplateAndFileName.create("/templates/infrastructure/serviceImpl.java",
						entityFirstUpper + "ServiceImpl.java",
						"service.impl",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.mapper,
				TemplateAndFileName.create("/templates/infrastructure/mapper.java",
						entityFirstUpper + "Mapper.java",
						"mapper",null,"/src/main/java","",false));
		injectionTemplateConfigMap.put(OutputFileEnum.mapperXml,
				TemplateAndFileName.create("/templates/infrastructure/mapper.xml",
						entityFirstUpper + "Mapper.xml",
						"",null,"/src/main/resources/mapper","",true));
		/**
		 * 建表语句
		 */
		injectionTemplateConfigMap.put(OutputFileEnum.tableDdl,
				TemplateAndFileName.create("/templates/infrastructure/tableDdl.sql",
						"schema."+ tableName +".sql",
						"",null,"/src/main/resources/" + tableDdlClassPath,"",true));
		return injectionTemplateConfigMap;
	}

	/**
	 * 模板相关性类
	 */
	@Data
	@Builder
	public static class TemplateAndFileName{
		private String template;
		
		private String fileName;
		/**
		 * java文件内容类名
		 * 不带包名
		 */
		private String className;
		/**
		 * 包名
		 * 非全限定包名，只是最后一级
		 */
		private String packageName;
		/**
		 * 请求 controller 前缀
		 */
		private String urlPrefix;
		/**
		 * 子模块的子级相对路径
		 *  如：src/main/java
		 */
		private String relativeSubPath;
		/**
		 * 权限前缀
		 */
		private String authorityPrefix;

		/**
		 * 忽略父级pkg，不再拼接
		 * 主要是mapper.xml等不需要
		 */
		private Boolean ignoreParentPackage;
		
		
		/**
		 * 模板相关性对象
		 * @param template
		 * @param fileName
		 * @return
		 */
		private static TemplateAndFileName create(String template,
												  String fileName,
												  String packageName,
												  String urlPrefix,
												  String relativeSubPath,
												  String authorityPrefix,
												  Boolean ignoreParentPackage){
			return TemplateAndFileName.builder()
					.template(template)
					.fileName(fileName)
					.className(fileName.endsWith(StringPool.DOT_JAVA) ? fileName.replace(StringPool.DOT_JAVA,"") : null)
					.packageName(packageName)
					.urlPrefix(urlPrefix)
					.relativeSubPath(relativeSubPath)
					.authorityPrefix(authorityPrefix)
					.ignoreParentPackage(ignoreParentPackage)
					.build();
		}
	}


	/**
	 * 从objectMap获取 InjectionData
	 * InjectionData 最终是放到 objectMap中的，可以从里面获取到，但注意获取时机
	 * @param objectMap
	 * @return
	 */
	public static InjectionData injectionDataFromObjectMap(Map<String, Object> objectMap,OutputFileEnum outputFileEnum) {
		return Optional.ofNullable(objectMap)
				.map(m -> ((HashMap<String, Object>) m.get(injection)))
				.map(m -> ((HashMap<String, Object>) m.get(customMap)))
				.map(m -> ((InjectionData) m.get(outputFileEnum.name())))
				.orElse(null);
	}

	/**
	 * 自定义注入的map获取
	 * @param objectMap
	 * @return
	 */
	public static Map<String,Object> injectionFromObjectMap(Map<String, Object> objectMap) {
		return Optional.ofNullable(objectMap)
				.map(m -> ((HashMap<String, Object>) m.get(injection)))
				.orElse(null);
	}

	public static Map<OutputFileEnum, TemplateAndFileName> customFileFromObjectMap(Map<String, Object> objectMap) {
		return Optional.ofNullable(objectMap)
				.map(m -> ((HashMap<String, Object>) m.get(injection)))
				.map(m -> ((HashMap<OutputFileEnum, TemplateAndFileName>) m.get(customFile)))
				.orElse(null);
	}
	public static Map<String,Object> customMapFromObjectMap(Map<String, Object> objectMap) {
		return Optional.ofNullable(objectMap)
				.map(m -> ((HashMap<String, Object>) m.get(injection)))
				.map(m -> ((HashMap<String, Object>) m.get(customMap)))
				.orElse(null);
	}
	/**
	 * 自定义map中已放入
	 * @param objectMap
	 * @return
	 */
	public static TableGenerateConf tableGenerateConfFromObjectMap(Map<String, Object> objectMap) {
		return Optional.ofNullable(objectMap)
				.map(m -> ((HashMap<String, Object>) m.get(injection)))
				.map(m -> ((TableGenerateConf) m.get(tableGenerateConf)))
				.orElse(null);
	}
	public static Map<OutputFileEnum, TemplateAndFileName> injectionTemplateConfigMap(Map<String, Object> objectMap) {
		return Optional.ofNullable(objectMap)
				.map(m -> ((HashMap<String, Object>) m.get(injection)))
				.map(m -> ((Map<OutputFileEnum, TemplateAndFileName>) m.get(injectionTemplateConfigMap)))
				.orElse(null);
	}
	/**
	 * 自定义注入的对象
	 */
	@Data
	@Builder
	public static class InjectionData{
		/**
		 * package 为关键字，改为pkg
		 * 全限定名
		 */
		private String pkg;
		/**
		 * pkg对应的路径
		 */
		private String pkgPath;
		/**
		 * 导入包，带import关键字
		 */
		private List<String> imports;
		/**
		 * 父类名，继承的父类名
		 * 全限定名
		 */
		private String superClass;

		/**
		 * 父类名，首字母大写的类名
		 */
		private String superClassName;
		/**
		 * 请求 controller 前缀
		 */
		private String urlPrefix;

		/**
		 * 子模块的子级相对路径
		 *  如：src/main/java
		 */
		private String relativeSubPath;

		/**
		 * java文件内容类名
		 * 不带包名
		 */
		private String className;

		/**
		 * java文件内容类名变量使用
		 */
		private String classNameVar;
		/**
		 * 权限前缀，主要用于controller
		 */
		private String authorityPrefix;


		/**
		 * 表注释
		 */
		private String tableComment;
		/**
		 * urlpath，拼接在 url 前缀后面
		 */
		private String entityUrlPath;
		/**
		 * entityAuthority，拼接在 authority 前缀后面
		 */
		private String entityAuthority;

		/**
		 * 包括的方法 key应该为 MethodEnum 的name字符值
		 */
		private Map<String,Boolean> method;
		/**
		 * 默认的feignClient配置名称
		 * 用于变量配置，一般建议使用实体名称首字母小写
		 */
		private String feignClientConfigName;
		/**
		 * 默认的feignClient名称
		 * 用于默认值，一般建议使用实体名称首字母小写
		 */
		private String feignClientDefaultName;
		/**
		 * 建表语句
		 */
		private String createTableSql;

		/**
		 * 构建
		 * @param pkg
		 * @param imports
		 * @param superClass
		 * @return
		 */
		public static InjectionData create(String pkg,
										   List<String> imports,
										   String superClass,
										   String urlPrefix,
										   String relativeSubPath,
										   String className,
										   String authorityPrefix,
										   String tableComment,
										   String entityUrlPath,
										   String entityAuthority,
										   Map<String,Boolean> method,
										   String feignClientConfigName,
										   String feignClientDefaultName,
										   String createTableSql) {
			return InjectionData.builder()
					.pkg(pkg)
					.imports(imports)
					.superClass(superClass)
					.superClassName(superClass == null ? superClass : superClass.substring(superClass.lastIndexOf(StringPool.DOT)))
					.pkgPath(TableGenerateConf.pkgPath(pkg))
					.urlPrefix(urlPrefix)
					.relativeSubPath(relativeSubPath)
					.className(className)
					.classNameVar(StrUtil.lowerFirst(className))
					.authorityPrefix(authorityPrefix)
					.tableComment(tableComment)
					.entityUrlPath(entityUrlPath)
					.entityAuthority(entityAuthority)
					.method(method)
					.feignClientConfigName(feignClientConfigName)
					.feignClientDefaultName(feignClientDefaultName)
					.createTableSql(createTableSql)
					.build();
		}

		/**
		 * 转为map
		 * @return
		 */
		public Map<String, Object> toMap() {
			return BeanUtil.beanToMap(this);
		}

	}

}
