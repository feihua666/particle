package com.particle.global.security.tenant;

import com.particle.global.tool.thread.ThreadContextTool;

/**
 * <p>
 * 租户工具
 * </p>
 *
 * @author yangwei
 * @since 2022-06-30 14:21
 */
public class TenantTool {

	private static String currentTenantKey = "currentTenantKey";

	private static Boolean tenantEnable = false;

	/**
	 * 租户id
	 * @return
	 */
	public static Long getTenantId(){
		Object o = ThreadContextTool.get(currentTenantKey);
		if (o != null) {
			if (o instanceof Long) {
				return ((Long) o);
			}
			if (o instanceof GrantedTenant) {
				return ((GrantedTenant) o).getId();
			}
		}
		return null;
	}

	/**
	 * 设置租户
	 * @param tenantId
	 */
	public static void setTenantId(Long tenantId){
		ThreadContextTool.put(currentTenantKey,tenantId);
	}

	/**
	 * 清除租户
	 */
	public static void clear() {
		ThreadContextTool.remove(currentTenantKey);
	}

	/**
	 * 将是否启用租户写成静态工具类，方便使用
	 * @return
	 */
	public static boolean isTenantEnable() {
		return tenantEnable;
	}

	/**
	 * 启用租户
	 */
	public static void tenantEnable() {
		tenantEnable = true;
	}
}
