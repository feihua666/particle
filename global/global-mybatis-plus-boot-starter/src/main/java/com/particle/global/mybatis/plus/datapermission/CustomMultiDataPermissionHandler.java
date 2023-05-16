package com.particle.global.mybatis.plus.datapermission;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 自定义的数据权限过滤,本实现数据权限包括两部分，一部分为租户数据权限，为系统级别的；一部分为真正的数据权限
 * 租户数据权限意义：项目中一部分表是系统级别的全局表，不考虑租户字段，但查询的时候又避免不了对租户进行限制
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 22:21
 */
public class CustomMultiDataPermissionHandler implements MultiDataPermissionHandler {
	/**
	 * 依赖多租户处理器，因为系统中有一些系统平台级资源数据可能会分配到租户使用
	 * 而这些资源表在多租户中已经不使用租户字段进行隔离，但仍需要租户关系表进行隔离，针对这种情况，使用数据权限解决，以减少开发量
	 * 暂时没用到
	 */
	private TenantLineHandler tenantLineHandler;

	private TenantMultiDataPermissionHandler tenantMultiDataPermissionHandler;
	private NormalMultiDataPermissionHandler normalMultiDataPermissionHandler;

	private LoginUserSuperAdminResolver loginUserSuperAdminResolver;

	public CustomMultiDataPermissionHandler(TenantLineHandler tenantLineHandler){
		this.tenantLineHandler = tenantLineHandler;
	}

	/**
	 * 没有处理就直接返回null，否则会拼接两个一样的条件
	 * 该方法是获取额外的sql数据权限片段
	 * @param table
	 * @param where
	 * @param mappedStatementId
	 * @return
	 */
	@Override
	public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {
		if (loginUserSuperAdminResolver != null) {
			// 超级管理员不限制
			if (loginUserSuperAdminResolver.resolve()) {
				return null;
			}
		}


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

	@Autowired(required = false)
	public void setLoginUserSuperAdminResolver(LoginUserSuperAdminResolver loginUserSuperAdminResolver) {
		this.loginUserSuperAdminResolver = loginUserSuperAdminResolver;
	}
}
