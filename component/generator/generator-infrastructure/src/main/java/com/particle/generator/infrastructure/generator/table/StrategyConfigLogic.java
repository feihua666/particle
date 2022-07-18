package com.particle.generator.infrastructure.generator.table;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.particle.generator.domain.TableType;
import com.particle.generator.domain.component.TableGenerateConf;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;

/**
 * <p>
 * 策略配置逻辑
 * </p>
 *
 * @author yangwei
 * @since 2022-07-07 14:16
 */
public class StrategyConfigLogic {

	/**
	 * 实体结尾
	 */
	private static String ENTITY_SUFFIX = "DO";

	public static void config(StrategyConfig.Builder builder, TableGenerateConf tableGenerateConf) {
		builder.addInclude(tableGenerateConf.getTableName()) // 设置需要生成的表名
				.addTablePrefix(tableGenerateConf.getTablePrefix()); // 设置过滤表前缀
		builder.entityBuilder()
				.naming(NamingStrategy.underline_to_camel)
				.columnNaming(NamingStrategy.underline_to_camel)
				.superClass(BaseDO.class)
				.versionColumnName(BaseDO.COLUMN_VERSION)
				.versionPropertyName(BaseDO.PROPERTY_VERSION)
				.formatFileName("%s" + ENTITY_SUFFIX)
				.enableLombok();
		if (tableGenerateConf.getTableType() == TableType.TREE) {
			builder.entityBuilder().superClass(BaseTreeDO.class);
		}

		builder.mapperBuilder()
				.superClass(IBaseMapper.class)
				.enableMapperAnnotation();

		builder.serviceBuilder()
				.superServiceClass(IBaseService.class)
				.superServiceImplClass(IBaseServiceImpl.class);
		builder.controllerBuilder()
				.enableHyphenStyle()
				.enableRestStyle();

	}

	/**
	 * 删除尾部的 ENTITY_SUFFIX 后缀
	 * @param entityName
	 * @return
	 */
	public static String removeEntitySuffix(String entityName) {
		return entityName.endsWith(ENTITY_SUFFIX) ? entityName.substring(0,entityName.length() - ENTITY_SUFFIX.length()) : entityName;
	}
}
