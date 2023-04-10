package com.particle.global.big.datasource.bigdatasource.api.validate.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.big.datasource.bigdatasource.api.validate.ParamValidator;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.Data;

import java.util.Map;

/**
 * <p>
 * java script 脚本参数校验器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 22:17
 */
public class EnjoyTemplateParamValidator implements ParamValidator {
	@Override
	public boolean support(ParamValidateType type) {
		return type == ParamValidateType.enjoy_template;
	}

	@Override
	public boolean validate(Object command,String enjoyTemplate) {
		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(command);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();

		EnjoyValidateResult enjoyValidateResult = new EnjoyValidateResult();
		objectMap.put("validateResult", enjoyValidateResult);
		String render = TemplateTool.render(enjoyTemplate, objectMap);

		if (enjoyValidateResult.getIsSuccess() == null) {
			if (StrUtil.isEmpty(render)) {
				throw new BigDatasourceException("param validate error. result value must be set. example is #(validateResult.setIsSuccess(true)) or #{data.name==1}");
			}else {
				if ("true".equals(render) || "false".equals(render)) {
					Boolean aBoolean = Boolean.valueOf(render);
					return aBoolean;
				}else {
					throw new BigDatasourceException("enjoy template must render true or false value.for " + enjoyTemplate + " renderResult=" + render);
				}

			}


		}
		return Boolean.TRUE.equals( enjoyValidateResult.getIsSuccess() );
	}

	public static EnjoyTemplateParamValidator create() {
		EnjoyTemplateParamValidator enjoyTemplateParamValidator = new EnjoyTemplateParamValidator();
		return enjoyTemplateParamValidator;
	}

	/**
	 *
	 */
	@Data
	public static class EnjoyValidateResult{
		private Boolean isSuccess;
	}
}
