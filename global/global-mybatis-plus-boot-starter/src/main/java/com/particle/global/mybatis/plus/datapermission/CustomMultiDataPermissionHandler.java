package com.particle.global.mybatis.plus.datapermission;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 自定义的数据权限过滤
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 22:21
 */
public class CustomMultiDataPermissionHandler implements MultiDataPermissionHandler {
	/**
	 * 依赖多租户处理器，因为系统中有一些系统平台级资源数据可能会分配到租户使用
	 * 而这些资源表在多租户中已经不使用租户字段进行隔离，但仍需要租户关系表进行隔离，针对这种情况，使用数据权限解决，以减少开发量
	 */
	private TenantLineHandler tenantLineHandler;

	private TenantMultiDataPermissionHandler tenantMultiDataPermissionHandler;
	private NormalMultiDataPermissionHandler normalMultiDataPermissionHandler;

	public CustomMultiDataPermissionHandler(TenantLineHandler tenantLineHandler){
		this.tenantLineHandler = tenantLineHandler;
	}

	@Override
	public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {
		Expression tenantSqlSegment = tenantSqlSegment(table, where, mappedStatementId);
		Expression normalSqlSegment = normalSqlSegment(table, tenantSqlSegment, mappedStatementId);
		// 如果没有处理过，直接返回null，否则会拼接两个一样的条件
		if (where == normalSqlSegment) {
			return null;
		}
		return normalSqlSegment;
	}

	/**
	 * 租户级数据权限
	 * @param table
	 * @param where
	 * @param mappedStatementId
	 * @return
	 */
	private Expression tenantSqlSegment(Table table, Expression where, String mappedStatementId) {
		if (tenantMultiDataPermissionHandler != null) {
			return tenantMultiDataPermissionHandler.getSqlSegment(table, where, mappedStatementId);
		}
		return where;
	}

	/**
	 * 真正的业务数据权限
	 * @param table
	 * @param where
	 * @param mappedStatementId
	 * @return
	 */
	public Expression normalSqlSegment(Table table, Expression where, String mappedStatementId) {
		if (normalMultiDataPermissionHandler != null) {
			return normalMultiDataPermissionHandler.getSqlSegment(table, where, mappedStatementId);
		}
		return where;
	}
	@Autowired(required = false)
	public void setTenantMultiDataPermissionHandler(TenantMultiDataPermissionHandler tenantMultiDataPermissionHandler) {
		this.tenantMultiDataPermissionHandler = tenantMultiDataPermissionHandler;
	}

	@Autowired(required = false)
	public void setNormalMultiDataPermissionHandler(NormalMultiDataPermissionHandler normalMultiDataPermissionHandler) {
		this.normalMultiDataPermissionHandler = normalMultiDataPermissionHandler;
	}
}
