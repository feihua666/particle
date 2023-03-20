package com.particle.global.big.datasource.bigdatasource.api.config;

import lombok.Data;

import java.util.Map;

/**
 * <p>
 * 大数据源查询指令字段配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-13 13:37
 */
@Data
public class BigDatasourceQueryCommandPropertyConfig {

	public static String identifierForBigDatasourceQueryCommandConfigProperty = "identifierForBigDatasourceQueryCommandConfig";
	public static String identifierForBigDatasourceQueryCommandConfigPropertyValue = "identifierForBigDatasourceQueryCommandConfig";

	/**
	 * 用来标识对应是否为配置对象
	 */
	private String identifierForBigDatasourceQueryCommandConfig = identifierForBigDatasourceQueryCommandConfigPropertyValue;
	/**
	 * 是否为字典
	 */
	private Boolean isDict;
	/**
	 * 是否必填
	 */
	private Boolean isRequired;
	/**
	 * 验证的正则匹配
	 */
	private String validateRegex;
	/**
	 * 属性注释，一般是中文说明
	 */
	private String annotation;
	/**
	 * 备注，其它说明内容
	 */
	private String remark;
	/**
	 * 扩展信息，当该类中属性配置不支持更多配置时，可以使用扩展属性支持
	 */
	private Map<String,Object> ext;
}
