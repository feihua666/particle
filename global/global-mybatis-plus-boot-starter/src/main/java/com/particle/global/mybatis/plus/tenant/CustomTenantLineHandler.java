package com.particle.global.mybatis.plus.tenant;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.Data;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-06-30 14:44
 */
@Data
public class CustomTenantLineHandler implements TenantLineHandler {


	private List<String> ignoreTables;

	@Override
	public Expression getTenantId() {
		return new LongValue(TenantTool.getTenantId());
	}

	@Override
	public String getTenantIdColumn() {
		return TenantLineHandler.super.getTenantIdColumn();
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
}
