package com.particle.global.big.datasource.bigdatasource.enums;

import java.util.Arrays;

/**
 * <p>
 * 参数类型
 * </p>
 *
 * @author yangwei
 * @since 2024-01-16 13:11:52
 */
public enum ParamType {

	/**
	 * 对象
	 */
	object,

	/**
	 * 数组
	 */
	array,

	/**
	 * 字符串
	 */
	string,

	/**
	 * 数字
	 */
	number,

	/**
	 * 浮点数，变量冲突，这里加参数构造
	 */
	floats("float"),

	/**
	 * 布尔值，变量冲突，这里加参数构造
	 */
	booleans("boolean");

	private String value;

	ParamType() {
		this.value = this.name();
	}

	ParamType(String value) {
		this.value = value;
	}


	/**
	 * 自定义
	 * @param value
	 * @return
	 */
	public static ParamType valuesOf(String value) {
		return Arrays.stream(ParamType.values()).filter(item -> item.value.equals(value)).findFirst().orElse(null);
	}
}
