package com.particle.global.openapi.exception;

import com.particle.global.exception.code.IErrorCode;

/**
 * <p>
 * 开放接口相关 错误码
 * 其中业务码 从 0003 开始
 * 添加枚举常量 需要同步修改 {@link IErrorCodeOpenapiStatusMax} 以记录当前最大值
 * </p>
 *
 * @author yangwei
 * @since 2022-12-25 23:00
 */
public enum ErrorCodeOpenapiEnum implements IErrorCode {


	/**
	 * 用户登录标识密码不存在，用于修改密码或重置密码
	 */
	OPENAPI_CLIENT_ID_NOT_EXIST(40000030001L, "客户端id不存在"),
	/**
	 * 一般指开放接口请求验证不通过的一个通用返回结果
	 */
	OPENAPI_ILLEGAL_REQUEST_ERROR(40000030002L, "非法请求"),
	/**
	 * 签名不正确
	 */
	OPENAPI_ILLEGAL_REQUEST_INVALID_SIGNATURE_ERROR(40000030003L, "签名不正确"),
	/**
	 * 重复请求
	 */
	OPENAPI_ILLEGAL_REQUEST_REPEATED_REQUEST_ERROR(40000030004L, "重复请求"),
	/**
	 * 请求已过期
	 */
	OPENAPI_ILLEGAL_REQUEST_EXPIRED_REQUEST_ERROR(40000030005L, "请求已过期"),

	/**
	 * 比非法请求稍精准一些
	 */
	OPENAPI_BAD_REQUEST_ERROR(40000030006L,"请求参数有错误"),

	/**
	 * 客户端已禁用
	 */
	OPENAPI_CLIENT_DISABLED(40000030007L, "客户端已禁用"),

	/**
	 * 供应商 负载均衡器不存在
	 */
	OPENAPI_LOADBALANCER_NOT_EXIST(50000030001L, "负载均衡器不存在"),
	/**
	 * 供应商执行器不存在
	 */
	OPENAPI_EXECUTE_PROVIDER_NOT_EXIST(50000030002L, "供应商执行器不存在");

	ErrorCodeOpenapiEnum(long status, String errMessage) {
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
