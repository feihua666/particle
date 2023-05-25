package com.particle.global.messaging.event.messaging;

import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * spring cloud stream 消息工具类
 * </p>
 *
 * @author yangwei
 * @since 2022-10-12 17:27
 */
public class GlobalCloudSteamMessageUtil {

	private static final String placeHolder = "{}";

	private static final String out = "out";
	private static final String in = "in";

	private static final String out_zero = "out-0";
	private static final String in_zero = "in-0";

	private static final String bindingNamePlaceHolderOutZero = placeHolder + "-" + out_zero;
	private static final String bindingNamePlaceHolderInZero = placeHolder + "-" + in_zero;


	/**
	 * out bindingName
	 * @param functionDefinition
	 * @return
	 */
	public static String outZeroBindingName(String functionDefinition) {
		return StrUtil.format(bindingNamePlaceHolderOutZero, functionDefinition);
	}

	/**
	 * in bindingName
	 * @param functionDefinition
	 * @return
	 */
	public static String inZeroBindingName(String functionDefinition) {
		return StrUtil.format(bindingNamePlaceHolderInZero, functionDefinition);
	}
}
