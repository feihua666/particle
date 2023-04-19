package com.particle.lowcode.infrastructure.generator.gateway.impl;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.query.DefaultQuery;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.gateway.LowcodeDbTableInfoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 物理表信息处理实现
 * </p>
 *
 * @author yangwei
 * @since 2023-01-04 17:18
 */
@Slf4j
@Component
public class LowcodeDbTableInfoGatewayImpl implements LowcodeDbTableInfoGateway {
	@Override
	public List<LowcodeModelItem> loadByTableName(String tableName ,String url,String username,String password) {
		TableInfo tableInfo = loadTableInfo(tableName, url, username, password);
		List<LowcodeModelItem> lowcodeModelItems = tableInfoToLowcodeModelItems(tableInfo);
		return lowcodeModelItems;
	}

	@Override
	public String loadCreateTableSql(String tableName ,String url,String username,String password) {

		return mysqlLoadCreateTableSql(tableName, url, username, password);
	}


	private String mysqlLoadCreateTableSql(String tableName ,String url,String username,String password) {
		// dataSourceConfig
		DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(url, username, password);
		DataSourceConfig dataSourceConfig = dataSourceConfigBuilder.build();
		try (
				Connection conn = dataSourceConfig.getConn();
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
			log.error("SQL Exception：" + e.getMessage(),e);
		}finally {
			try {
				dataSourceConfig.getConn().close();
			} catch (SQLException throwables) {
				log.error(throwables.getMessage(),throwables);
			}
		}
		return "仅支持mysql";
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
		TableInfo tableInfo;

		// dataSourceConfig
		DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(url, username, password);
		dataSourceConfigBuilder
				.addConnectionProperty("remarks","true")
				.addConnectionProperty("useInformationSchema","true")
				.addConnectionProperty("remarksReporting","true");
		DataSourceConfig dataSourceConfig = dataSourceConfigBuilder.build();
		try {
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

			List<TableInfo> tableInfos = new DefaultQuery(configBuilder).queryTables();
			if (tableInfos.isEmpty()) {
				throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.DATA_NOT_FOUND,tableName + " 表不存在");
			}
			tableInfo = tableInfos.iterator().next();
		} finally {
			try {
				dataSourceConfig.getConn().close();
			} catch (SQLException throwables) {
				log.error(throwables.getMessage(),throwables);
			}
		}

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

		TableField.MetaInfo metaInfo = field.getMetaInfo();

		LowcodeModelItem lowcodeModelItem = LowcodeModelItem.create();
		lowcodeModelItem.setColumnName(field.getColumnName());
		lowcodeModelItem.setPropertyName(field.getPropertyName());
		lowcodeModelItem.setJdbcType(metaInfo.getJdbcType().name());
		lowcodeModelItem.setPropertyType(field.getPropertyType());
		lowcodeModelItem.setPropertyFullType(Optional.ofNullable(field.getColumnType()).map(i->i.getPkg()).orElse(null));
		lowcodeModelItem.setCommentFull(field.getComment());
		lowcodeModelItem.fillCommentSimpleByCommentFull();


		lowcodeModelItem.setDefaultValue(metaInfo.getDefaultValue());
		// 是否唯一字段判断，目前mybatis plus generator不支持，如果自己写的话也是可以的，但要兼容多种数据库也很费劲，暂不添加，放到前端设置
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
