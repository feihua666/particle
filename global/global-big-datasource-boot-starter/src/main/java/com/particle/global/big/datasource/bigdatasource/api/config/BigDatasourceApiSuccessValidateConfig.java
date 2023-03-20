package com.particle.global.big.datasource.bigdatasource.api.config;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.particle.global.big.datasource.bigdatasource.api.validate.ParamValidator;
import com.particle.global.big.datasource.bigdatasource.api.validate.impl.EnjoyTemplateParamValidator;
import com.particle.global.big.datasource.bigdatasource.api.validate.impl.JavaScriptAssertParamValidator;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.exception.Assert;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 大数据源结果验证配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-13 13:37
 */
@Data
public class BigDatasourceApiSuccessValidateConfig {

	private static List<ParamValidator> defaultParamValidators;
	/**
	 * 数据描述信息
	 */
	private List<ValidateItem> validateItems;


	private List<ParamValidator> paramValidators;


	public static BigDatasourceApiSuccessValidateConfig create() {
		BigDatasourceApiSuccessValidateConfig config = new BigDatasourceApiSuccessValidateConfig();
		config.setParamValidators(initDefaultParamValidators());
		return config;
	}
	public static BigDatasourceApiSuccessValidateConfig create(List<ValidateItem> validateItems) {
		BigDatasourceApiSuccessValidateConfig config = create();
		config.setValidateItems(validateItems);
		return config;
	}

	public static List<ParamValidator> initDefaultParamValidators(){
		if (defaultParamValidators == null) {
			defaultParamValidators = Lists.newArrayList(EnjoyTemplateParamValidator.create(), JavaScriptAssertParamValidator.create());
		}
		return defaultParamValidators;
	}

	public void addValidateItem(ValidateItem validateItem) {
		if (CollectionUtil.isEmpty(validateItems)) {
			validateItems = new ArrayList<>();
		}
		validateItems.add(validateItem);
	}


	/**
	 * 校验参数成功与失败
	 * @param rawResult
	 * @param assertMode true=在校验失败时抛出异常
	 * @return true=校验成功，false=校验失败
	 */
	public boolean doValidate(Object rawResult,boolean assertMode) {
		// 如果没有验证项，表示验证成功
		if(CollectionUtil.isEmpty(validateItems)){
			return true;
		}
		for (ValidateItem validateItem : validateItems) {
			for (ParamValidator paramValidator : paramValidators) {
				if (paramValidator.support(validateItem.getType())) {
					boolean validate = paramValidator.validate(rawResult, validateItem.getContentTemplate());
					if (assertMode) {
						Assert.isTrue(validate,validateItem.getErrorMessage());
					}else {
						if(!validate){
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Data
	public static class ValidateItem{
		/**
		 * 名称，仅具可读性
		 */
		private String name;
		/**
		 * 类型
		 */
		private ParamValidateType type;
		/**
		 * 脚本模板
		 */
		private String contentTemplate;
		/**
		 * 校验失败时提示信息
		 */
		private String errorMessage;

		public static ValidateItem create(String name,ParamValidateType type,String contentTemplate,String errorMessage){
			ValidateItem ValidateItem = new ValidateItem();
			ValidateItem.setName(name);
			ValidateItem.setType(type);
			ValidateItem.setContentTemplate(contentTemplate);
			ValidateItem.setErrorMessage(errorMessage);
			return ValidateItem;
		}
	}
}
