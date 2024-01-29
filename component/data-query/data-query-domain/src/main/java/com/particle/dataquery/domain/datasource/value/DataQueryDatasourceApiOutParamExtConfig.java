package com.particle.dataquery.domain.datasource.value;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.dto.basic.Value;
import com.particle.global.tool.script.GroovyTool;
import lombok.Data;

import javax.script.ScriptException;

/**
 * <p>
 * 出参数扩展配置
 * </p>
 *
 * @author yangwei
 * @since 2023-11-30 13:51:10
 */
@Data
public class DataQueryDatasourceApiOutParamExtConfig extends Value {

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
	public static DataQueryDatasourceApiOutParamExtConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiOutParamExtConfig dataQueryDatasourceApiOutParamExtConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiOutParamExtConfig.class);
		return dataQueryDatasourceApiOutParamExtConfig;
	}

	public static DataQueryDatasourceApiOutParamExtConfig create(String groovyScript) {
		DataQueryDatasourceApiOutParamExtConfig dataQueryDatasourceApiOutParamExtConfig = new DataQueryDatasourceApiOutParamExtConfig();
		dataQueryDatasourceApiOutParamExtConfig.groovyScript = groovyScript;
		return dataQueryDatasourceApiOutParamExtConfig;
	}
}
