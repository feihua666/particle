package com.particle.user.client.exception;

import com.particle.global.exception.code.ErrorCodeGlobalStatusMax;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.exception.code.IErrorCodeStatusMax;

/**
 * <p>
 * 用户相关 错误码
 * 其中业务码 从 0001 开始
 * 添加枚举常量 需要同步修改 {@link IErrorCodeUserStatusMax} 以记录当前最大值
 * </p>
 *
 * @author yangwei
 * @since 2022-12-25 23:00
 */
public enum ErrorCodeUserEnum implements IErrorCode {


	/**
	 * 用户登录标识密码不存在，用于修改密码或重置密码
	 */
	USER_IDENTIFIER_PASSWORD_NOT_EXIST(40000010001L, "系统错误");

	ErrorCodeUserEnum(long status, String errMessage) {
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
