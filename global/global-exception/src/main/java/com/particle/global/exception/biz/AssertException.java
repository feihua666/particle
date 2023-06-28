package com.particle.global.exception.biz;

import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;

import java.util.Map;

/**
 * <p>
 * 断言异常，主要用于使用 {@link Assert}时抛出的异常
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 10:47
 */
public class AssertException extends BizException {

	public static IErrorCode DEFAULT_ERR_CODE = ErrorCodeGlobalEnum.ASSERT_ERROR;


	public AssertException(Throwable cause) {
		super(DEFAULT_ERR_CODE,cause);
	}

	public AssertException(String userTip) {
		super(DEFAULT_ERR_CODE,userTip);
	}

	public AssertException(IErrorCode error, String userTip) {
		super(error, userTip);
	}

	public AssertException(IErrorCode error, Throwable cause) {
		super(error, cause);
	}

	public AssertException(IErrorCode error) {
		super(error);
	}

	public AssertException(IErrorCode error, Map<String, Object> data) {
		super(error, data);
	}

	public AssertException(IErrorCode error, Map<String, Object> data, Throwable cause) {
		super(error, data, cause);
	}
}
