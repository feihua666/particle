package com.particle.tenant.client.exception;

import com.particle.global.exception.code.IErrorCode;

/**
 * <p>
 * 租户相关 错误码
 * 其中业务码 从 0002 开始
 * </p>
 *
 * @author yangwei
 * @since 2023-05-12 18:05:41
 */
public enum ErrorCodeTenantEnum implements IErrorCode {

	/**
	 * 租户添加用户时人数限制错误
	 */
	tenant_user_exceed(40000020001L, "已超过最大人数"),
	/**
	 * 用户登录时用户已过期错误,一般根据是否过期标识判断
	 */
	tenant_user_expired(40000020002L, "用户已过期"),
	/**
	 * 用户登录时用户已过期错误,一般根据是否超过过期时间标识判断
	 */
	tenant_user_expired_limit(40000020003L, "用户已超过使用期限"),
	/**
	 * 用户登录时用户尚未生效
	 */
	tenant_user_not_effective(40000020004L, "用户尚未生效"),
	/**
	 * 用户登录时用户已离职/退出
	 */
	tenant_user_leave(40000020005L, "用户已退出"),
	/**
	 * 用户登录时，租户已彬
	 */
	tenant_disabled(40000020006L, "租户已禁用"),
	/**
	 * 用户登录时，租户未生效
	 */
	tenant_not_effective(40000020007L, "租户尚未生效"),
	/**
	 * 用户登录时租户尚未生效
	 */
	tenant_not_expired_limit(40000020008L, "租户已超过使用期限");

	ErrorCodeTenantEnum(long status, String errMessage) {
		this.status = status;
		this.errCode = this.name();
		this.errMessage = errMessage;
	}
	/**
	 * 状态业务码码
	 */
	private long status;
	/**
	 * 错误代码
	 */
	private String errCode;
	/**
	 * 错误信息描述
	 */
	private String errMessage;

	@Override
	public long getStatus() {
		return status;
	}

	@Override
	public String getErrCode() {
		return errCode;
	}

	@Override
	public String getErrMessage() {
		return errMessage;
	}
}
