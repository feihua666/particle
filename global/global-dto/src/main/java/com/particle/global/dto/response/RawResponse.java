package com.particle.global.dto.response;

import com.particle.global.exception.code.IErrorCode;

/**
 * <p>
 * 原生数据响应对象
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 10:17
 */
public class RawResponse extends SingleResponse<Object>{


	public static RawResponse buildSuccess() {
		RawResponse response = new RawResponse();
		response.setSuccess(true);
		return response;
	}

	public static RawResponse buildFailure(IErrorCode errCodeGlobal, String userTip) {
		RawResponse response = new RawResponse();
		response.setSuccess(false);
		response.setStatus(errCodeGlobal.getStatus());
		response.setErrCode(errCodeGlobal.getErrCode());
		response.setErrMessage(handleUserTip(errCodeGlobal,userTip));
		return response;
	}

	public static RawResponse ofRaw(Object data) {
		RawResponse response = new RawResponse();
		response.setSuccess(true);
		response.setData(data);
		return response;
	}
	public static RawResponse buildFailure(IErrorCode errCodeGlobal) {
		return buildFailure(errCodeGlobal, null);
	}

}
