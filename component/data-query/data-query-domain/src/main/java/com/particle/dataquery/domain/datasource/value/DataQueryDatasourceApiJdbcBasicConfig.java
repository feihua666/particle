package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiJdbcBasicConfigDataType;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiJdbcBasicConfigSqlTemplateType;
import com.particle.global.dto.basic.Value;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateTool;
import lombok.Data;

import javax.script.ScriptException;

/**
 * <p>
 * jdbc 数据源接口基础配置
 * 该配置大致与{@link com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig} 相对应
 * </p>
 *
 * @author yangwei
 * @since 2023-03-18 11:48
 */
@Data
public class DataQueryDatasourceApiJdbcBasicConfig extends Value {
	/**
	 * 类型
	 */
	private DataQueryDatasourceApiJdbcBasicConfigSqlTemplateType sqlTemplateType;

	private DataQueryDatasourceApiJdbcBasicConfigDataType dataType;
	/**
	 * 内容
	 */
	private String sqlTemplate;

	/**
	 * 是否在分页时，查询总数
	 */
	private Boolean isSearchCount;

	/**
	 * 脚本预热
	 * @throws ScriptException
	 */
	public void warmUpLight() throws ScriptException {
		if (sqlTemplateType != null && sqlTemplate != null) {
			switch (sqlTemplateType) {
				case enjoy_template:
					TemplateTool.templateCompile(sqlTemplate);
					break;
				case groovy_script_template:
					GroovyTool.compile(sqlTemplate,true);
					break;
				default:
					break;
			}
		}
	}


	public static DataQueryDatasourceApiJdbcBasicConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiJdbcBasicConfig dataQueryDatasourceApiJdbcBasicConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiJdbcBasicConfig.class);
		return dataQueryDatasourceApiJdbcBasicConfig;
	}

	public static DataQueryDatasourceApiJdbcBasicConfig create(DataQueryDatasourceApiJdbcBasicConfigSqlTemplateType type,
															   DataQueryDatasourceApiJdbcBasicConfigDataType dataType,
															   String sqlTemplate,
															   Boolean isSearchCount) {
		DataQueryDatasourceApiJdbcBasicConfig dataQueryDatasourceApiJdbcBasicConfig = new DataQueryDatasourceApiJdbcBasicConfig();
		dataQueryDatasourceApiJdbcBasicConfig.setSqlTemplateType(type);
		dataQueryDatasourceApiJdbcBasicConfig.setDataType(dataType);
		dataQueryDatasourceApiJdbcBasicConfig.setSqlTemplate(sqlTemplate);
		dataQueryDatasourceApiJdbcBasicConfig.setIsSearchCount(isSearchCount);
		return dataQueryDatasourceApiJdbcBasicConfig;
	}
}
