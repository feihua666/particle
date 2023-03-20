package com.particle.global.big.datasource.bigdatasource.api.validate.impl;

import com.particle.global.big.datasource.bigdatasource.api.validate.ParamValidator;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.validation.ValidateTool;

import java.util.Map;

/**
 * <p>
 * java script 脚本参数校验器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 22:17
 */
public class JavaScriptAssertParamValidator implements ParamValidator {
	@Override
	public boolean support(ParamValidateType type) {
		return type == ParamValidateType.javascript_assert;
	}

	@Override
	public boolean validate(Object command,String javaScript) {
		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(command);
		Map<String, Object> bindings = objectTemplateRenderDataWrap.toRenderMap();
		return ValidateTool.javascriptAssert(javaScript,bindings);
	}

	public static JavaScriptAssertParamValidator create() {
		JavaScriptAssertParamValidator javaScriptAssertParamValidator = new JavaScriptAssertParamValidator();
		return javaScriptAssertParamValidator;
	}
}
