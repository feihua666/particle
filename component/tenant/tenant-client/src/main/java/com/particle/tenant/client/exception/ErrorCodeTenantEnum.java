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
	tenant_user_exceed(40000020001L, "已超过最大人数");

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
