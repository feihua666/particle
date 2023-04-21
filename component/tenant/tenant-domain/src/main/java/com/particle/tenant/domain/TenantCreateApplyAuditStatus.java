package com.particle.tenant.domain;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 租户创建申请审核状态 字典项
 * </p>
 *
 * @author yw
 * @since 2023-04-20 14:02:42
 */
public enum TenantCreateApplyAuditStatus implements IDictItem {

	/**
	 * 待审核
	 */
	un_audit
	,
	/**
	 * 审核通过
	 */
	audit_pass
	,
	/**
	 * 审核不通过
	 */
	audit_pass_not
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.tenant_create_apply_audit_status.groupCode();
	}

	/**
	 * 租户创建申请审核状态 字典组
	 */
	public enum Group implements IDictGroup {
		tenant_create_apply_audit_status;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

