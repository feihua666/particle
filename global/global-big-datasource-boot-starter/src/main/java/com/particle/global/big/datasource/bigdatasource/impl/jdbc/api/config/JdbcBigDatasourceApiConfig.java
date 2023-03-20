package com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config;

import com.particle.global.big.datasource.bigdatasource.api.config.AbstractBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigDataType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigSqlTemplateType;
import com.particle.global.mybatis.plus.mybatis.DynamicSqlSourceHelperTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.Data;

/**
 * <p>
 * jdbc大数据源接口配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 19:11
 */
@Data
public class JdbcBigDatasourceApiConfig extends AbstractBigDatasourceApiConfig {

	/**
	 * jdbc基础配置sql模板类型，即： sqlTemplate 类型
	 */
	private JdbcBigDatasourceApiConfigSqlTemplateType sqlTemplateType;

	/**
	 * 是否分页查询
	 */
	private JdbcBigDatasourceApiConfigDataType dataType;

	/**
	 * sql语句模板
	 */
	private String sqlTemplate;


	public static JdbcBigDatasourceApiConfig create(JdbcBigDatasourceApiConfigSqlTemplateType type, JdbcBigDatasourceApiConfigDataType dataType, String sqlTemplate) {
		JdbcBigDatasourceApiConfig jdbcBigDatasourceApiConfig = new JdbcBigDatasourceApiConfig();
		jdbcBigDatasourceApiConfig.setSqlTemplateType(type);
		jdbcBigDatasourceApiConfig.setDataType(dataType);
		jdbcBigDatasourceApiConfig.setSqlTemplate(sqlTemplate);
		return jdbcBigDatasourceApiConfig;
	}

	public static JdbcBigDatasourceApiConfig createByWithRawType(JdbcBigDatasourceApiConfigDataType dataType,String sqlTemplate){
		return create(JdbcBigDatasourceApiConfigSqlTemplateType.raw,dataType, sqlTemplate);
	}
	public static JdbcBigDatasourceApiConfig createByWithEnjoyType(JdbcBigDatasourceApiConfigDataType dataType,String sqlTemplate){
		return create(JdbcBigDatasourceApiConfigSqlTemplateType.enjoy_template,dataType, sqlTemplate);
	}
	public static JdbcBigDatasourceApiConfig createByWithMybatisXmlType(JdbcBigDatasourceApiConfigDataType dataType,String sqlTemplate){
		return create(JdbcBigDatasourceApiConfigSqlTemplateType.enjoy_template,dataType, sqlTemplate);
	}

	/**
	 * 返回渲染的sql
	 * @return
	 */
	public String render(Object data){
		if (sqlTemplateType == JdbcBigDatasourceApiConfigSqlTemplateType.enjoy_template) {
			return TemplateTool.render(sqlTemplate, TemplateRenderDataWrap.create(data));
		}
		/**
		 * 原生支持，无需渲染 得益于 {@link DynamicSqlSourceHelperTool}
		 */
		if (sqlTemplateType == JdbcBigDatasourceApiConfigSqlTemplateType.mybatis_script_template) {
			return sqlTemplate;
		}
		if (sqlTemplateType == JdbcBigDatasourceApiConfigSqlTemplateType.raw) {
			return sqlTemplate;
		}


		throw new RuntimeException("not support render for type " + sqlTemplateType.name());
	}
}
