package com.particle.lowcode.infrastructure.generator.gateway.impl;

import com.baomidou.mybatisplus.generator.IDatabaseQuery;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.gateway.LowcodeDbTableInfoGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 物理表信息处理实现
 * </p>
 *
 * @author yangwei
 * @since 2023-01-04 17:18
 */
@Component
public class LowcodeDbTableInfoGatewayImpl implements LowcodeDbTableInfoGateway {
	@Override
	public List<LowcodeModelItem> loadByTableName(String tableName ,String url,String username,String password) {
		TableInfo tableInfo = loadTableInfo(tableName, url, username, password);
		List<LowcodeModelItem> lowcodeModelItems = tableInfoToLowcodeModelItems(tableInfo);
		return lowcodeModelItems;
	}

	/**
	 * 加载表信息
	 * @param tableName
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	private TableInfo loadTableInfo(String tableName,String url,String username,String password){

		// dataSourceConfig
		DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(url, username, password);
		DataSourceConfig dataSourceConfig = dataSourceConfigBuilder.build();
		// strategyConfig
		StrategyConfig.Builder strategyConfigBuilder = new StrategyConfig.Builder();
		StrategyConfig strategyConfig = strategyConfigBuilder.addInclude(tableName).build();

		// 只用到了数据源和策略配置
		ConfigBuilder configBuilder = new ConfigBuilder(
				null,
				dataSourceConfig,
				strategyConfig,
				null,
				null,
				null);

		List<TableInfo> tableInfos = new IDatabaseQuery.DefaultDatabaseQuery(configBuilder).queryTables();
		if (tableInfos.isEmpty()) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.DATA_NOT_FOUND,tableName + " 表不存在");
		}
		TableInfo tableInfo = tableInfos.iterator().next();
		return tableInfo;
	}

	/**
	 * 转换为模型项领域对象
	 * @param tableInfo
	 * @return
	 */
	private List<LowcodeModelItem> tableInfoToLowcodeModelItems(TableInfo tableInfo) {
		return tableInfo.getFields().stream().map(this::createByTableField).collect(Collectors.toList());
	}

	/**
	 * 转换为模型项领域对象，单个
	 * @param field
	 * @return
	 */
	private LowcodeModelItem createByTableField(TableField field) {
		LowcodeModelItem lowcodeModelItem = LowcodeModelItem.create();
		lowcodeModelItem.setColumnName(field.getColumnName());
		lowcodeModelItem.setPropertyName(field.getPropertyName());
		lowcodeModelItem.setJdbcType(field.getType());
		lowcodeModelItem.setPropertyType(field.getPropertyType());
		lowcodeModelItem.setCommentFull(field.getComment());
		lowcodeModelItem.fillCommentSimpleByCommentFull();

		TableField.MetaInfo metaInfo = field.getMetaInfo();

		lowcodeModelItem.setDefaultValue(metaInfo.getDefaultValue());
		// 目前mybatis plus generator不支持，如果自己写的话也是可以的，但要兼容多种数据库也很费劲，暂不添加，放到前端设置
		lowcodeModelItem.setIsUnique(false);
		lowcodeModelItem.setIsRequired(!metaInfo.isNullable());
		lowcodeModelItem.setIsKey(field.isKeyFlag());
		lowcodeModelItem.setIsKeyIdentity(field.isKeyIdentityFlag());
		lowcodeModelItem.setIsKeyWord(field.isKeyWords());
		lowcodeModelItem.setColumnLength(metaInfo.getLength());
		lowcodeModelItem.setFractionLength(metaInfo.getScale());
		// 这个只能通过前端设置，一般建表不设置外键
		lowcodeModelItem.setIsForeignKey(false);


		return lowcodeModelItem;
	}
}