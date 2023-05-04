package com.particle.global.oss.exception;

import com.particle.global.exception.BaseException;
import com.particle.global.exception.code.IErrorCode;

/**
 * <p>
 * 桶不为空异常，主要用来删除bucket抛出
 * </p>
 *
 * @author yangwei
 * @since 2023-04-28 21:57
 */
public class BucketNotEmptyException extends BaseException {

	public BucketNotEmptyException(String userTip, Throwable cause) {
		super(userTip, cause);
	}

	public BucketNotEmptyException(Throwable cause) {
		super(cause);
	}
}
