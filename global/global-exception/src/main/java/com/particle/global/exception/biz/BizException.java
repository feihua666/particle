package com.particle.global.exception.biz;

import com.particle.global.exception.BaseException;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;

import java.util.Map;

/**
 * <p>
 * 业务异常或已知异常，这种异常不需要重试
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 13:40
 */
public class BizException extends BaseException {

	public static IErrorCode DEFAULT_ERR_CODE = ErrorCodeGlobalEnum.PLACEHOLDER_ERROR;

	public BizException(Throwable cause) {
		super(DEFAULT_ERR_CODE, cause);
	}

	public BizException(String userTip) {
		super(userTip,DEFAULT_ERR_CODE);
	}

	public BizException(IErrorCode error,String userTip) {
		super(userTip,error);
	}

	public BizException(IErrorCode error, Throwable cause) {
		super(error, cause);
	}

	public BizException(IErrorCode error) {
		super(error);
	}

	public BizException(IErrorCode error, Map<String, Object> data) {
		super(error, data);
	}

	public BizException(IErrorCode error, Map<String, Object> data, Throwable cause) {
		super(error, data, cause);
	}
}
