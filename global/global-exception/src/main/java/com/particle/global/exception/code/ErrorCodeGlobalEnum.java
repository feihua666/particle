package com.particle.global.exception.code;

import com.particle.global.exception.Assert;

/**
 * <p>
 * 全局常用错误代码
 * 添加枚举常量 需要同步修改 {@link ErrorCodeGlobalStatusMax} 以记录当前最大值
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
	DELETE_ERROR(50000000013L,"删除失败"),

	/**
	 * 读取超时
	 */
	READ_TIMEOUT(50000000014L, "读取超时"),
	/**
	 * 连接超时
	 */
	CONNECT_TIMEOUT(50000000015L, "连接超时"),
	/**
	 * 断言异常，主要用于使用 {@link Assert}时抛出的异常
	 */
	ASSERT_ERROR(50000000016L, "断言异常"),
	/**
	 * 缺少必要的配置，或配置不正确
	 */
	CONFIG_ERROR(50000000017L, "缺少必要的配置，或配置不正确"),
	/**
	 * 常用的400 错误
	 */
	BAD_REQUEST_ERROR(40000000001L,"请求参数有错误"),
	/**
	 * 枚举转换错误，一般为给了一个不存在的枚举值字符串
	 */
	NO_ENUM_CONSTANT_ERROR(40000000002L,"参数错误，枚举不存在"),
	/**
	 * 验证码不正确
	 */
	CAPTCHA_ERROR(40000000003L,"验证码不正确"),
	/**
	 * 非常请求，一些不正常的请求，主要指能预测到的手动修改参数情况
	 */
	ILLEGAL_REQUEST_ERROR(40000000004L,"非法请求，你的行为已被记录！！"),
	EMAIL_REQUEST_ERROR(40000000005L,"邮箱格式不正确"),
	MOBILE_REQUEST_ERROR(40000000006L,"手机号格式不正确"),
	MOBILE_EMAIL_REQUEST_ERROR(40000000007L,"手机号或邮箱格式不正确"),
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
	ACCOUNT_NOT_LOGIN_ERROR(40100000009L,"未认证，用户未登录"),

	NO_PRIVILEGE_ERROR(40300000001L,"没有权限"),
	NO_DATA_PRIVILEGE_ERROR(40300000002L,"没有数据权限"),
	// 一般用在用户匿名登录，没有权限，登录后尝试的场景
	NO_PRIVILEGE_ANONYMOUS_ERROR(40300000003L,"没有权限,请尝试登录"),
	NO_PRIVILEGE_RATELIMIT_ERROR(40300000004L,"超过限流速率"),
	USAGE_COUNT_LIMIT_ERROR(40300000005L,"使用次数已达上限"),

	/**
	 * 数据不存在，如果没有数据时建议使用
	 */
	DATA_NOT_FOUND(40400000001L,"数据不存在"),
	/**
	 * 接口地址不存在
	 */
	URL_NOT_FOUND(40400000002L,"接口地址不存在"),


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
