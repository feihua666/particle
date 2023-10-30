package com.particle.global.mybatis.plus.tenant;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.particle.global.dto.basic.DO;
import com.particle.global.security.tenant.TenantTool;
import lombok.Data;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * <p>
 * 自定义租户配置处理器
 * </p>
 *
 * @author yangwei
 * @since 2022-06-30 14:44
 */
@Data
public class CustomTenantLineHandler implements TenantLineHandler {

	public static String columnTenantId = DO.COLUMN_TENANT_ID;


	private List<String> ignoreTables;

	@Override
	public Expression getTenantId() {
		Long tenantId = TenantTool.getTenantId();
		if (tenantId == null) {
			return null;
		}
		return new LongValue(tenantId);
	}

	@Override
	public String getTenantIdColumn() {
		// return TenantLineHandler.super.getTenantIdColumn();
		return columnTenantId;
	}

	@Override
	public boolean ignoreTable(String tableName) {
		if (CollectionUtil.isEmpty(ignoreTables)) {
			return false;
		}
		return ignoreTables.contains(tableName);	}

	@Override
	public boolean ignoreInsert(List<Column> columns, String tenantIdColumn) {
		return TenantLineHandler.super.ignoreInsert(columns, tenantIdColumn);
	}

	/**
	 * 忽略租户限制
	 * @param supplier
	 * @param <T>
	 * @return
	 */
	public static <T> T executeIgnoreTenant(Supplier<T> supplier){
		try {
			// 设置忽略租户插件
			InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
			return supplier.get();
		} finally {
			InterceptorIgnoreHelper.clearIgnoreStrategy();
		}
	}

	/**
	 * 忽略租户限制
	 * @param runnable
	 */
	public static void executeIgnoreTenant(Runnable runnable){
		try {
			// 设置忽略租户插件
			InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
			runnable.run();
		} finally {
			InterceptorIgnoreHelper.clearIgnoreStrategy();
		}
	}
}
