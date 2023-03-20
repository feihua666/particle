package com.particle.global.big.datasource.bigdatasource.command;

import java.util.Map;

/**
 * <p>
 * 大数据源查询指令
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 13:11
 */
public interface IBigDatasourceQueryCommand {


	/**
	 * map数据，一般用于取值或支持map的传参
	 * @return
	 */
	Map<?,?> toMap();
	/**
	 * json字符串，一般用于http请求或支持json格式的传参
	 * @return
	 */
	String toJson();

	/**
	 * xml字符串，一般用于http请求或支持xml格式的传参
	 * @return
	 */
	String toXml();
}
