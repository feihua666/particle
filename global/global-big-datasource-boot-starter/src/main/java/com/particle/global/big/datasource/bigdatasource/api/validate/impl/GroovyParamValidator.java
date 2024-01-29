package com.particle.global.big.datasource.bigdatasource.api.validate.impl;

import com.particle.global.big.datasource.bigdatasource.api.validate.ParamValidator;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import lombok.SneakyThrows;

import javax.script.Bindings;
import java.util.Map;

/**
 * <p>
 * groovy脚本参数验证器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 17:27
 */
public class GroovyParamValidator implements ParamValidator {
	@Override
	public boolean support(ParamValidateType type) {
		return type == ParamValidateType.groovy_template;
	}

	@SneakyThrows
	@Override
	public boolean validate(Object command, String template) {
		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(command);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();
		Bindings bindings = GroovyTool.createBindings();
		bindings.putAll(objectMap);
		Object eval = GroovyTool.compileAndEval(template, bindings, true);
		boolean b = eval instanceof Boolean;
		if(b){
			return ((Boolean) eval);
		}
		throw new BigDatasourceException("groovy script must render true or false value.for " + template + " renderResult=" + eval);
	}

	public static GroovyParamValidator create(){
		return new GroovyParamValidator();
	}
}
