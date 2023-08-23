package com.particle.global.openapi.collect;

import com.particle.global.tool.thread.ThreadContextTool;

/**
 * <p>
 * 开放接口收集工具
 * </p>
 *
 * @author yangwei
 * @since 2023-08-15 09:38
 */
public class OpenapiCollectTool {

	public static final String openapiContext_key = "openapiContext_key";
	public static final String openapiStart_key = "openapiStart_key";

	/**
	 * 设置openapi开始标志
	 */
	public static void startOpenApi() {
		ThreadContextTool.put(openapiStart_key,Boolean.TRUE);
	}

	/**
	 * 用于判断openapi是否开始，对应做一些处理
	 * @return
	 */
	public static boolean isStartOpenApi() {
		Boolean b = (Boolean)ThreadContextTool.get(openapiStart_key);
		return b != null && b;
	}

	/**
	 * 设置请求上下文
	 * @param openapiContext
	 */
	public static void putContext(OpenapiContext openapiContext) {
		ThreadContextTool.put(openapiContext_key,openapiContext);
	}

	/**
	 * 获取请求上下文
	 * @return
	 */
	public static OpenapiContext getContext() {
		return (OpenapiContext)ThreadContextTool.get(openapiContext_key);
	}

	/**
	 * 清空
	 */
	public static void clear() {
		ThreadContextTool.remove(openapiContext_key);
		ThreadContextTool.remove(openapiStart_key);
	}
}
