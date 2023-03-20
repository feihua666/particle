package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiJdbcBasicConfigDataType;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiJdbcBasicConfigSqlTemplateType;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiPageableAdapterType;
import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 * jdbc 数据源接口基础配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-18 11:48
 */
@Data
public class DataQueryDatasourceApiPageableAdapterConfig extends Value {
	/**
	 * 入参分页信息支持的类型
	 */
	private DataQueryDatasourceApiPageableAdapterType inParamTemplateType;
	/**
	 * 入参分页信息解析模板
	 */
	private String inParamTemplate;
	/**
	 * 出参分页信息支持的类型
	 */
	private DataQueryDatasourceApiPageableAdapterType outParamTemplateType;
	/**
	 * 出参分页信息解析模板
	 */
	private String outParamTemplate;

	public static DataQueryDatasourceApiPageableAdapterConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiPageableAdapterConfig pageableAdapterConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiPageableAdapterConfig.class);
		return pageableAdapterConfig;
	}

	public static DataQueryDatasourceApiPageableAdapterConfig create(
			DataQueryDatasourceApiPageableAdapterType inParamTemplateType,
			String inParamTemplate,
			DataQueryDatasourceApiPageableAdapterType outParamTemplateType,
			String outParamTemplate
	) {
		DataQueryDatasourceApiPageableAdapterConfig pageableAdapterConfig = new DataQueryDatasourceApiPageableAdapterConfig();
		pageableAdapterConfig.setInParamTemplateType(inParamTemplateType);
		pageableAdapterConfig.setInParamTemplate(inParamTemplate);
		pageableAdapterConfig.setOutParamTemplateType(outParamTemplateType);
		pageableAdapterConfig.setOutParamTemplate(outParamTemplate);
		return pageableAdapterConfig;
	}
}
