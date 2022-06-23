package com.particle.global.dto.response;

import com.particle.global.exception.code.IErrorCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 多条数据响应返回
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 14:32
 */
public class MultiResponse<T> extends Response {

	private static final long serialVersionUID = 1L;

	private Collection<T> data;

	public List<T> getData() {
		return null == data ? Collections.emptyList() : new ArrayList<>(data);
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public boolean isEmpty() {
		return data == null || data.isEmpty();
	}

	public boolean isNotEmpty() {
		return !isEmpty();
	}

	public static MultiResponse buildSuccess() {
		MultiResponse response = new MultiResponse();
		response.setSuccess(true);
		return response;
	}

	public static MultiResponse buildFailure(IErrorCode errCodeGlobal) {
		return buildFailure(errCodeGlobal, null) ;
	}
	public static MultiResponse buildFailure(IErrorCode errCodeGlobal,String userTip) {
		MultiResponse response = new MultiResponse();
		response.setSuccess(false);
		response.setStatus(errCodeGlobal.getStatus());
		response.setErrCode(errCodeGlobal.getErrCode());
		response.setErrMessage(handleUserTip(errCodeGlobal,userTip));
		return response;
	}
	public static <T> MultiResponse<T> of(Collection<T> data) {
		MultiResponse<T> response = new MultiResponse<>();
		response.setSuccess(true);
		response.setData(data);
		return response;
	}

}
