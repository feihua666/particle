package com.particle.global.big.datasource.bigdatasource.api.validate;

import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;

/**
 * <p>
 * 参数校验器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 22:11
 */
public interface ParamValidator {

	/**
	 * 是否支持类型
	 * @param type
	 * @return
	 */
	boolean support(ParamValidateType type);

	/**
	 * 校验
	 * @param command
	 * @param template 脚本
	 * @return true = 校验通过，false=校验失败
	 */
	boolean validate(Object command,String template);
}
