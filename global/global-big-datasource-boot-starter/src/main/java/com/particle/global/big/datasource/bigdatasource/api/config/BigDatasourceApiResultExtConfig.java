package com.particle.global.big.datasource.bigdatasource.api.config;

import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import lombok.Data;
import lombok.SneakyThrows;

import javax.script.Bindings;
import java.util.Map;

/**
 * <p>
 * 大数据源参数扩展配置
 * </p>
 *
 * @author yangwei
 * @since 2023-11-30 13:38:59
 */
@Data
public class BigDatasourceApiResultExtConfig {

    /**
     * groovy脚本
	 */
	private String groovyScript;

	public static BigDatasourceApiResultExtConfig create(String groovyScript) {
		BigDatasourceApiResultExtConfig bigDatasourceApiCommandExtConfig = new BigDatasourceApiResultExtConfig();
		bigDatasourceApiCommandExtConfig.groovyScript = groovyScript;
		return bigDatasourceApiCommandExtConfig;
	}


	/**
	 * 处理
	 * @param o
	 * @param command
	 * @param queryString
	 * @return 返回的结果将用来替换 参数 o,如果返回null将不替换
	 */
	@SneakyThrows
	public Object handle(Object o,Object command, String queryString,Map<String,Object> extBindings) {
		if (StrUtil.isEmpty(groovyScript)) {
			return null;
		}

		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(o);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();
		objectMap.put("command", command);
		objectMap.put("queryString", queryString);

		if (extBindings != null) {
			objectMap.putAll(extBindings);
		}

		Bindings bindings = GroovyTool.createBindings();
		bindings.putAll(objectMap);
		Object eval = GroovyTool.compileAndEval(groovyScript, bindings, true);
		return eval;
	}
}
