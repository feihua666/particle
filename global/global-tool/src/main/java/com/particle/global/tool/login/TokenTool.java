package com.particle.global.tool.login;

import com.particle.global.tool.thread.ThreadContextTool;

/**
 * <p>
 * Token 工具
 * </p>
 *
 * @author yangwei
 * @since 2026-03-07 19:51:58
 */
public class TokenTool {

	private static String request_token_key = "request_token_key";


	/**
	 * 获取 token
	 * @return
	 */
	public static String getToken(){
		Object o = ThreadContextTool.get(request_token_key);
		if (o != null) {
			return o.toString();
		}
		return null;
	}

	/**
	 * 设置token
	 * @param token
	 */
	public static void setToken(String token){
		ThreadContextTool.put(request_token_key,token);
	}


	/**
	 * 清除token
	 */
	public static void clear() {
		ThreadContextTool.remove(request_token_key);
	}

}
