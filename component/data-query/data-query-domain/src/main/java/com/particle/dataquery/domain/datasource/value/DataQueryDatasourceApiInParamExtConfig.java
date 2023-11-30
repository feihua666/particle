package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 * 入参数扩展配置
 * </p>
 *
 * @author yangwei
 * @since 2023-11-30 13:51:10
 */
@Data
public class DataQueryDatasourceApiInParamExtConfig extends Value {

	/**
	 * groovy 脚本
	 */
	private String groovyScript;


	public static DataQueryDatasourceApiInParamExtConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiInParamExtConfig dataQueryDatasourceApiInParamExtConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiInParamExtConfig.class);
		return dataQueryDatasourceApiInParamExtConfig;
	}

	public static DataQueryDatasourceApiInParamExtConfig create(String groovyScript) {
		DataQueryDatasourceApiInParamExtConfig dataQueryDatasourceApiInParamExtConfig = new DataQueryDatasourceApiInParamExtConfig();
		dataQueryDatasourceApiInParamExtConfig.groovyScript = groovyScript;
		return dataQueryDatasourceApiInParamExtConfig;
	}
}
