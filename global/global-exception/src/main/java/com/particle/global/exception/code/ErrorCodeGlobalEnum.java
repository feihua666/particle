package com.particle.global.exception.code;

/**
 * <p>
 * 全局常用错误代码
 * </p>
 *
 * @author yangwei
 * @since 2022-05-12 21:20
 */
public enum ErrorCodeGlobalEnum implements IErrorCode {


	/**
	 * 系统错误
	 */
	SYSTEM_ERROR(50000000001L, "系统错误"),
	/**
	 * 未知错误
	 */
	UNKNOWN_ERROR(50000000002L, "未知异常"),

	/**
	 * 一般在异常 baseException中使用
	 */
	PLACEHOLDER_ERROR(50000000003L, "默认异常"),

	/**
	 * 保存失败
	 */
	SAVE_ERROR(50000000010L,"保存失败"),
	/**
	 * 更新数据版本错误，需要重新刷新数据
	 */
	UPDATE_DATA_VERSION_ERROR(50000000011L,"数据版本错误"),
	/**
	 * 锁已经被占用异常
	 * [ˈɒkjupaɪd]
	 */
	LOCK_ALREADY_OCCUPIED_ERROR(50000000012L,"锁已经被占用"),

	/**
	 * 删除失败，系统内部错误
	 */
	DELETE_ERROR(50000000012L,"删除失败"),
	/**
	 * 常用的400 错误
	 */
	BAD_REQUEST_ERROR(40000000001L,"请求参数有错误"),
	/**
	 * 枚举转换错误，一般为给了一个不存在的枚举值字符串
	 */
	NO_ENUM_CONSTANT_ERROR(40000000002L,"参数错误，枚举不存在"),
	/**
	 * 未登录
	 */
	UNAUTHORIZED_ERROR(40100000001L,"未认证"),
	// 未知原因的情况下使用
	AUTHENTICATION_ERROR(40100000002L,"登录失败"),
	INVALID_ACCOUNT_ERROR(40100000003L,"账号不正确"),
	INVALID_PASSWORD_ERROR(40100000004L,"密码不正确"),
	ACCOUNT_LOACKED_ERROR(40100000005L,"账号已经被锁定"),
	ACCOUNT_EXPIRED_ERROR(40100000006L,"账号已经过期"),
	PASSWORD_EXPIRED_ERROR(40100000007L,"密码已经过期"),
	ACCOUNT_DISABLED_ERROR(40100000008L,"账号已经被禁用"),

	NO_PRIVILEGE_ERROR(40300000001L,"没有权限"),
	NO_DATA_PRIVILEGE_ERROR(40300000002L,"没有数据权限"),
	// 一般用在用户匿名登录，没有权限，登录后尝试的场景
	NO_PRIVILEGE_ANONYMOUS_ERROR(40300000003L,"没有权限,请尝试登录"),
	NO_PRIVILEGE_RATELIMIT_ERROR(40300000004L,"超过限流速率"),

	/**
	 * 数据不存在
	 */
	DATA_NOT_FOUND(40400000001L,"数据不存在"),
	/**
	 * 地址不存在
	 */
	URL_NOT_FOUND(40400000002L,"地址不存在"),


	METHOD_NOT_SUPPORTED_ERROR(40500000002L,"不支持的方法"),

	MEDIA_TYPE_NOT_SUPPORTED_ERROR(41500000001L,"请求参数有错误");


	ErrorCodeGlobalEnum(long status, String errMessage) {
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
