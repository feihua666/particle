package com.particle.dataquery.domain.datasource.value;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.dto.basic.Value;
import com.particle.global.tool.script.GroovyTool;
import lombok.Data;

import javax.script.ScriptException;

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

	/**
	 * 编译预热
	 * @throws ScriptException
	 */
	public void warmUpLight() throws ScriptException {
		if (StrUtil.isNotEmpty(groovyScript)) {
			GroovyTool.compile(groovyScript,true);
		}
	}
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
