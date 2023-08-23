package com.particle.global.openapi.tool;

import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * openapi工具类
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 16:31
 */
public class OpenapiTool {


	/**
	 * 将map转为待签名（可拼接）的字符串
	 * @param map
	 * @return
	 */
	public static String buildMapToStringForSignature(Map<String,String> map) {
		StringBuffer sb = new StringBuffer();

		for (Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
			Map.Entry<String, String> entry = iterator.next();
			sb.append(entry.getKey()).append("=").append(entry.getValue());
		}
		return sb.toString();
	}
}
