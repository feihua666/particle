package com.particle.global.openapi.exception;

import com.particle.global.exception.code.IErrorCodeStatusMax;

/**
 * <p>
 * 开放接口状态码最大值记录
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 17:26:14
 */
public class IErrorCodeOpenapiStatusMax implements IErrorCodeStatusMax {

	/**
	 * s 前缀没有什么意义，statusMax 首字母可以这样认为，因为变量不允许为直接数字
	 */
	private static Long s_5000000 = 50000030002L;
	private static Long s_4000001 = 40000030007L;
	private static Long s_4010000 = null;
	private static Long s_4030000 = null;
	private static Long s_4040000 = null;
	private static Long s_4050000 = null;
	private static Long s_4150000 = null;
}
