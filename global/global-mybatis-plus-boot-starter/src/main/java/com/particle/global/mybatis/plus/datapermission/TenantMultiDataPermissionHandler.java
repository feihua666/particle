package com.particle.global.mybatis.plus.datapermission;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;

/**
 * <p>
 * 租户级数据权限
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 22:45
 */
public interface TenantMultiDataPermissionHandler extends MultiDataPermissionHandler {

	/**
	 * 租户id大于或等于该值，将使用租户数据过滤
	 */
	public static final Long minTenantIdUseTenantDataPermisson = 10000L;
}
