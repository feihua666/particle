package com.particle.global.exception.system;

import com.particle.global.exception.BaseException;
import com.particle.global.exception.code.IErrorCode;

import java.util.Map;

/**
 * <p>
 * 系统异常或未知异常，该异常重试可能会工作正常，如：因网络原因导致的异常
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 13:41
 */
public class SystemException extends BaseException {

	public SystemException(IErrorCode error, Map<String, Object> data) {
		super(error, data);
	}

	public SystemException(IErrorCode error, Map<String, Object> data, Throwable cause) {
		super(error, data, cause);
	}

	public SystemException(IErrorCode error, Throwable cause) {
		super(error, cause);
	}

	public SystemException(IErrorCode error) {
		super(error);
	}
}
