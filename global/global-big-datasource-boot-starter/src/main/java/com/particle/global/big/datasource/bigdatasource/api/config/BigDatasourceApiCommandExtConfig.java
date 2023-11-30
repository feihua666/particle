package com.particle.global.big.datasource.bigdatasource.api.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.particle.global.big.datasource.bigdatasource.api.validate.ParamValidator;
import com.particle.global.big.datasource.bigdatasource.api.validate.impl.EnjoyTemplateParamValidator;
import com.particle.global.big.datasource.bigdatasource.api.validate.impl.GroovyParamValidator;
import com.particle.global.big.datasource.bigdatasource.api.validate.impl.JavaScriptAssertParamValidator;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.exception.Assert;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import lombok.Data;
import lombok.SneakyThrows;

import javax.script.Bindings;
import java.util.ArrayList;
import java.util.List;
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
public class BigDatasourceApiCommandExtConfig {

	/**
	 * groovy脚本
	 */
	private String groovyScript;

	public static BigDatasourceApiCommandExtConfig create(String groovyScript) {
		BigDatasourceApiCommandExtConfig bigDatasourceApiCommandExtConfig = new BigDatasourceApiCommandExtConfig();
		bigDatasourceApiCommandExtConfig.groovyScript = groovyScript;
		return bigDatasourceApiCommandExtConfig;
	}

	/**
	 * 处理
	 * @param command
	 * @param queryString
	 * @return 返回的结果将用来替换 参数 command,如果返回null将不替换
	 */
	@SneakyThrows
	public Object handle(Object command, String queryString) {
		if (StrUtil.isEmpty(groovyScript)) {
			return null;
		}

		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(command);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();
		objectMap.put("queryString", queryString);
		Bindings bindings = GroovyTool.createBindings();
		bindings.putAll(objectMap);
		Object eval = GroovyTool.compileAndEval(groovyScript, bindings, true);
		return eval;
	}
}
