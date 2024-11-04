package com.particle.generator.infrastructure.generator.table;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.util.FileUtils;
import com.particle.generator.domain.OutputFileEnum;
import com.particle.generator.domain.component.TableGenerateConf;
import com.particle.global.datasource.sqlinit.CustomDataSourceScriptDatabaseInitializer;
import com.particle.global.tool.str.NetPathTool;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 增强freemarker 模板功能
 * </p>
 *
 * @author yangwei
 * @since 2022-07-07 14:52
 */
@Slf4j
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

	@Override
	protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {

		//String otherPath = getPathInfo(OutputFile.other);
		TableGenerateConf tableGenerateConf = InjectionConfigLogic.tableGenerateConfFromObjectMap(objectMap);
		String otherPath = tableGenerateConf.getOutputAbsoluteDir();
		Map<OutputFileEnum, InjectionConfigLogic.TemplateAndFileName> outputFileEnumTemplateAndFileNameMap = InjectionConfigLogic.injectionTemplateConfigMap(objectMap);
		Map<OutputFileEnum, InjectionConfigLogic.TemplateAndFileName> customFile = InjectionConfigLogic.customFileFromObjectMap(objectMap);
		Map<String, Object> customMap = InjectionConfigLogic.customMapFromObjectMap(objectMap);
		// key为文件名称，value为模板路径
		// 在定义时key中带需要替换的变量
		customFile.forEach((outputFileEnum, templateAndFileName) -> {

			// 生成文件的绝对路径片段，最终组装成最终的绝对路径
			List<String> pathSegment = new ArrayList<>();
			pathSegment.add(otherPath);
			InjectionConfigLogic.InjectionData injectionData = null;
			injectionData = InjectionConfigLogic.injectionDataFromObjectMap(objectMap, outputFileEnum);

			pathSegment.add(StrUtil.nullToEmpty(injectionData.getRelativeSubPath()));
			pathSegment.add(StrUtil.nullToEmpty(injectionData.getPkgPath()));

			// 填充其它属性，这些属性在customMap中调用的 InjectionData.create获取不到一些数据，如：表信息
			pathSegment.add(templateAndFileName.getFileName());

			// 最终组装成最终的绝对路径
			String fileAbsolutePath = null;
			for (int i = 0; i < pathSegment.size(); i++) {
				String segment = pathSegment.get(i);
				// 第一个就是子模块的绝对路径，直接做为开始
				if (i == 0) {
					// 子模板的绝对路径不能为空，如果为空可能生成的路径不正确，这里没有做校验
					fileAbsolutePath = segment;
				}else {
					fileAbsolutePath = NetPathTool.concat(fileAbsolutePath,segment);
				}
			}

			if (tableGenerateConf.getFileDelete()) {
				FileUtil.del(fileAbsolutePath);
				log.info("删除文件:templatePath={},outputFile={}",templateAndFileName.getTemplate(),fileAbsolutePath);
			}else {

				// 将注入属性平铺，可以不用考虑当前模板，少写一个 outputFileEnum 层级
				Map<String, Object> injection = InjectionConfigLogic.injectionFromObjectMap(objectMap);
				Map<String, Object> injectionDataMap = injectionData.toMap();
				injection.putAll(injectionDataMap);

				outputFile(new File(fileAbsolutePath), objectMap, templateFilePath(templateAndFileName.getTemplate()),templateAndFileName.getFileOverride());

				// schema处理
				if (outputFileEnum == OutputFileEnum.tableDdl) {
					// 命名需要和 component/componenttemplate/componenttemplate-infrastructure/src/main/resources/db/schema.componenttemplate.sql 一致
					String schemaSqlFileName = StrUtil.format("schema.{}.sql",tableGenerateConf.getComponentModuleName());
					String parent = FileUtil.getParent(fileAbsolutePath, 1);
					String schemaPath = NetPathTool.concat(parent,schemaSqlFileName);

					String singleSqlName = templateAndFileName.getFileName();
					String importLocation = CustomDataSourceScriptDatabaseInitializer.IMPORT_PREFIX + "classpath:"+ InjectionConfigLogic.tableDdlClassPath +"/" + singleSqlName;

					if (FileUtil.exist(schemaPath)) {

						String content = FileUtil.readUtf8String(schemaPath);
						if(StrUtil.isEmpty(content) || !content.contains(singleSqlName)){
							// importLocation 如：-- import classpath:db/schema.component_area.sql
							FileUtil.appendUtf8String(System.getProperty("line.separator") + importLocation, schemaPath);
						}
					}else {
						log.warn("schemaPath={} not exist,importLocation like ({}) maybe used",schemaPath,importLocation);
					}
				}
				//	生成完成后移除注入的平铺map
				injectionDataMap.forEach((key,value)->{
					injection.remove(key, value);
				});
			}
		});
	}

	/**
	 * 重写父级方法，主要是为了打印日志
	 * @param file
	 * @param objectMap
	 * @param templatePath
	 */
	@Override
	protected void outputFile(File file, Map<String, Object> objectMap, String templatePath, boolean fileOverride) {
		if (isCreate(file,fileOverride)) {
			try {
				// 全局判断【默认】
				boolean exist = file.exists();
				if (!exist) {
					File parentFile = file.getParentFile();
					FileUtils.forceMkdir(parentFile);
				}
				writer(objectMap, templatePath, file);
				log.info("生成文件:templatePath={},outputFile={}",templatePath,file.getAbsolutePath());
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}	}

	@Override
	public AbstractTemplateEngine batchOutput() {
		try {
			ConfigBuilder config = this.getConfigBuilder();
			List<TableInfo> tableInfoList = config.getTableInfoList();
			tableInfoList.forEach(tableInfo -> {
				Map<String, Object> objectMap = this.getObjectMap(config, tableInfo);
				// 数据源配置放进来，自定义注入
				objectMap.put("dataSourceConfig", config.getDataSourceConfig());
				Optional.ofNullable(config.getInjectionConfig()).ifPresent(t -> {
					t.beforeOutputFile(tableInfo, objectMap);
					// 输出自定义文件
					outputCustomFile(t.getCustomFiles(), tableInfo, objectMap);
				});
				// Mp.java
				outputEntity(tableInfo, objectMap);
				// mapper and xml
				outputMapper(tableInfo, objectMap);
				// service
				outputService(tableInfo, objectMap);
				// MpController.java
				outputController(tableInfo, objectMap);
			});
		} catch (Exception e) {
			throw new RuntimeException("无法创建文件，请检查配置信息！", e);
		}finally {
			ConfigBuilder config = this.getConfigBuilder();
			Connection conn = config.getDataSourceConfig().getConn();
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException throwables) {
				}
			}
		}
		return this;
	}
}
