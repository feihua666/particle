package com.particle.lowcode.domain.generator.gateway;

import com.particle.lowcode.domain.generator.LowcodeModelItem;

import java.util.List;

/**
 * <p>
 * 从数据库加载表信息并转为模型项
 * </p>
 *
 * @author yangwei
 * @since 2023-01-04 17:16
 */
public interface LowcodeDbTableInfoGateway {

	/**
	 * 根据表名加载模型项数据
	 * @param tableName 全表名,不支持多个
	 * @return
	 */
	List<LowcodeModelItem> loadByTableName(String tableName,String url,String username,String password);

	/**
	 * 获取建表语句
	 * @param tableName
	 * @return
	 */
	public String loadCreateTableSql(String tableName ,String url,String username,String password) ;
}
