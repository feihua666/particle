package com.particle.generator.test;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.generator.domain.AdapterLogicType;
import com.particle.generator.domain.AdapterType;
import com.particle.generator.domain.TableType;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * <p>
 * mybatis plus 新版本生成
 * </p>
 *
 * @author yangwei
 * @since 2022-07-01 10:42
 */
@Slf4j
public class MybatisPlusGeneratorTest {
	public static String datasourceUrl = "jdbc:mysql://localhost/particle_dev?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";
	public static String datasourceUsername = "root";
	public static String datasourcePassword = "rootroot";

	public static String globalAuthor = "yw";

	public static boolean globalFileOverride = true;

	// 得到项目路径
	// /Users/yw/fh/git-source/particle
	public static String globalOutputDir = System.getProperty("user.dir");


	// packageParent + packageModuleName 组成最终的包名
	public static String packageParent = "com.particle";
	public static String packageModuleName = "system";


	public static TableType tableType = TableType.TREE;
	public static AdapterType adapterType = AdapterType.WEB;
	public static AdapterLogicType adapterLogicType = AdapterLogicType.ADMIN;

	public static void main(String[] args) {
		FastAutoGenerator.create(datasourceUrl, datasourceUsername, datasourcePassword)
				.globalConfig(builder -> {
					builder.author(globalAuthor) // 设置作者
							.enableSwagger() // 开启 swagger 模式
							.disableOpenDir() // 禁止打开输出目录
							.outputDir(globalOutputDir); // 指定输出目录
					if (globalFileOverride) {
						builder.fileOverride(); // 覆盖已生成文件
					}
				})
				.packageConfig(builder -> {
					builder.parent(packageParent) // 设置父包名
							.moduleName(packageModuleName) // 设置父包模块名
							// do 是关键字不能出现在包名中
							.entity("dos")
							.controller(adapterType.name().toLowerCase())
							.pathInfo(Collections.singletonMap(OutputFile.mapperXml, globalOutputDir)); // 设置mapperXml生成路径
				})
				.templateConfig(builder -> {

				})
				.injectionConfig(builder -> {
					builder.beforeOutputFile((tableInfo, objectMap) -> {
						log.info("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
					});
				})
				.strategyConfig(builder -> {
					builder.addInclude("component_area") // 设置需要生成的表名
							.addTablePrefix("component"); // 设置过滤表前缀
					builder.entityBuilder()
							.naming(NamingStrategy.underline_to_camel)
							.columnNaming(NamingStrategy.underline_to_camel)
							.superClass(BaseDO.class)
							.versionColumnName(BaseDO.COLUMN_VERSION)
							.versionPropertyName(BaseDO.PROPERTY_VERSION)
							.formatFileName("%sDO")
							.enableLombok();
					if (tableType == TableType.TREE) {
						builder.entityBuilder().superClass(BaseTreeDO.class);
					}

					builder.mapperBuilder()
							.superClass(IBaseMapper.class);

					builder.serviceBuilder()
							.superServiceClass(IBaseService.class)
							.superServiceImplClass(IBaseServiceImpl.class);
					builder.controllerBuilder()
							.enableHyphenStyle()
							.enableRestStyle()
							.convertFileName(entityName ->
									entityName
											+ StrUtil.upperFirst(adapterLogicType.name().toLowerCase())
											+ StrUtil.upperFirst(adapterType.name().toLowerCase())
											+ ConstVal.CONTROLLER
							);
					if (adapterType == AdapterType.WEB) {
						builder.controllerBuilder()
								.superClass(AbstractBaseWebAdapter.class);
					}else if (adapterType == AdapterType.WAP) {
						builder.controllerBuilder().superClass(AbstractBaseWapAdapter.class);
					}else if (adapterType == AdapterType.MOBILE) {
						builder.controllerBuilder().superClass(AbstractBaseMobileAdapter.class);
					}else {
						throw new RuntimeException("不支持的适配器类型 adapterType=" + adapterType);
					}
				})
				.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();

	}
}
