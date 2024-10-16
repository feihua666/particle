package com.particle.tenant.client.exception;

import com.particle.global.exception.code.IErrorCodeStatusMax;

/**
 * <p>
 * 租户状态码最大值记录
 * </p>
 *
 * @author yangwei
 * @since 2023-05-12 18:13
 */
public class IErrorCodeTenantStatusMax implements IErrorCodeStatusMax {

	/**
	 * s 前缀没有什么意义，statusMax 首字母可以这样认为，因为变量不允许为直接数字
	 */
	private static Long s_5000002 = null;
	private static Long s_4000002 = 40000020001L;
	private static Long s_4010002 = null;
	private static Long s_4030002 = null;
	private static Long s_4040002 = null;
	private static Long s_4050002 = null;
	private static Long s_4150002 = null;
}
