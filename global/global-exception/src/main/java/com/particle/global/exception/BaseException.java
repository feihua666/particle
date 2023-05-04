package com.particle.global.exception;

import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 基础异常类，所有异常的基类
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 13:37
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static IErrorCode DEFAULT_ERR_CODE = ErrorCodeGlobalEnum.PLACEHOLDER_ERROR;

	private final IErrorCode error;
	private final Map<String, Object> data = new HashMap<>();

	public BaseException(Throwable cause) {
		super(format(DEFAULT_ERR_CODE.getErrCode(), handleStaticUserTip(DEFAULT_ERR_CODE,DEFAULT_ERR_CODE.getErrMessage()), null));
		this.error = DEFAULT_ERR_CODE;
	}

	public BaseException(String userTip,IErrorCode error) {
		super(format(error.getErrCode(), handleStaticUserTip(error,userTip), null));
		this.error = error;
	}

	protected BaseException(String userTip) {
		super(format(DEFAULT_ERR_CODE.getErrCode(), handleStaticUserTip(DEFAULT_ERR_CODE,userTip), null));
		this.error = DEFAULT_ERR_CODE;
	}
	protected BaseException(String userTip, Throwable cause) {
		super(format(DEFAULT_ERR_CODE.getErrCode(), handleStaticUserTip(DEFAULT_ERR_CODE,userTip), null),cause);
		this.error = DEFAULT_ERR_CODE;
	}
	protected BaseException(IErrorCode error, Throwable cause) {
		this(error,null,cause);
	}

	protected BaseException(IErrorCode error) {
		super(format(error.getErrCode(), error.getErrMessage(), null));
		this.error = error;
		this.putAllDataIfNotEmpty(data);
	}

	protected BaseException(IErrorCode error, Map<String, Object> data) {
		super(format(error.getErrCode(), error.getErrMessage(), data));
		this.error = error;
		this.putAllDataIfNotEmpty(data);
	}

	protected BaseException(IErrorCode error, Map<String, Object> data, Throwable cause) {
		super(format(error.getErrCode(), error.getErrMessage(), data), cause);
		this.error = error;
		this.putAllDataIfNotEmpty(data);
	}

	private void putAllDataIfNotEmpty(Map<String, Object> data){
		Optional.ofNullable(data).ifPresent(dataMap->{
			this.data.putAll(dataMap);
		});
	}
	private static String format(String code, String message, Map<String, Object> data) {
		if (data == null) {
			return message;
		}
		return String.format("%s:%s", message, data == null ? "" : data.toString());
	}

	public IErrorCode getError() {
		return error;
	}

	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * 构造函数中使用，须为静态常量方法
	 * @param errorCode
	 * @param userTip
	 * @return
	 */
	private static String handleStaticUserTip(IErrorCode errorCode, String userTip) {
		return Optional.ofNullable(userTip).map(ut -> StrUtil.format(userTip,errorCode.getErrMessage())).orElse(errorCode.getErrMessage());

	}

	protected String handleUserTip(IErrorCode errorCode, String userTip) {
		return handleStaticUserTip(errorCode,userTip);

	}

}
