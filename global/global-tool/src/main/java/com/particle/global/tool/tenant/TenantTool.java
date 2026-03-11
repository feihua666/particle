package com.particle.global.tool.tenant;

import com.particle.global.dto.tenant.TenantContext;
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

	private static String currentTenantContextKey = "currentTenantContextKey";

	private static Boolean tenantEnable = false;

	/**
	 * 租户id
	 * @return
	 */
	public static Long getTenantId(){
		Object o = ThreadContextTool.get(currentTenantContextKey);
		if (o != null) {
			if (o instanceof TenantContext) {
				return ((TenantContext) o).getTenantId();
			}
		}
		return null;
	}

	/**
	 * 设置租户
	 * @param tenantId
	 */
	public static void setTenantId(Long tenantId){
		TenantContext tenantContext = TenantContext.create(tenantId);
		ThreadContextTool.put(currentTenantContextKey,tenantContext);
	}

	/**
	 * 无租户
	 */
	public static void none() {
		TenantContext tenantContext = TenantContext.createNone();
		ThreadContextTool.put(currentTenantContextKey,tenantContext);
	}

	/**
	 * 清除租户
	 */
	public static void clear() {
		ThreadContextTool.remove(currentTenantContextKey);
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
