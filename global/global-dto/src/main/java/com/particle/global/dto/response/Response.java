package com.particle.global.dto.response;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.DTO;
import com.particle.global.exception.code.IErrorCode;

import java.util.Optional;

/**
 * <p>
 * 响应对象或返回值对象，一般为应用门面服务返回
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 14:11
 */
public class Response extends DTO {

	private static final long serialVersionUID = 1L;

	private boolean success;

	private Long status;

	private String errCode;

	private String errMessage;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Response [success=" + success + ", status="+ status +", errCode=" + errCode + ", errMessage=" + errMessage + "]";
	}

	public static Response buildSuccess() {
		Response response = new Response();
		response.setSuccess(true);
		return response;
	}

	public static Response buildFailure(IErrorCode errorCode) {

		return buildFailure(errorCode, null);
	}
	public static Response buildFailure(IErrorCode errorCode,String userTip) {
		Response response = new Response();
		response.setSuccess(false);
		response.setStatus(errorCode.getStatus());
		response.setErrCode(errorCode.getErrCode());
		response.setErrMessage(handleUserTip(errorCode,userTip));
		return response;
	}
	protected static String handleUserTip(IErrorCode errorCode, String userTip) {
		return Optional.ofNullable(StrUtil.emptyToNull(userTip)).map(ut -> StrUtil.format(userTip,errorCode.getErrMessage())).orElse(errorCode.getErrMessage());

	}
}
