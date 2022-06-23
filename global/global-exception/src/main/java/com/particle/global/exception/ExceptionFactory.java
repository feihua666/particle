package com.particle.global.exception;

import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.exception.system.SystemException;

import java.util.Map;

/**
 * <p>
 * 异常工厂类，静态工具类
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 13:43
 */
public class ExceptionFactory {

	public static BizException bizException(IErrorCode errorCode) {
		return new BizException(errorCode);
	}
	public static BizException bizException(IErrorCode errorCode, Map<String,Object> data) {
		return new BizException(errorCode,data);
	}
	public static BizException bizException(IErrorCode errorCode, Map<String,Object> data,Throwable e) {
		return new BizException(errorCode,data,e);
	}


	public static SystemException systemException(IErrorCode errorCode) {
		return new SystemException(errorCode);
	}
	public static SystemException systemException(IErrorCode errorCode, Map<String,Object> data) {
		return new SystemException(errorCode,data);
	}
	public static SystemException systemException(IErrorCode errorCode, Map<String,Object> data,Throwable e) {
		return new SystemException(errorCode,data,e);
	}
}
