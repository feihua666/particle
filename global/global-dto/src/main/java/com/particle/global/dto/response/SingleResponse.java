package com.particle.global.dto.response;

import com.particle.global.exception.code.IErrorCode;

/**
 * <p>
 * 单条数据响应
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 14:14
 */
public class SingleResponse<T> extends Response {

	private static final long serialVersionUID = 1L;

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static SingleResponse buildSuccess() {
		SingleResponse response = new SingleResponse();
		response.setSuccess(true);
		return response;
	}

	public static SingleResponse buildFailure(IErrorCode errCodeGlobal,String userTip) {
		SingleResponse response = new SingleResponse();
		response.setSuccess(false);
		response.setStatus(errCodeGlobal.getStatus());
		response.setErrCode(errCodeGlobal.getErrCode());
		response.setErrMessage(handleUserTip(errCodeGlobal,userTip));
		return response;
	}

	public static <T> SingleResponse<T> of(T data) {
		SingleResponse<T> response = new SingleResponse<>();
		response.setSuccess(true);
		response.setData(data);
		return response;
	}
	public static SingleResponse buildFailure(IErrorCode errCodeGlobal) {
		return buildFailure(errCodeGlobal, null);
	}

}